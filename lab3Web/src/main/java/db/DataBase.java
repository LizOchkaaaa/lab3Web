package db;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import model.CheckAreaBean;
import java.sql.*;
import java.util.Properties;

public class DataBase {
    private String DB_BASE;
    private String DB_NAME;
    private int DB_PORT;
    private String DB_HOST;
    private String SV_LOGIN;
    private String SV_PASS;
    private String SV_PASS2;
    private String SV_ADDR;
    private int SV_PORT;
    private int SSH_PORT;
    private int FORWARDING_PORT;
    public DataBase()  {
        try {
            this.DB_PORT = Integer.parseInt(System.getenv("DB_PORT"));//5432
            this.DB_HOST = System.getenv("DB_HOST");// pg
            this.DB_BASE = System.getenv("DB_BASE");// jdbc:postgresql://
            this.DB_NAME= System.getenv("DB_NAME");// studs

            this.SV_LOGIN = System.getenv("SV_LOGIN");// helios login
            this.SV_PASS = System.getenv("SV_PASS"); // pass helios
            this.SV_PASS2 = System.getenv("SV_PASS2"); // pass .pgpass
            this.SV_PORT = Integer.parseInt(System.getenv("SV_PORT")); //server port
            this.SV_ADDR = System.getenv("SV_ADDR"); //helios.se.ifmo.ru

            this.SSH_PORT = Integer.parseInt(System.getenv("SSH_PORT")); // 2222
            this.FORWARDING_PORT = Integer.parseInt(System.getenv("FORWARDING_PORT")); //9191

            Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            JSch jsch = new JSch();
            Session session = jsch.getSession(SV_LOGIN, SV_ADDR, SSH_PORT);
            session.setPassword(SV_PASS);
            session.setConfig(config);
            session.connect();
            session.setPortForwardingL(FORWARDING_PORT, DB_HOST, DB_PORT);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public Connection connect() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(DB_BASE + "localhost:" + FORWARDING_PORT + "/" + DB_NAME, SV_LOGIN, SV_PASS2);
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL Driver not found");
        } catch (SQLException e) {
            System.err.println("Connection to database failed");
        }
        return null;
    }
    public ResultSet load(Connection db) throws SQLException {
    PreparedStatement pstmt = db.prepareStatement("SELECT * from resultTable");
    return pstmt.executeQuery();
    }
    public void add(Connection db, CheckAreaBean currentResult) throws SQLException {
        PreparedStatement pstmt = db.prepareStatement("INSERT INTO resultTable(x, y, r, resultArea, time, timeScript) VALUES (?, ?, ?, ?, ?, ?)");
        pstmt.setFloat(1, currentResult.getX().floatValue());
        pstmt.setFloat(2, currentResult.getY().floatValue());
        pstmt.setFloat(3,currentResult.getR().floatValue());
        pstmt.setBoolean(4, currentResult.isResultArea());
        pstmt.setDate(5, Date.valueOf(currentResult.getTime().toLocalDate()));
        pstmt.setLong(6, currentResult.getTimeScript());
        pstmt.executeUpdate();
    }
}
