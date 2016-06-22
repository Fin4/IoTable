package org.iotable.core.normalize.validation;


import org.iotable.core.model.ioUnits.IoUnit;

import java.util.List;

@FunctionalInterface
public interface IoUnitValidator<T extends IoUnit> {

        List<T> validate(List<T> ioUnits);
}
