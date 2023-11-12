package model;

import java.io.Serializable;

public enum SelectX implements Serializable {
    VALUEM3(-3.0),
    VALUEM2(-2.0),
    VALUEM1(-1.0),
    VALUE0(0.0),
    VALUEP1(1.0),
    VALUEP2(2.0),
    VALUEP3(3.0),
    VALUEP4(4.0),
    VALUEP5(5.0),
    UNSELECTED(null);

    private Double value;
    SelectX(Double value){
        this.value = value;
    }

    public Double getValue() {
        return value;
    }
}
