import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class _05ChangeTownNamesCasing {
    private static final String URL = "jdbc:mysql://localhost:3306/minions_db";

    private static final String USERNAME = "root";
    private static final String PASS = "1234";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String countryName = reader.readLine();

        String selectTownsQuery = "SELECT town_id,name FROM towns WHERE country = ?";
        String updateTownQuery = "UPDATE towns SET name = ? WHERE town_id = ?";
        List<String> townsAffected = new ArrayList<>();

        try (
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASS);
                PreparedStatement statement = connection.prepareStatement(selectTownsQuery);
        ) {
            statement.setString(1, countryName);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    int townId = resultSet.getInt("town_id");
                    String townName = resultSet.getString("name");
                    PreparedStatement updateStatement = connection.prepareStatement(updateTownQuery);
                    updateStatement.setString(1, townName.toUpperCase());
                    updateStatement.setInt(2, townId);
                    updateStatement.executeUpdate();
                    townsAffected.add(townName.toUpperCase());
                }

                System.out.printf("%d town names were affected.%n", townsAffected.size());
                System.out.println(townsAffected.toString());
            } else {
                System.out.println("No town names were affected.");
            }

            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
