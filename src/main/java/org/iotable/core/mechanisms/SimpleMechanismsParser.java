package org.iotable.core.mechanisms;

import org.iotable.core.model.IoTable;
import org.iotable.core.model.ioUnits.*;
import org.iotable.core.model.mechanisms.Mechanism;
import org.springframework.web.servlet.tags.form.SelectTag;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SimpleMechanismsParser implements MechanismsParser {

    @Override
    public List<Mechanism> getBySymbol(IoTable ioTable) {

        List<Mechanism> mechanisms = new ArrayList<>();

        Map<String, List<AnalogInput>> aiMap = ioTable.getAnalogInputs().stream().collect(Collectors.groupingBy(analogInput -> {

                    String symbol = analogInput.getIoUnit().symbol;

                    if (symbol.matches(".+\\.[a-zA-Z0-9]+")) return symbol.substring(0, symbol.lastIndexOf("."));
                    else return symbol;
                }
        ));

        Map<String, List<DiscreteInput>> diMap = ioTable.getDiscreteInputs().stream().collect(Collectors.groupingBy(discreteInput -> {

                    String symbol = discreteInput.getIoUnit().symbol;

                    if (symbol.matches(".+\\.[a-zA-Z0-9]+")) return symbol.substring(0, symbol.lastIndexOf("."));
                    else return symbol;
                }
        ));

        Map<String, List<AnalogOutput>> aoMap = ioTable.getAnalogOutputs().stream()
                .collect(Collectors.groupingBy(analogOutput -> {

                    String symbol = analogOutput.getIoUnit().symbol;

                    if (symbol.matches(".+\\.[a-zA-Z0-9]+")) return symbol.substring(0, symbol.lastIndexOf("."));
                    else return symbol;
                }
        ));

        Map<String, List<DiscreteOutput>> doMap = ioTable.getDiscreteOutputs().stream()
                .collect(Collectors.groupingBy(discreteOutput -> {

                    String symbol = discreteOutput.getIoUnit().symbol;

                    if (symbol.matches(".+\\.[a-zA-Z0-9]+")) return symbol.substring(0, symbol.lastIndexOf("."));
                    else return symbol;
                }
        ));



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

    private String group(String symbol) {

        if (symbol.matches(".+\\.[a-zA-Z0-9]+")) return symbol.substring(0, symbol.lastIndexOf("."));
        else return symbol;

    }

    @Override
    public List<Mechanism> getByDescription(IoTable ioTable) {

        List<Mechanism> mechanisms = new ArrayList<>();
/*
        Map<String, List<IoUnit>> map = ioUnits.stream().collect(Collectors.groupingBy(ioUnit -> {

                    String description = ioUnit.description;

                    if (description.matches(".+\\-.+")) return description.substring(0, description.lastIndexOf("-"));
                    else return description;
                }
        ));

        for (Map.Entry<String, List<IoUnit>> entry : map.entrySet()) {

            String desc = entry.getKey();

        }*/

        return mechanisms;
    }

    @Override
    public List<Mechanism> getEntire(IoTable ioTable) {
        return null;
    }

}
