import java.sql.*;

public class _07PrintAllMinionNames {
    private static final String URL = "jdbc:mysql://localhost:3306/minions_db";

    private static final String USERNAME = "root";
    private static final String PASS = "1234";

    public static void main(String[] args) {
        String getMinionsCount = "SELECT COUNT(*) AS count FROM minions";
        int minionsCount = 0;
        String selectMinion = "SELECT name FROM minions WHERE minion_id = ?";
        try (
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASS);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(getMinionsCount);
        ) {
            if (resultSet.next()) {
                minionsCount = resultSet.getInt("count");
            }

            PreparedStatement selectStatement = connection.prepareStatement(selectMinion);
            int startCount = 1;
            int endCount = minionsCount;

            while (startCount <= endCount) {
                selectStatement.setInt(1, startCount);
                ResultSet startResult = selectStatement.executeQuery();
                if (startResult.next()) {
                    System.out.println(startResult.getString("name"));
                }
                startResult.close();

                selectStatement.setInt(1, endCount);
                ResultSet endResult = selectStatement.executeQuery();
                if (endResult.next() && (startCount != endCount)) {
                    System.out.println(endResult.getString("name"));
                }
                endResult.close();

                startCount++;
                endCount--;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
