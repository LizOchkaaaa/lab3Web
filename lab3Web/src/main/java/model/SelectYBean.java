package model;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.Objects;

@Named
@SessionScoped
public class SelectYBean implements Serializable {
    private Double value;

    public Double getValue() {
        return value;
    }

    public void setValue(Double value){
        this.value = value;
    }

    public void validateY(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o == null) {
            FacesMessage message = new FacesMessage("Please, input Y!");
            throw new ValidatorException(message);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SelectYBean beans = (SelectYBean) o;
        return value == beans.value &&
                value == beans.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }

    @Override
    public String toString() {
        return "selectYBean{" +
                "value=" + value +
                '}';
    }
}
