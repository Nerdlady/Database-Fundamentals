import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class _09IncreaseAgeStoredProcedure {
    private static final String URL = "jdbc:mysql://localhost:3306/minions_db";

    private static final String USERNAME = "root";
    private static final String PASS = "1234";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int minionId = Integer.parseInt(reader.readLine());

        String createProcedure =
                "CREATE PROCEDURE usp_get_older(minion_id INT)\n" +
                "BEGIN " +
                "UPDATE minions  AS m " +
                "SET m.age = m.age +1 " +
                "WHERE m.minion_id = minion_id; " +
                "END";

        String callProcedure = "CALL usp_get_older(?)";
        String selectMinion = "SELECT name,age FROM minions WHERE minion_id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASS);
             Statement statement = connection.createStatement();
        ) {
            statement.executeUpdate(createProcedure);

            PreparedStatement callProcedureStatement = connection.prepareStatement(callProcedure);
            callProcedureStatement.setInt(1,minionId);
            callProcedureStatement.executeUpdate();
            callProcedureStatement.close();

            PreparedStatement preparedStatement = connection.prepareStatement(selectMinion);
            preparedStatement.setInt(1, minionId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                System.out.printf("%s %d", resultSet.getString("name"), resultSet.getInt("age"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
