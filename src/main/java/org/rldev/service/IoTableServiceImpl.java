package org.rldev.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.rldev.iotable.document.IoTableDocument;
import org.rldev.iotable.model.IoTable;
import org.rldev.iotable.model.ioUnits.AnalogInput;
import org.rldev.iotable.model.ioUnits.AnalogOutput;
import org.rldev.iotable.model.ioUnits.DigitalInput;
import org.rldev.iotable.model.ioUnits.DigitalOutput;
import org.rldev.iotable.model.ioUnits.typeadapters.AnalogInputTypeAdapter;
import org.rldev.iotable.model.ioUnits.typeadapters.AnalogOutputTypeAdapter;
import org.rldev.iotable.model.ioUnits.typeadapters.DigitalInputTypeAdapter;
import org.rldev.iotable.model.ioUnits.typeadapters.DigitalOutputTypeAdapter;
import org.rldev.iotable.normalize.validation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class IoTableServiceImpl implements IoTableService {

    @Autowired private IoUnitValidator<AnalogInput> aiSimpleValidator;
    @Autowired private IoUnitValidator<DigitalInput> diSimpleValidator;
    @Autowired private IoUnitValidator<AnalogOutput> aoSimpleValidator;
    @Autowired private IoUnitValidator<DigitalOutput> doSimpleValidator;

    @Override
    public IoTable getFromWorkbook(final IoTableDocument document) throws IOException {

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(AnalogInput.class, new AnalogInputTypeAdapter())
                .registerTypeAdapter(DigitalInput.class, new DigitalInputTypeAdapter())
                .registerTypeAdapter(AnalogOutput.class, new AnalogOutputTypeAdapter())
                .registerTypeAdapter(DigitalOutput.class, new DigitalOutputTypeAdapter())
                .create();

        String json = document.getAsJsonString();

        IoTable ioTable = gson.fromJson(json, IoTable.class);

        return ioTable;
    }

    @Override
    public IoTable validate(IoTable ioTable) {

        List<AnalogInput> analogInputs = ioTable.getAnalogInputs();
        List<DigitalInput> digitalInputs = ioTable.getDigitalInputs();
        List<AnalogOutput> analogOutputs = ioTable.getAnalogOutputs();
        List<DigitalOutput> digitalOutputs = ioTable.getDigitalOutputs();

        aiSimpleValidator.validate(analogInputs);
        diSimpleValidator.validate(digitalInputs);
        aoSimpleValidator.validate(analogOutputs);
        doSimpleValidator.validate(digitalOutputs);

        IoTable validIoTable = new IoTable();

        validIoTable.setAnalogInputs(analogInputs);
        validIoTable.setDigitalInputs(digitalInputs);
        validIoTable.setAnalogOutputs(analogOutputs);
        validIoTable.setDigitalOutputs(digitalOutputs);

        return validIoTable;
    }
}
