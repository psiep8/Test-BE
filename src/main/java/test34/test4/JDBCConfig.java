package test34.test4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;

public class JDBCConfig {
    public static void main(String[] args) throws IOException {

        String url = "jdbc:mysql://localhost:3306/test_BE";
        String user = "root";
        String password = "root";
        String createTable = new String(Files.readAllBytes(Paths.get("src/main/java/test34/test3sql/tableCreation.sql")));
        String insertData = new String(Files.readAllBytes(Paths.get("src/main/java/test34/test3sql/tableProva.sql")));
        String selectData = new String(Files.readAllBytes(Paths.get("src/main/java/test34/test3sql/sqlFunction.sql")));

        try (Connection conn = DriverManager.getConnection(url, user, password); Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(createTable);
            stmt.executeUpdate(insertData);

            ResultSet rs = stmt.executeQuery(selectData);
            while (rs.next()) {
                int id = rs.getInt("id");
                String users = rs.getString("users");
                int years = rs.getInt("years");
                double sales = rs.getDouble("sales");
                System.out.println("id: " + id + ", user: " + users + ", year: " + years + ", sales: " + sales);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
