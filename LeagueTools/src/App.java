import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class App {
    public static void main(String[] args) throws Exception {


        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "!dieÄrzte3");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from people");

            while (resultSet.next()) {
                System.out.println(resultSet.getString("firstname"));
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
