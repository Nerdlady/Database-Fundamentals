import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class _03GetMinionNames {
    private static final String URL = "jdbc:mysql://localhost:3306/minions_db";

    private static final String USERNAME = "root";
    private static final String PASS = "1234";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int villainId = Integer.parseInt(reader.readLine());

        String selectMinions = "SELECT m.name,m.age FROM minions AS m \n" +
                "                INNER JOIN minions_villains AS mv\n" +
                "                ON mv.minion_id = m.minion_id\n" +
                "                WHERE mv.villain_id = ?";


        String selectVillain = "SELECT name FROM villains WHERE villain_id = ?";
        try (
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASS);
                PreparedStatement statement = connection.prepareStatement(selectMinions);
               PreparedStatement villainStatement = connection.prepareStatement(selectVillain);
        ) {
            statement.setInt(1,villainId);
            villainStatement.setInt(1,villainId);

            ResultSet villainResultSet = villainStatement.executeQuery();
            if (villainResultSet.next()){
                System.out.printf("Villain name: %s %n",villainResultSet.getString("name"));
            }

            ResultSet minionResultSet = statement.executeQuery();
            while (minionResultSet.next()){
                System.out.println(String.format("%s %d",minionResultSet.getString("name"),minionResultSet.getInt("age")));
            }
            minionResultSet.close();
            villainResultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
