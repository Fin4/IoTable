package org.iotable.core.mechanisms;

import org.iotable.core.model.IoTable;
import org.iotable.core.model.ioUnits.*;
import org.iotable.core.model.mechanisms.Mechanism;

import java.util.*;
import java.util.stream.Collectors;

public class SimpleMechanismsParser implements MechanismsParser {

    @Override
    public List<Mechanism> getBySymbol(IoTable ioTable) {

        List<Mechanism> mechanisms = new ArrayList<>();

        Map<String, List<AnalogInput>> aiMap = ioTable.getAnalogInputs().stream()
                .collect(Collectors.groupingBy(analogInput -> dotSymbolAnalyzer(analogInput.getIoUnit().symbol)));

        Map<String, List<DiscreteInput>> diMap = ioTable.getDiscreteInputs().stream()
                .collect(Collectors.groupingBy(discreteInput -> dotSymbolAnalyzer(discreteInput.getIoUnit().symbol)));

        Map<String, List<AnalogOutput>> aoMap = ioTable.getAnalogOutputs().stream()
                .collect(Collectors.groupingBy(analogOutput -> dotSymbolAnalyzer(analogOutput.getIoUnit().symbol)));

        Map<String, List<DiscreteOutput>> doMap = ioTable.getDiscreteOutputs().stream()
                .collect(Collectors.groupingBy(discreteOutput -> dotSymbolAnalyzer(discreteOutput.getIoUnit().symbol)));

        Set<String> m = new HashSet<>();
        m.addAll(aiMap.keySet());
        m.addAll(diMap.keySet());
        m.addAll(aoMap.keySet());
        m.addAll(doMap.keySet());

        for (String s : m) {

            List<AnalogInput> analogInputs = new ArrayList<>();
            List<DiscreteInput> discreteInputs = new ArrayList<>();
            List<AnalogOutput> analogOutputs = new ArrayList<>();
            List<DiscreteOutput> discreteOutputs = new ArrayList<>();

            if (aiMap.containsKey(s)) analogInputs = aiMap.get(s);
            if (diMap.containsKey(s)) discreteInputs = diMap.get(s);
            if (aoMap.containsKey(s)) analogOutputs = aoMap.get(s);
            if (doMap.containsKey(s)) discreteOutputs = doMap.get(s);

            Mechanism mechanism = new Mechanism(s, "", analogInputs, discreteInputs, analogOutputs, discreteOutputs);
            mechanisms.add(mechanism);
        }

        return mechanisms;
    }

    private String dotSymbolAnalyzer(String symbol) {

        if (symbol.matches(".+\\.[a-zA-Z0-9]+")) return symbol.substring(0, symbol.lastIndexOf("."));
        else return symbol;
    }

    @Override
    public List<Mechanism> getByDescription(IoTable ioTable) {

        return new ArrayList<>();
    }

    @Override
    public List<Mechanism> getEntire(IoTable ioTable) {
        return new ArrayList<>();
    }

    @Override
    public List<Mechanism> getByType(final String type, final List<Mechanism> mechanisms) {

        return mechanisms.stream()
                .filter(mechanism -> mechanism.getSymbol().startsWith(type))
                .collect(Collectors.toList());
    }
}
