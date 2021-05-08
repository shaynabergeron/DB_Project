import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;

public class Employee {

    private int id;
    private int storeId;
    private int positionId; 
    private String fName; 
    private String lName; 
    private int salary;
    private String gender;
    private String email;
    private String address;
    private String phoneNumber;
    private String zipCode;
    private Connection conn;
    private PreparedStatement ps;

    public Employee(
        int storeId, 
        int positionId,
        String fName, 
        String lName, 
        int salary, 
        String gender,
        String email,
        String address,
        String phoneNumber,
        String zipCode,
        Connection conn
        ){
        
        this.conn = conn;
        this.id = getId();
        this.storeId = storeId;
        this.positionId = positionId;
        this.fName = fName;
        this.lName = lName;
        this.salary = salary;
        this.gender = gender;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.zipCode = zipCode;

        insertToDB();
    }

    public Employee(Connection conn){
        this.conn = conn;
    }

    public Connection getConn(){
        return this.conn;
    }
    
    public int getId(){
        int maxId = 0;
        try{
            Statement state = getConn().createStatement();
            ResultSet result = state.executeQuery("SELECT count(*) from EMPLOYEE");
            while(result.next()){
            maxId = (result.getInt(1));
        }
        }
        catch(Exception e){
            System.out.println(e);
        }

        return maxId += 1;
    }

    public void insertToDB(
        int id, int storeId, int positionID, String fName, String lName, int salary, String gender,
        String email, String address, String phoneNumber, String zipCode
        ){

        try{
            String sql = "INSERT INTO EMPLOYEE VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            this.ps = this.conn.prepareStatement(sql);
            this.ps.setInt(1, id);
            this.ps.setInt(2, storeId);
            this.ps.setInt(3, positionId);
            this.ps.setString(4, fName);
            this.ps.setString(5, lName);
            this.ps.setInt(6, salary);
            this.ps.setString(7, gender);
            this.ps.setString(8, email);
            this.ps.setString(9, address);
            this.ps.setString(10, phoneNumber);
            this.ps.setString(11, zipCode);

            this.ps.execute();
            this.ps.close();
            
            System.out.println("\n***Employee Inserted\n");
            
        }catch(Exception e){
			System.out.println(e);
		}
    }

    public void insertToDB(){
        try{
            String sql = "INSERT INTO EMPLOYEE VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            this.ps = this.conn.prepareStatement(sql);
            this.ps.setInt(1, this.id);
            this.ps.setInt(2, this.storeId);
            this.ps.setInt(3, this.positionId);
            this.ps.setString(4, this.fName);
            this.ps.setString(5, this.lName);
            this.ps.setInt(6, this.salary);
            this.ps.setString(7, this.gender);
            this.ps.setString(8, this.email);
            this.ps.setString(9, this.address);
            this.ps.setString(10, this.phoneNumber);
            this.ps.setString(11, this.zipCode);

            this.ps.execute();
            this.ps.close();
            
            System.out.println("\n***Employee Inserted\n");
            
        }catch(Exception e){
			System.out.println(e);
		}
    }

    public void deleteFromDB(){
        try{

            String sql = "DELETE FROM EMPLOYEE WHERE E_EmployeeID = ?";
            this.ps = this.conn.prepareStatement(sql);
            this.ps.setInt(1, this.id);
            this.ps.execute();
            this.ps.close();

            System.out.println("\n***Employee Deleted\n");

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void deleteFromDB(int id){
        try{

            String sql = "DELETE FROM EMPLOYEE WHERE E_EmployeeID = ?";
            this.ps = this.conn.prepareStatement(sql);
            this.ps.setInt(1, id);
            this.ps.execute();
            this.ps.close();

            System.out.println("\n***Employee Deleted\n");

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public ResultSet getAllEmployees(){

        ResultSet result = null;

        try{
            String sql = "SELECT * FROM EMPLOYEE";
            this.ps = this.conn.prepareStatement(sql);
            result = ps.executeQuery();

        }catch( Exception e){
            e.printStackTrace();
        }

        return result;
    }

    public void closePs(){

        try{
            this.ps.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}