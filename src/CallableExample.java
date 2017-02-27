import java.sql.*;

public class CallableExample {
    public static void main(String[] args) {
        Connection connection = null;

        try {
            connection = SelectExample.getConnection();

            Statement statement = connection.createStatement();
            String sql = "SELECT first_name_child, last_name_child FROM children " +
                    "WHERE id_child = 1";
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            CallableStatement stmt = connection.prepareCall("{?= call GETFULLNAME(?,?)}");
            stmt.setString(2, resultSet.getString(1));
            stmt.setString(3, resultSet.getString(2));
            stmt.registerOutParameter(1, Types.CHAR);
            stmt.execute();
            System.out.println(stmt.getString(1));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
