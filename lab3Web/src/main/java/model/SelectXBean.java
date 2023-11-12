package model;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.Objects;

@Named
@SessionScoped
public class SelectXBean implements Serializable {
    private Double value;

    public Double getValue() {
        return value;
    }

    public void setValue(Double value){
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SelectXBean beans = (SelectXBean) o;
        return value == beans.value &&
                value == beans.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }

    @Override
    public String toString() {
        return "selectXBean{" +
                "value=" + value +
                '}';
    }
}
