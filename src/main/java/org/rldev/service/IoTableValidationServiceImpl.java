package org.rldev.service;

import org.rldev.iotable.model.IoTable;
import org.rldev.iotable.model.ioUnits.AnalogInput;
import org.rldev.iotable.model.ioUnits.AnalogOutput;
import org.rldev.iotable.model.ioUnits.DigitalInput;
import org.rldev.iotable.model.ioUnits.DigitalOutput;
import org.rldev.iotable.validators.IoUnitsValidator.AiSimpleValidator;
import org.rldev.iotable.validators.IoUnitsValidator.IoUnitSimpleValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IoTableValidationServiceImpl implements IoTableValidationService {

    @Override
    public IoTable validate(IoTable ioTable) {

        List<AnalogInput> analogInputs = ioTable.getAnalogInputs();
        List<DigitalInput> digitalInputs = ioTable.getDigitalInputs();
        List<AnalogOutput> analogOutputs = ioTable.getAnalogOutputs();
        List<DigitalOutput> digitalOutputs = ioTable.getDigitalOutputs();

        new IoUnitSimpleValidator(new AiSimpleValidator()).validate(analogInputs);
        new IoUnitSimpleValidator().validate(digitalInputs);
        new IoUnitSimpleValidator().validate(analogOutputs);
        new IoUnitSimpleValidator().validate(digitalOutputs);

        IoTable validIoTable = new IoTable();

        validIoTable.setAnalogInputs(analogInputs);
        validIoTable.setDigitalInputs(digitalInputs);
        validIoTable.setAnalogOutputs(analogOutputs);
        validIoTable.setDigitalOutputs(digitalOutputs);

        return validIoTable;
    }
}
