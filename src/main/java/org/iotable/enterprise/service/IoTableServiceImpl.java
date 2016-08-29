package org.iotable.enterprise.service;

import org.iotable.core.document.IoTableDocument;
import org.iotable.core.model.IoTable;
import org.iotable.core.model.ioUnits.AnalogInput;
import org.iotable.core.model.ioUnits.AnalogOutput;
import org.iotable.core.model.ioUnits.DiscreteInput;
import org.iotable.core.model.ioUnits.DiscreteOutput;
import org.iotable.core.normalize.validation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IoTableServiceImpl implements IoTableService {

    @Autowired private AiValidator aiValidator;
    @Autowired private DiValidator diValidator;
    @Autowired private AoValidator aoValidator;
    @Autowired private DoValidator doValidator;

    @Override
    public IoTable getFromWorkbook(final IoTableDocument document) {

        return document.getAsIoTable();
    }

    @Override
    public IoTable validate(IoTable ioTable) {

        List<AnalogInput> analogInputs = ioTable.getAnalogInputs();
        List<DiscreteInput> discreteInputs = ioTable.getDiscreteInputs();
        List<AnalogOutput> analogOutputs = ioTable.getAnalogOutputs();
        List<DiscreteOutput> discreteOutputs = ioTable.getDiscreteOutputs();

        return new IoTable(aiValidator.validate(analogInputs),
                diValidator.validate(discreteInputs),
                aoValidator.validate(analogOutputs),
                doValidator.validate(discreteOutputs));
    }
}
