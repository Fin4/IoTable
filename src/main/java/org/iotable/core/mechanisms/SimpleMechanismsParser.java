package org.iotable.core.mechanisms;

import org.iotable.core.model.IoTable;
import org.iotable.core.model.ioUnits.IoUnit;
import org.iotable.core.model.mechanisms.Mechanism;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SimpleMechanismsParser implements MechanismsParser {

    @Override
    public List<Mechanism> getBySymbol(IoTable ioTable) {

        List<Mechanism> mechanisms = new ArrayList<>();

/*        Map<String, List<IoUnit>> map = ioUnits.stream().collect(Collectors.groupingBy(ioUnit -> {

                    String symbol = ioUnit.symbol;

                    if (symbol.matches(".+\\.[a-zA-Z0-9]+")) return symbol.substring(0, symbol.lastIndexOf("."));
                    else return symbol;
                }
        ));

        for (Map.Entry<String, List<IoUnit>> entry : map.entrySet()) {
            Mechanism m = new Mechanism();
            m.setSymbol(entry.getKey());
            m.setIoUnits(entry.getValue());
            mechanisms.add(m);
        }*/

        return mechanisms;
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
