import java.sql.*;

public class UpdateExample {
    public static void main(String[] args) throws SQLException {

            Connection connection = null;
            try {



                connection = SelectExample.getConnection();
                connection.setAutoCommit(false);
                Statement statement = connection.createStatement();
                int countUpdatedRows = statement.executeUpdate(
                        "UPDATE rooms SET active = 0 WHERE capacity_room < 10");

                //System.out.println(countUpdatedRows + "\n");

                DatabaseMetaData metaData = connection.getMetaData();
                System.out.println(metaData.getDriverName());
                System.out.println(metaData.getDriverVersion());
                System.out.println(metaData.getUserName());
                String[] array = {"TABLE"};
                ResultSet resultSet = metaData.getTables(null, null, null, array);
                while (resultSet.next()) {
                    System.out.println(resultSet.getString(3));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                connection.close();
            }
        }

}
