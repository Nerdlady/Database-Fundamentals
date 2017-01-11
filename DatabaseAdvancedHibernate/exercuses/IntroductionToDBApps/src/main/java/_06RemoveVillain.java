import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class _06RemoveVillain {
    private static final String URL = "jdbc:mysql://localhost:3306/minions_db";

    private static final String USERNAME = "root";
    private static final String PASS = "1234";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int villainId = Integer.parseInt(reader.readLine());

        String deleteFromMappingTable = "DELETE FROM minions_villains WHERE villain_id = ?";
        String deleteVillain = "DELETE FROM villains WHERE villain_id = ?";
        String selectVillainName = "SELECT name FROM villains WHERE villain_id = ?";

        try (
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASS);
                PreparedStatement statement = connection.prepareStatement(deleteFromMappingTable);
        ) {
            PreparedStatement selectVillainStatement = connection.prepareStatement(selectVillainName);
            selectVillainStatement.setInt(1, villainId);

            ResultSet villainNameResult = selectVillainStatement.executeQuery();

            String villainName = "";
            if (villainNameResult.next()) {
                villainName = villainNameResult.getString("name");
                villainNameResult.close();

                statement.setInt(1, villainId);

                int minionsReleased = statement.executeUpdate();

                PreparedStatement deleteVillainStatement = connection.prepareStatement(deleteVillain);
                deleteVillainStatement.setInt(1, villainId);
                deleteVillainStatement.executeUpdate();
                deleteVillainStatement.close();

                System.out.printf("%s was deleted%n", villainName);
                System.out.printf("%d minions released", minionsReleased);
            } else {
                System.out.println("No such villain was found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
