package coursehunter;

import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcRunner {

    public static void main(String[] args) throws SQLException {

//        for (int i = 1; i < 24; i++) {
//            String q = String.valueOf(i);
//            System.out.print(q + " : ");
//            getDataById(q);
//        }
        var result = getDataById("2 OR 1 = 1");
        System.out.println(result);

    }

    private static List<String> getDataById(String id) throws SQLException {
        String sql = """
                SELECT id, data 
                from info
                where id = %s
                """.formatted(id);
        List<String> resultData = new ArrayList<>();
        List<Integer> resultId = new ArrayList<>();
        try(var connection = ConnectionManager.open();
            var statement = connection.createStatement()) {
            var resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                resultData.add(resultSet.getObject("data", String.class));
                resultId.add(resultSet.getObject("id", Integer.class));
            }
        }
        System.out.print(resultData + " : ");
        System.out.println(resultId);
        return resultData;
    }
}
