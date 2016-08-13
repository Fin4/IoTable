package org.iotable.core.mechanisms.mappers;

import org.iotable.core.Config;
import org.iotable.core.model.ioUnits.DiscreteInput;
import org.iotable.core.model.ioUnits.DiscreteOutput;
import org.iotable.core.model.ioUnits.IoUnit;
import org.iotable.core.model.mechanisms.Mechanism;

import java.util.List;
import java.util.stream.Collectors;


public final class SimpleDgMapper implements MechanismMapper {

    @Override
    public String map(String template, Mechanism mechanism) {

        List<IoUnit> diUnits = mechanism.getDiscreteInputs()
                .stream()
                .map(DiscreteInput::getIoUnit).collect(Collectors.toList());
        List<IoUnit> doUnits = mechanism.getDiscreteOutputs()
                .stream()
                .map(DiscreteOutput::getIoUnit).collect(Collectors.toList());

        String opened = getSymbol(Config.getProperty("dg.openedSymbol"), diUnits);
        String closed = getSymbol(Config.getProperty("dg.closedSymbol"), diUnits);
        String open = getSymbol(Config.getProperty("dg.openSymbol"), doUnits);
        String close = getSymbol(Config.getProperty("dg.closeSymbol"), doUnits);

        return template
                .replaceAll(Config.getProperty("dg.map.symbol"), mechanism.getSymbol().replaceAll(".+-", "").replaceAll("\\.", "_"))
                .replaceAll(Config.getProperty("dg.map.opened"), opened)
                .replaceAll(Config.getProperty("dg.map.closed"), closed)
                .replaceAll(Config.getProperty("dg.map.open"), open)
                .replaceAll(Config.getProperty("dg.map.close"), close);
    }

    private String getSymbol(String regex, List<IoUnit> ioUnits) {

        return ioUnits
                .stream()
                .filter(ioUnit -> ioUnit.symbol.matches(regex))
                .findFirst()
                .map(ioUnit -> ioUnit.symbol.replaceAll(".+-", "").replaceAll("\\.", "_"))
                .orElse(Config.getProperty("dg.nullUnit"));
    }
}
