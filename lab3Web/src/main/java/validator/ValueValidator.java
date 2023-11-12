package validator;

import model.Type;

public class ValueValidator {
    private final double[] xValues = {-3, -2, -1, 0, 1, 2, 3, 4, 5};

    public boolean validate(double x, double y, double r, Type type){
        if (type == Type.SUBMIT) {
            return validateSubmit(x, y, r);
        }
        else if (type == Type.CLICK){
            return validateClick(x, y, r);
        }
        return false;
    }
    private boolean validateClick(double x, double y, double r){
        System.out.println("validateR: "+validateClickR(r));
        System.out.println("validateX: "+validateClickX(x, r));
        System.out.println("validateY: "+validateClickY(y, r));
        System.out.println("x " + x);
        System.out.println("y " + y);
        System.out.println("r " + r);
        return validateClickR(r) && validateClickX(x, r) && validateClickY(y, r);
    }

    private boolean validateSubmit(double x, double y, double r){
        return validateSubmitX(x) && validateSubmitY(y) && validateSubmitR(r);
    }

    private boolean validateSubmitX(double x){
        for(double value : xValues){
            if(value == x){
                return true;
            }
        }
        return false;
    }
    private boolean validateSubmitY(double y){
        return -3 <= y && y <= 5;
    }

    private boolean validateSubmitR(double r){
        return 1 <= r && r <= 3 && (r % 0.5) % 1  == 0;
    }
    private boolean validateClickX(double x, double r){
        return -1.25*r <= x && x <= 1.25*r;
    }

    private boolean validateClickY(double y, double r){
        return -1.25*r <= y && y <= 1.25*r;
    }

    private boolean validateClickR(double r){
        return validateSubmitR(r);
    }
}
