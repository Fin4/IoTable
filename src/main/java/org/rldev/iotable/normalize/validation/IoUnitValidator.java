package org.rldev.iotable.normalize.validation;


import org.rldev.iotable.model.ioUnits.IoUnit;

import java.util.List;

@FunctionalInterface
public interface IoUnitValidator<T extends IoUnit> {

        List<T> validate(List<T> ioUnits);
}
