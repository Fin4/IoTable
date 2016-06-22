package org.iotable.enterprise.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.iotable.core.document.IoTableDocument;
import org.iotable.core.model.IoTable;
import org.iotable.core.model.ioUnits.AnalogInput;
import org.iotable.core.model.ioUnits.AnalogOutput;
import org.iotable.core.model.ioUnits.DiscreteInput;
import org.iotable.core.model.ioUnits.DiscreteOutput;
import org.iotable.core.model.ioUnits.typeadapters.AnalogInputTypeAdapter;
import org.iotable.core.model.ioUnits.typeadapters.AnalogOutputTypeAdapter;
import org.iotable.core.model.ioUnits.typeadapters.DiscreteInputTypeAdapter;
import org.iotable.core.model.ioUnits.typeadapters.DiscreteOutputTypeAdapter;
import org.iotable.core.normalize.validation.IoUnitValidator;

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

        return gson.fromJson(json, IoTable.class);
    }

    @Override
    public IoTable validate(IoTable ioTable) {

        List<AnalogInput> analogInputs = ioTable.getAnalogInputs();
        List<DiscreteInput> discreteInputs = ioTable.getDiscreteInputs();
        List<AnalogOutput> analogOutputs = ioTable.getAnalogOutputs();
        List<DiscreteOutput> discreteOutputs = ioTable.getDiscreteOutputs();

        IoTable validIoTable = new IoTable();

        validIoTable.setAnalogInputs(aiSimpleValidator.validate(analogInputs));
        validIoTable.setDiscreteInputs(diSimpleValidator.validate(discreteInputs));
        validIoTable.setAnalogOutputs(aoSimpleValidator.validate(analogOutputs));
        validIoTable.setDiscreteOutputs(doSimpleValidator.validate(discreteOutputs));

        return validIoTable;
    }
}
