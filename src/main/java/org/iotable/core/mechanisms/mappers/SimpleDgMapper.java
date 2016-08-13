package org.iotable.core.mechanisms.mappers;

import org.iotable.core.model.ioUnits.DiscreteInput;
import org.iotable.core.model.ioUnits.DiscreteOutput;
import org.iotable.core.model.ioUnits.IoUnit;
import org.iotable.core.model.mechanisms.Mechanism;

import java.util.List;
import java.util.stream.Collectors;


public class SimpleDgMapper implements MechanismMapper {

    @Override
    public String map(String template, Mechanism mechanism) {

        List<IoUnit> diUnits = mechanism.getDiscreteInputs()
                .stream()
                .map(DiscreteInput::getIoUnit).collect(Collectors.toList());
        List<IoUnit> doUnits = mechanism.getDiscreteOutputs()
                .stream()
                .map(DiscreteOutput::getIoUnit).collect(Collectors.toList());

        String opened = getSymbol(".+\\.1", diUnits);
        String closed = getSymbol(".+\\.2", diUnits);
        String open = getSymbol(".+\\.Y1", doUnits);
        String close = getSymbol(".+\\.Y2", doUnits);

        return template
                .replaceAll("%symbol%", mechanism.getSymbol().replaceAll(".+-", "").replaceAll("\\.", "_"))
                .replaceAll("%opened%", opened)
                .replaceAll("%closed%", closed)
                .replaceAll("%open%", open)
                .replaceAll("%close%", close);
    }

    private String getSymbol(String regex, List<IoUnit> ioUnits) {

        return ioUnits
                .stream()
                .filter(ioUnit -> ioUnit.symbol.matches(regex))
                .findFirst()
                .map(ioUnit -> ioUnit.symbol.replaceAll(".+-", "").replaceAll("\\.", "_"))
                .orElse("NULL");
    }
}
