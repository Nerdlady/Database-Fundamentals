import java.sql.*;

public class _02GetVillainsNames {
    private static final String URL = "jdbc:mysql://localhost:3306/minions_db";

    private static final String USERNAME = "root";
    private static final String PASS = "1234";

    public static void main(String[] args) {
        String selectVillains = "SELECT v.name,COUNT(mv.villain_id) AS 'count' FROM villains AS v " +
                "INNER JOIN minions_villains AS mv " +
                "ON v.villain_id = mv.villain_id " +
                "GROUP BY v.name " +
                "HAVING COUNT(mv.villain_id) > 3 " +
                "ORDER BY count DESC";
        try (
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASS);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(selectVillains)
        ) {
            while (resultSet.next()){
                System.out.println(String.format("%s %d",resultSet.getString("name"),resultSet.getInt("count")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
