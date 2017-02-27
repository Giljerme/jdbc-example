import java.sql.*;

public class SelectExample {
    public static void main(String[] args) {


        try(Connection connection = SelectExample.getConnection()) {

            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM rooms";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                System.out.format("%2d|%15s|%3d|%15s|%5b|%15s|%15s|%6s|%6s\n",
                        resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getInt(3), resultSet.getString(4),
                        resultSet.getBoolean(5), resultSet.getString(6),
                        resultSet.getString(7), resultSet.getString(8),
                        resultSet.getString("working_start_hour"));
            }
            System.out.println();

            ResultSetMetaData metaData = resultSet.getMetaData();
            System.out.println(metaData.getTableName(1));
            int columnCount = metaData.getColumnCount();
            System.out.println(columnCount);
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(metaData.getColumnName(i) + " - " +
                                        metaData.getColumnTypeName(i));
                System.out.println();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        String databaseName = "jdbc:mysql://localhost:3306/kids?useSSL=false";
        String user = "root";
        String password = "root";

        return DriverManager.getConnection(databaseName, user, password);
    }
}
