package model;
import jakarta.enterprise.context.SessionScoped;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Table(name = "resultTable")
@SessionScoped
public class CheckAreaBean implements Serializable {
    private Long id;
    private Double x;
    private Double y;
    private Double r;
    private boolean resultArea;
    private LocalDateTime time;
    private long timeScript;

    public CheckAreaBean() {

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getX() {
        return round(BigDecimal.valueOf(x));
    }
    public CheckAreaBean(double x, double y, double r, boolean resultArea, LocalDateTime time, long timeScript){
        this.x = x;
        this.y = y;
        this. r = r;
        this.resultArea = resultArea;
        this.time = time;
        this. timeScript = timeScript;
    }

    private BigDecimal round(BigDecimal value) {
        if(value != null){
            return value.setScale(3 , RoundingMode.HALF_UP);
        }
        return null;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public BigDecimal getY() {
        return round(BigDecimal.valueOf(y));
    }
    public void setY(Double y) {
        this.y = y;
    }

    public Double getR() {
        return r;
    }
    public void setR(Double r) {
        this.r = r;
    }

    public boolean isResultArea() {
        return resultArea;
    }

    public void setResultArea(boolean resultArea) {
        this.resultArea = resultArea;
    }

    public long getTimeScript() {
        return timeScript;
    }

    public void setTimeScript(long timeScript) {
        this.timeScript = timeScript;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

}
