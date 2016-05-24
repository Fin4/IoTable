package org.rldev.iotable.mechanisms;

import org.rldev.iotable.model.ioUnits.IoUnit;
import org.rldev.iotable.model.mechanisms.Mechanism;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SimpleMechanismsParser implements MechanismsParser {

    @Override
    public List<Mechanism> getMechanisms(List<IoUnit> ioUnits) {

        List<Mechanism> mechanisms = new ArrayList<>();

        Map<String, List<IoUnit>> map = ioUnits.stream().collect(Collectors.groupingBy(ioUnit -> {

                    String symbol = ioUnit.getSymbol();

                    if (symbol.matches(".+\\.[a-zA-Z0-9]+")) return symbol.substring(0, symbol.lastIndexOf("."));
                    else return symbol;
                }
        ));

        for (Map.Entry<String, List<IoUnit>> entry: map.entrySet()) {
            Mechanism m = new Mechanism();
            m.setSymbol(entry.getKey());
            m.setIoUnits(entry.getValue());
            mechanisms.add(m);
        }

        return mechanisms;
    }

    @Override
    public List<Mechanism> getMechanisms(String json) {
        return null;
    }
}
