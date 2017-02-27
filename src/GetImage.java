import java.sql.*;
import java.io.*;

public class GetImage {
    public static void main(String[] args) {
        Connection connection = null;

        try {
            connection = SelectExample.getConnection();
            String sql =
                    "SELECT profile_image FROM children WHERE profile_image IS NOT NULL";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            int i = 0;
            while (resultSet.next()) {
                Blob b = resultSet.getBlob(1);
                FileOutputStream fout = new FileOutputStream("d:\\images\\" +
                                                                i + ".jpg");
                byte barr[]=b.getBytes(1,(int)b.length());
                i++;
                fout.write(barr);
                fout.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
