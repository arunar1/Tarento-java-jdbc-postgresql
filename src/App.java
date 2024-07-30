import java.sql.Connection;
import java.util.Scanner;
import java.util.ArrayList;

import com.jdbc.tarento.ConnectDB;
import com.jdbc.tarento.GetTable;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        String username = "postgres";
        String password = "admin";
        ConnectDB db = new ConnectDB();
        Connection con = db.connectToDb(username, password);

        String ch;
        System.out.println("Let's Intract with the database ");

        // do while loop

        do {
            System.out.println("1: Insert data into table ");
            System.out.println("2: Delete data from table ");
            System.out.println("3: Upadate data");
            System.out.println("4: Display table");
            System.out.println("5: create a table");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter the employee name:");
                    String name = sc.nextLine();
                    System.out.println("Enter the employee id :");
                    int id = sc.nextInt();
                    db.insertData(con, name,id);
                    break;

                case 2:
                    GetTable tables = new GetTable();
                    ArrayList<String> tableNames = tables.getTable(con);
                    System.out.println("Tables in the database:");
                    for (int i = 0; i < tableNames.size(); i++) {
                        System.out.println(i+1 + " : " + tableNames.get(i)); 
                    }
                    System.out.print("Enter the table name index: ");
                    int index = sc.nextInt();
                    sc.nextLine();
                    String selectedTable = tableNames.get(index-1); 
                    System.out.println("Selected Table : "+selectedTable);
                    break;

                case 3:

                    break;

                case 4:
                    int num;
                    System.out.println("Enter the emp_id to show result");
                    num=sc.nextInt();
                    String sql = "select emp_name from employee where emp_id="+num+";";
                    try {
                        String emp_name = db.getResult(sql, con, "Comapany");
                        System.out.println(emp_name);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;

                    case 5:
                        System.out.println("Enter the table name: ");
                        String tableName = sc.nextLine();
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
                        index = sc.nextInt();
                        sc.nextLine(); 

                        String primeKey = tableAtri[index - 1].split(" ")[0]; 
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
                    System.out.println("Wrong choice is selected");
                    break;
            }


            System.out.println("Do you want to continue if Yes enter Y/y :");

            ch=sc.next();

        } while (ch.equalsIgnoreCase("y"));

        sc.close();

        
    }

}
