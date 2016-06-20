package org.rldev.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.rldev.iotable.document.IoTableDocument;
import org.rldev.iotable.model.IoTable;
import org.rldev.iotable.model.ioUnits.AnalogInput;
import org.rldev.iotable.model.ioUnits.AnalogOutput;
import org.rldev.iotable.model.ioUnits.DiscreteInput;
import org.rldev.iotable.model.ioUnits.DiscreteOutput;
import org.rldev.iotable.model.ioUnits.typeadapters.AnalogInputTypeAdapter;
import org.rldev.iotable.model.ioUnits.typeadapters.AnalogOutputTypeAdapter;
import org.rldev.iotable.model.ioUnits.typeadapters.DiscreteInputTypeAdapter;
import org.rldev.iotable.model.ioUnits.typeadapters.DiscreteOutputTypeAdapter;
import org.rldev.iotable.normalize.validation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IoTableServiceImpl implements IoTableService {

    @Autowired private IoUnitValidator<AnalogInput> aiSimpleValidator;
    @Autowired private IoUnitValidator<DiscreteInput> diSimpleValidator;
    @Autowired private IoUnitValidator<AnalogOutput> aoSimpleValidator;
    @Autowired private IoUnitValidator<DiscreteOutput> doSimpleValidator;

    @Override
    public IoTable getFromWorkbook(final IoTableDocument document) {

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(AnalogInput.class, new AnalogInputTypeAdapter())
                .registerTypeAdapter(DiscreteInput.class, new DiscreteInputTypeAdapter())
                .registerTypeAdapter(AnalogOutput.class, new AnalogOutputTypeAdapter())
                .registerTypeAdapter(DiscreteOutput.class, new DiscreteOutputTypeAdapter())
                .create();

        String json = document.getAsJsonString();

        IoTable ioTable = gson.fromJson(json, IoTable.class);

        return ioTable;
    }

    @Override
    public IoTable validate(IoTable ioTable) {

        List<AnalogInput> analogInputs = ioTable.getAnalogInputs();
        List<DiscreteInput> discreteInputs = ioTable.getDiscreteInputs();
        List<AnalogOutput> analogOutputs = ioTable.getAnalogOutputs();
        List<DiscreteOutput> discreteOutputs = ioTable.getDiscreteOutputs();

        aiSimpleValidator.validate(analogInputs);
        diSimpleValidator.validate(discreteInputs);
        aoSimpleValidator.validate(analogOutputs);
        doSimpleValidator.validate(discreteOutputs);

        IoTable validIoTable = new IoTable();

        validIoTable.setAnalogInputs(analogInputs);
        validIoTable.setDiscreteInputs(discreteInputs);
        validIoTable.setAnalogOutputs(analogOutputs);
        validIoTable.setDiscreteOutputs(discreteOutputs);

        return validIoTable;
    }
}
