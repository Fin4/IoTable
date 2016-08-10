package org.iotable.core.mechanisms;


import org.iotable.core.model.mechanisms.Mechanism;

import java.util.List;

public interface MechanismMapper {

    String map(String template, Mechanism mechanism);
}
