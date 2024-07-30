import java.sql.Connection;
import java.util.Scanner;

import com.jdbc.tarento.ConnectDB;
import com.jdbc.tarento.GetAttribute;
import com.jdbc.tarento.GetTable;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        String username = "postgres";
        String password = "admin";
        ConnectDB db = new ConnectDB();
        Connection con = db.connectToDb(username, password);

        String ch;
        System.out.println("Let's interact with the database");

        do {
            System.out.println("1: Insert data into table ");
            System.out.println("2: Delete Data");
            System.out.println("3: Update data");
            System.out.println("4: Display table");
            System.out.println("5: Create a table");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter the employee name: ");
                    String name = sc.nextLine();
                    System.out.println("Enter the employee id: ");
                    int id = sc.nextInt();
                    db.insertData(con, name, id);
                    break;

                case 2:
                    GetTable tables = new GetTable();
                    String tableName = tables.getTable(con, sc);
                    System.out.println("Selected table :" + tableName);
                    GetAttribute tableAttributes = new GetAttribute();

                    ArrayList<String>  tableAttri = new ArrayList<>();
                    tableAttri = tableAttributes.getAttribute(con, tableName);


                    System.out.println(tableAttri);

                    System.out.print("Enter the table "+ tableAttri.get(0)+ " :");

                    String userInput = sc.nextLine();
                    String query = "DELETE FROM " + tableName + " WHERE " + tableAttri.get(0) + " = " + userInput + ";";

                    System.out.println(query);

                    db.deleteData(con,tableName,query);







                    break;

                case 3:
                    // Your code for updating data goes here
                    break;

                case 4:
                    System.out.println("Enter the emp_id to show result: ");
                    int num = sc.nextInt();
                    String sql = "SELECT emp_name FROM employee WHERE emp_id=" + num + ";";
                    try {
                        String emp_name = db.getResult(sql, con, "emp_name");
                        System.out.println(emp_name);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;

                case 5:
                    System.out.println("Enter the table name: ");
                    tableName = sc.nextLine();
                    System.out.println("Enter the number of attributes: ");
                    int n = sc.nextInt();
                    sc.nextLine();

                    String[] tableAtri = new String[n];
                    for (int i = 0; i < n; i++) {
                        System.out.println("Enter attribute " + (i + 1) + " (name and datatype, e.g., 'id INT'): ");
                        tableAtri[i] = sc.nextLine();
                    }

                    System.out.println("Attributes:");
                    for (int i = 0; i < tableAtri.length; i++) {
                        System.out.println((i + 1) + ": " + tableAtri[i]);
                    }

                    System.out.println("Select the primary key from above by entering the corresponding index: ");
                    int indexPk = sc.nextInt();
                    sc.nextLine();

                    String primeKey = tableAtri[indexPk - 1].split(" ")[0]; // assuming attribute is in the form 'name
                                                                            // datatype'
                    String createTableSQL = "CREATE TABLE " + tableName + " (";
                    for (int i = 0; i < tableAtri.length; i++) {
                        createTableSQL += tableAtri[i];
                        if (i < tableAtri.length - 1) {
                            createTableSQL += ", ";
                        }
                    }
                    createTableSQL += ", PRIMARY KEY (" + primeKey + "));";

                    System.out.println(createTableSQL);

                    db.createTable(con, createTableSQL);
                    break;

                default:
                    System.out.println("Wrong choice selected.");
                    break;
            }

            System.out.println("Do you want to continue? If yes, enter Y/y: ");
            ch = sc.next();

        } while (ch.equalsIgnoreCase("y"));

        sc.close();
    }
}
