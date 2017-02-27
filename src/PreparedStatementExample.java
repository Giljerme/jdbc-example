import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PreparedStatementExample {
    public static void main(String[] args) {
        Connection connection = null;

        try {
            connection = SelectExample.getConnection();
            String sql = "INSERT INTO children VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql);
            preparedStatement.setInt(1, 15);
            preparedStatement.setString(2, "some comment");
            preparedStatement.setDate(3, new Date(112, 10, 10));
            preparedStatement.setString(4, "Petro");
            preparedStatement.setInt(5, 0);
            FileInputStream fin = new FileInputStream("d:\\image.jpg");
            preparedStatement.setBinaryStream(6, fin, fin.available());
            preparedStatement.setInt(7, 1);
            preparedStatement.setString(8, "Petrov");
            preparedStatement.setInt(9, 9);

            System.out.println(preparedStatement.executeUpdate());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
