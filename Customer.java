import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;

public class Customer {

    private int id;
    private String fName; 
    private String lName; 
    private String phoneNumber;
    private Connection conn;
    private PreparedStatement ps;

    public Customer(
        String fName, 
        String lName, 
        String phoneNumber,
        Connection conn
        ){
        
        this.conn = conn;
        this.id = getId();
        this.fName = fName;
        this.lName = lName;
        this.phoneNumber = phoneNumber;

        insertToDB();
    }

    public Customer(Connection conn){
        this.conn = conn;
    }

    public Connection getConn(){
        return this.conn;
    }

    public int getId(){
        int maxId = 0;
        try{
            Statement state = getConn().createStatement();
            ResultSet result = state.executeQuery("SELECT count(*) from CUSTOMER");
            while(result.next()){
            maxId = (result.getInt(1));
        }
        }
        catch(Exception e){
            System.out.println(e);
        }

        return maxId += 1;
    }

    public void insertToDB(int id, String fName, String lName, String phoneNumber){
        try{
            String sql = "INSERT INTO CUSTOMER VALUES(?, ?, ?, ?)";

            this.ps = this.conn.prepareStatement(sql);
            this.ps.setInt(1, id);
            this.ps.setString(2, fName);
            this.ps.setString(3, lName);
            this.ps.setString(4, phoneNumber);

            this.ps.execute();
            this.ps.close();
            
            System.out.println("\n***Customer Inserted!\n");
            
        }catch(Exception e){
			System.out.println(e);
		}
    }

    public void insertToDB(){
        try{
            String sql = "INSERT INTO CUSTOMER VALUES(?, ?, ?, ?)";

            this.ps = this.conn.prepareStatement(sql);
            this.ps.setInt(1, this.id);
            this.ps.setString(2, this.fName);
            this.ps.setString(3, this.lName);
            this.ps.setString(4, this.phoneNumber);

            this.ps.execute();
            this.ps.close();
            
            System.out.println("\n***Customer Inserted!\n");
            
        }catch(Exception e){
			System.out.println(e);
		}
    }

    public void deleteFromDB(){
        try{

            String sql = "DELETE FROM SALES WHERE Sale_CustomerID = ?";
            this.ps = this.conn.prepareStatement(sql);
            this.ps.setInt(1, this.id);
            this.ps.execute();
            this.ps.close();

            sql = "DELETE FROM CUSTOMER WHERE C_CustomerID = ?";
            this.ps = this.conn.prepareStatement(sql);
            this.ps.setInt(1, this.id);
            this.ps.execute();
            this.ps.close();

            System.out.println("\n***Customer Deleted\n");

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void deleteFromDB(int id){
        try{

            String sql = "DELETE FROM SALES WHERE Sale_CustomerID    = ?";
            this.ps = this.conn.prepareStatement(sql);
            this.ps.setInt(1, id);
            this.ps.execute();
            this.ps.close();

            sql = "DELETE FROM CUSTOMER WHERE C_CustomerID = ?";
            this.ps = this.conn.prepareStatement(sql);
            this.ps.setInt(1, id);
            this.ps.execute();

            this.ps.close();

            System.out.println("\n***Customer Deleted\n");

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public ResultSet getAllCustomers(){

        ResultSet result = null;

        try{
            String sql = "SELECT * FROM CUSTOMER";
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