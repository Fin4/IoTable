package org.rldev.iotable.validators.IoUnitsValidator;

import org.rldev.iotable.model.IoUnit;

import java.util.List;
import java.util.Set;

public interface IoUnitValidator {

    List<? extends IoUnit> validate(List<? extends IoUnit> ioUnits);
}
