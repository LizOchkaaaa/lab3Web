package model;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import db.DataBase;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import validator.ValueValidator;

@SessionScoped
public class CheckAreaBeanResult implements Serializable {
    private LinkedList<CheckAreaBean> results;

    private DataBase db;

    @Named(value = "result")
    public LinkedList<CheckAreaBean> getResults() {
        return results;
    }
    public CheckAreaBeanResult() throws SQLException {
        if (results == null){
            results = new LinkedList<CheckAreaBean>();
        }
        db = new DataBase();
        Connection connection = db.connect();
        ResultSet data = db.load(connection);
        while (data.next()){
            results.add(new CheckAreaBean(
                    data.getFloat(2),
                    data.getFloat(3),
                    data.getFloat(4),
                    data.getBoolean(5),
                    data.getDate(6).toLocalDate().atStartOfDay(),
                    data.getLong(7)
            ));
        }
    }
    public void setResults(LinkedList<CheckAreaBean> results) {
        this.results = results;
    }

    public void addNewResult(double x, double y, double r, Type type) {
        ValueValidator validator = new ValueValidator();
        if (validator.validate(x, y, r, type)) {
            try {
                Connection connection = db.connect();
                final CheckAreaBean currentResult = new CheckAreaBean();
                final long startTime = System.nanoTime();
                final boolean result = AreaResult.getResult(x, y, r);
                final long endTime = System.nanoTime();
                final long time = endTime - startTime;
                currentResult.setX(x);
                currentResult.setY(y);
                currentResult.setR(r);
                currentResult.setResultArea(result);
                currentResult.setTime((LocalDateTime.now()));
                currentResult.setTimeScript(time);
                db.add(connection, currentResult);
                FacesContext.getCurrentInstance().getPartialViewContext().getEvalScripts().add("drawPoint(" + x + ", " + y + ", " + r + ", " + result + ");");
                if (results == null) {
                    results = new LinkedList<CheckAreaBean>();
                }
                results.addFirst(currentResult);
            }catch (Exception e){
                System.out.println("Result adding error: " + e.getMessage());
            }
        }
    }

}
