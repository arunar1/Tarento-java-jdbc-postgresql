import java.sql.Connection;

import com.jdbc.tarento.ConnectDB;

public class App {
    public static void main(String[] args) throws Exception {
        String username = "postgres";
        String password = "admin";
        ConnectDB db = new ConnectDB();
        Connection con=db.connectToDb(username, password);

        //Getting result
        String sql ="select emp_name from employee where emp_id=105;";
        String name=db.getResult(sql,con,"Comapany");
        System.out.println(name);

        //Inserting

        // db.insertData(con, "arun",105);
    }

}
