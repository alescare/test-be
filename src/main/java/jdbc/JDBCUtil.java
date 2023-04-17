package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCUtil {

    public void creaDB() {
        try (Connection con = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/test-be", "root", "root")) {
            try (Statement stmt = con.createStatement()) {
                String tableSql = "CREATE TABLE IF NOT EXISTS vendite"
                        + "(id int PRIMARY KEY AUTO_INCREMENT, utente varchar(15),"
                        + "data_vendita  int, quantita int)";
                stmt.execute(tableSql);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void popolaDB() {
        try (Connection con = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/test-be", "root", "root")) {
            try (Statement stmt = con.createStatement()) {
                String tableSql = "INSERT INTO " + "vendite (utente, data_vendita, quantita) " +
                        "VALUES " +
                        "('A','2021','500'), " +
                        "('B','2021','1000'); " +
                        "('C','2022','900'); " +
                        "('A','2022','1200'); " +
                        "('B','2021','600'); " +
                        "('A','2022','900'); " +
                        "('B','2021','500'); " +
                        "('C','2021','1000'); " +
                        "('C','2022','700'); " +
                        "('B','2021','500');";
                stmt.execute(tableSql);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet eseguiQuery(){
        ResultSet risultato;
        try (Connection con = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/test-be", "root", "root")) {
            try (Statement stmt = con.createStatement()) {
                String tableSql = "SELECT" +
                        " utente," +
                        " YEAR(data_vendita) AS anno," +
                        " SUM(importo) AS tot_vendite," +
                        " (SUM(importo) - LAG(SUM(importo)) OVER (PARTITION BY user ORDER BY YEAR(data_vendita))) AS inc_dec" +
                        " FROM vendite" +
                        " GROUP BY user, YEAR(data_vendita)" +
                        " ORDER BY user, anno";
                risultato = stmt.executeQuery(tableSql);
                return risultato;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
