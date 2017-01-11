import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Arrays;

public class _08IncreaseMinionsAge {
    private static final String URL = "jdbc:mysql://localhost:3306/minions_db";

    private static final String USERNAME = "root";
    private static final String PASS = "1234";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] ids = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        String selectMinionName = "SELECT name FROM minions WHERE minion_id = ?";
        String updateMinion = "UPDATE minions SET age = age + 1, name = ? WHERE minion_id = ?";
        String selectAll = "SELECT name,age FROM minions";

        try (
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASS);

        ) {

            for (int id : ids) {
                PreparedStatement selectNameStatement = connection.prepareStatement(selectMinionName);
                selectNameStatement.setInt(1, id);
                ResultSet minionNameResult = selectNameStatement.executeQuery();
                String currentMinionName = "";
                if (minionNameResult.next()) {
                    currentMinionName = minionNameResult.getString("name");
                }
                minionNameResult.close();
                selectNameStatement.close();
                currentMinionName = toTitleCase(currentMinionName);

                PreparedStatement statement = connection.prepareStatement(updateMinion);
                statement.setString(1,currentMinionName);
                statement.setInt(2,id);
                statement.executeUpdate();
                statement.close();
            }

            Statement selectAllStatement = connection.createStatement();
            ResultSet allResult = selectAllStatement.executeQuery(selectAll);

            while (allResult.next()){
                System.out.printf("%s %d%n",allResult.getString("name"),allResult.getInt("age"));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static String toTitleCase(String name){
        StringBuilder builder = new StringBuilder();
        char[] letters = name.toCharArray();
        boolean isNextTitleCase = true;

        for (char letter : letters) {
            if (Character.isSpaceChar(letter)){
                isNextTitleCase = true;
            } else if (isNextTitleCase){
                letter = Character.toTitleCase(letter);
                isNextTitleCase = false;
            }

            builder.append(letter);
        }

        return builder.toString();
    }
}
