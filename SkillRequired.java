import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;


public class SkillRequired {

    private int positionId;
    private int skillCode; 
    private Connection conn;
    private PreparedStatement ps;

    public SkillRequired(
        int positionId, 
        int skillCode,
        Connection conn
        ){
        
        this.positionId = positionId;
        this.skillCode = skillCode;

        this.conn = conn;

        insertToDB();
    }

    public SkillRequired(Connection conn){
        this.conn = conn;
    }

    public Connection getConn(){
        return this.conn;
    }

    public void insertToDB(int positionId, int skillCode){
        try{
            String sql = "INSERT INTO SKILL_REQUIRED VALUES(?, ?)";

            this.ps = this.conn.prepareStatement(sql);
            this.ps.setInt(1, positionId);
            this.ps.setInt(2, skillCode);

            this.ps.execute();
            this.ps.close();
            
            System.out.printf("\n***New SKILL_REQUIRED Inserted For Position: %d!\n", this.positionId);
            
        }catch(Exception e){
			System.out.println(e);
		}
    }

    public void insertToDB(){
        try{
            String sql = "INSERT INTO SKILL_REQUIRED VALUES(?, ?)";

            this.ps = this.conn.prepareStatement(sql);
            this.ps.setInt(1, this.positionId);
            this.ps.setInt(2, this.skillCode);

            this.ps.execute();
            this.ps.close();
            
            System.out.printf("\n***New SKILL_REQUIRED Inserted For Position: %d!\n", this.positionId);
            
        }catch(Exception e){
			System.out.println(e);
		}
    }

    public void deleteFromDB(){
        try{

            String sql = "DELETE FROM SKILL_REQUIRED WHERE SR_PositionID = ? AND SR_SkillCode = ?";
            this.ps = this.conn.prepareStatement(sql);
            this.ps.setInt(1, this.positionId);
            this.ps.setInt(2, this.skillCode);
            this.ps.execute();
            this.ps.close();

            System.out.printf("\n***SKILL_REQUIRED: %d for Position: %d DELETED! \n", this.skillCode, this.positionId);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void deleteFromDB(int positionId, int skillCode){
        try{

            String sql = "DELETE FROM SKILL_REQUIRED WHERE SR_PositionID = ? AND SR_SkillCode = ?";
            this.ps = this.conn.prepareStatement(sql);
            this.ps.setInt(1, positionId);
            this.ps.setInt(2, skillCode);
            this.ps.execute();
            this.ps.close();

            System.out.printf("\n***SKILL_REQUIRED: %d for Position: %d DELETED! \n", this.skillCode, this.positionId);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public ResultSet getAllSkillRequired(){

        ResultSet result = null;

        try{
            String sql = "SELECT * FROM SKILL_REQUIRED";
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