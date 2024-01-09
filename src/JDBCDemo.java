import java.sql.*;
public class JDBCDemo{
    public static void main(String[] args) throws Exception {
        sp3();

    }

    //read records
    public static void readRecords() throws Exception {
        String url = "jdbc:mysql://localhost:3307/jdbc_db";
        String userName = "root";
        String passWord = "root";

        String query = "select * from employee";

        Connection con = DriverManager.getConnection(url, userName, passWord);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

        while(rs.next()){
            System.out.println("Id is " + rs.getInt(1));
            System.out.println("Name is " + rs.getString(2));
            System.out.println("Salary is " + rs.getInt(3));
        }
        con.close();
    }
    //insert records
    public static void insertRecords() throws Exception {
        String url = "jdbc:mysql://localhost:3307/jdbc_db";
        String userName = "root";
        String passWord = "root";

        String query = "insert into employee values (4,'Priya',25000)";

        Connection con = DriverManager.getConnection(url, userName, passWord);
        Statement st = con.createStatement();
        int rows = st.executeUpdate(query);

        System.out.println("Number of rows affected: "+ rows);
        con.close();
    }
// insert data using variables
    public static void insertVar() throws Exception {
        String url = "jdbc:mysql://localhost:3307/jdbc_db";
        String userName = "root";
        String passWord = "root";

        int id=5;
        String name = "Vimala";
        int salary= 30000;

       //String query = "insert into employee values (4,'Priya',25000);"
        String query= "insert into employee values (" + id + ",'" +name + "'," +salary + ");";
        Connection con = DriverManager.getConnection(url, userName, passWord);
        Statement st = con.createStatement();
        int rows = st.executeUpdate(query);

        System.out.println("Number of rows affected: "+ rows);
        con.close();
    }

    // insert with prepared statement
    public static void insertUsingPst() throws Exception {
        String url = "jdbc:mysql://localhost:3307/jdbc_db";
        String userName = "root";
        String passWord = "root";

        int id=7;
        String name = "Renu";
        int salary= 30000;

        //String query = "insert into employee values (4,'Priya',25000);"
        String query= "insert into employee values (?,?,?);";

        Connection con = DriverManager.getConnection(url, userName, passWord);


        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1,id);
        pst.setString(2,name);
        pst.setInt(3,salary);
        int rows = pst.executeUpdate();

        System.out.println("Number of rows affected: "+ rows);

        con.close();
    }

    //delete
    public static void delete() throws Exception {
        String url = "jdbc:mysql://localhost:3307/jdbc_db";
        String userName = "root";
        String passWord = "root";

        //String query = "insert into employee values (4,'Priya',25000);"

        int id = 6;
        String query= "delete from employee where emp_id= " + id;

        Connection con = DriverManager.getConnection(url, userName, passWord);
        Statement st = con.createStatement();
        int rows = st.executeUpdate(query);

        System.out.println("Number of rows affected: "+ rows);

        con.close();
    }

    //update
    public static void update() throws Exception {
        String url = "jdbc:mysql://localhost:3307/jdbc_db";
        String userName = "root";
        String passWord = "root";

        //String query = "insert into employee values (4,'Priya',25000);"
        String query= "update employee set salary = 50000 where emp_id=1";

        Connection con = DriverManager.getConnection(url, userName, passWord);
        Statement st = con.createStatement();
        int rows = st.executeUpdate(query);

        System.out.println("Number of rows affected: "+ rows);

        con.close();
    }

    //Types of statement
    // 1. Normal ststement (select, read, update, delete)
    // 2. Prepared ststement ((select, read, update, delete))
    // 3. Callable ststement (to call stored procedure)

    // calling simple stored procedure
    public static void sp() throws Exception{
        String url = "jdbc:mysql://localhost:3307/jdbc_db";
        String userName = "root";
        String passWord = "root";

        Connection con = DriverManager.getConnection(url, userName, passWord);
        CallableStatement cst = con.prepareCall("{call GetEmp()}");
        ResultSet rs = cst.executeQuery();

        while(rs.next()){
            System.out.println("Id is " + rs.getInt(1));
            System.out.println("Name is " + rs.getString(2));
            System.out.println("Salary is " + rs.getInt(3));
        }

        con.close();
    }

    // calling stored procedure with input parameter
    public static void sp2() throws Exception {
        String url = "jdbc:mysql://localhost:3307/jdbc_db";
        String userName = "root";
        String passWord = "root";

        int id = 3;

        Connection con = DriverManager.getConnection(url, userName, passWord);
        CallableStatement cst = con.prepareCall("{call GetEmpById(?)}");
        cst.setInt(1, id);
        ResultSet rs = cst.executeQuery();

        while (rs.next()) {
            System.out.println("Id is " + rs.getInt(1));
            System.out.println("Name is " + rs.getString(2));
            System.out.println("Salary is " + rs.getInt(3));
        }

        con.close();
    }
    //calling stored procedure with in and out parameter
    public static void sp3() throws Exception {
        String url = "jdbc:mysql://localhost:3307/jdbc_db";
        String userName = "root";
        String passWord = "root";

        int id = 3;

        Connection con = DriverManager.getConnection(url, userName, passWord);
        CallableStatement cst = con.prepareCall("{call GetNameById(?,?)}");
        cst.setInt(1, id);
        cst.registerOutParameter(2,Types.VARCHAR);

        cst.executeUpdate();
        System.out.println(cst.getString(2));


        con.close();
    }





}

