package org.iotable.core.mechanisms.mappers;

import org.iotable.core.model.mechanisms.Mechanism;


public class SimpleDgMapper implements MechanismMapper {

    @Override
    public String map(String template, Mechanism mechanism) {

        String result = template.replaceAll("%symbol%", mechanism.getSymbol().replaceAll(".+-", "").replaceAll("\\.", "_"));

        String result1 = mechanism.getDiscreteInputs()
                .stream()
                .filter(discreteInput -> discreteInput.getIoUnit().symbol.matches(".+\\.1"))
                .findFirst()
                .map(di -> result.replaceAll("%opened%", di.getIoUnit().symbol.replaceAll(".+-", "").replaceAll("\\.", "_")))
                .orElse(result.replaceAll("%opened%", "NULL"));


        String result2 = mechanism.getDiscreteInputs()
                .stream()
                .filter(discreteInput -> discreteInput.getIoUnit().symbol.matches(".+\\.2"))
                .findFirst()
                .map(di -> result1.replaceAll("%closed%", di.getIoUnit().symbol.replaceAll(".+-", "").replaceAll("\\.", "_")))
                .orElse(result1.replaceAll("%closed%", "NULL"));

        return result2;
    }
}
