package org.rldev.iotable.validators.IoUnitsValidator;

import org.rldev.iotable.model.ioUnits.IoUnit;

import java.util.List;

public interface IoUnitValidator {

    List<? extends IoUnit> validate(List<? extends IoUnit> ioUnits);
}
