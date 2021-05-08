import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;

public class Position {

    private int id;
    private String title; 
    private String description; 
    private int payRangeLow;
    private int payRangeHigh;
    private Connection conn;
    private PreparedStatement ps;

    public Position(
        String title, 
        String description, 
        int payRangeLow,
        int payRangeHigh,
        Connection conn
        ){
        
        this.conn = conn;
        this.id = getId();
        this.title = title;
        this.description = description;
        this.payRangeLow = payRangeLow;
        this.payRangeHigh = payRangeHigh;
        
        insertToDB();
    }

    public Position(Connection conn){
        this.conn = conn;
    }

    public int getId(){
        int maxId = 0;
        try{
            Statement state = getConn().createStatement();
            ResultSet result = state.executeQuery("SELECT count(*) from POSITION");
            while(result.next()){
            maxId = (result.getInt(1));
        }
        }
        catch(Exception e){
            System.out.println(e);
        }

        return maxId += 1;
    }

    public Connection getConn(){
        return this.conn;
    }
    public void insertToDB(int id, String title, String description, int payRangeLow, int payRangeHigh){
        try{
            String sql = "INSERT INTO POSITION VALUES(?, ?, ?, ?, ?)";

            this.ps = this.conn.prepareStatement(sql);
            this.ps.setInt(1, id);
            this.ps.setString(2, title);
            this.ps.setString(3, description);
            this.ps.setInt(4, payRangeLow);
            this.ps.setInt(5, payRangeHigh);

            this.ps.execute();
            this.ps.close();
            
            System.out.println("\n***Position Inserted!\n");
            
        }catch(Exception e){
			System.out.println(e);
		}
    }

    public void insertToDB(){
        try{
            String sql = "INSERT INTO POSITION VALUES(?, ?, ?, ?, ?)";

            this.ps = this.conn.prepareStatement(sql);
            this.ps.setInt(1, this.id);
            this.ps.setString(2, this.title);
            this.ps.setString(3, this.description);
            this.ps.setInt(4, this.payRangeLow);
            this.ps.setInt(5, this.payRangeHigh);

            this.ps.execute();
            this.ps.close();
            
            System.out.println("\n***Position Inserted!\n");
            
        }catch(Exception e){
			System.out.println(e);
		}
    }

    public void deleteFromDB(){
        try{

            String sql = "DELETE FROM POSITION WHERE Pos_PositionID = ?";
            this.ps = this.conn.prepareStatement(sql);
            this.ps.setInt(1, this.id);
            this.ps.execute();
            this.ps.close();

            System.out.println("\n***Position Deleted\n");

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void deleteFromDB(int id){
        try{

            String sql = "DELETE FROM POSITION WHERE Pos_PositionID = ?";
            this.ps = this.conn.prepareStatement(sql);
            this.ps.setInt(1, id);
            this.ps.execute();
            this.ps.close();

            System.out.println("\n***Position Deleted\n");

        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public ResultSet getAllPositions(){

        ResultSet result = null;

        try{
            String sql = "SELECT * FROM POSITION";
            this.ps = this.conn.prepareStatement(sql);
            result = ps.executeQuery();

        }catch( Exception e){
            e.printStackTrace();
        }

        return result;
    }

    public ResultSet getAllSkillRequired(int positionId){

        ResultSet result = null;

        try{
            String sql = "SELECT DISTINCT * FROM SKILL_REQUIRED WHERE SR_PositionID = ?";
            this.ps = this.conn.prepareStatement(sql);
            this.ps.setInt(1, positionId);
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