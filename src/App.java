import java.sql.Connection;
import java.util.Scanner;

import com.jdbc.tarento.ConnectDB;

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
                    System.out.println(name);
                    // int id = sc.nextInt();
                    // db.insertData(con, name,id);
                    break;

                case 2:

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
                        System.out.println("Enter the table name");
                        String tableName= sc.nextLine();
                        System.out.println("Enter the number of Attributes :");
                        int n=sc.nextInt();

                        String[] tableAtri = new String[n];
                        System.out.println("Enter the Attribute seperated by coma's :");

                        tableAtri= sc.next().split(",");

                        for( int i=0;i<tableAtri.length;i++){
                            System.out.println((i+1)+": "+tableAtri[i]);
                        }

                        System.out.println("Select the Primary key from above by inputing corresponding index :");

                        int index= sc.nextInt();

                        String primeKey= tableAtri[index-1];
  

                        db.createTable(con, tableName);


                default:
                    System.out.println("Wrong choice is selected");
                    break;
            }


            System.out.println("Do you want to continue if Yes enter Y/y :");

            ch=sc.nextLine();

        } while (ch=="y" || ch=="Y");

        sc.close();

        
    }

}
