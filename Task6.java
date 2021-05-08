import java.sql.Connection;
import java.sql.ResultSet;

public class Task6 {
    public static void main(String[] args){
        try{

            //Connect to db
            ConnectToDBAZ ct = new ConnectToDBAZ();

            //Get Connection
            Connection conn = ct.getConn();

            /**                             EMPLOYE                               */

            System.out.println("\n-------Employee--------");
            
            Employee e;
            ResultSet result;

            /*
            * Create a new employee 
            * Get All Employees in the Database
            * Close Prepared Statement of Employee After done with Result Set
            * Delete Employee from the DB 
            * Get All Employees in the Database
            * Close Prepared Statement of Customer After done with Result Set
            */
            e = new Employee(1, 3, "Shayna", "Bergeron", 110000, "Female", "shaynaceline@gmail.com", "Lisa Dr", "504-999-9999", "70123", conn);
            
            result = e.getAllEmployees();
            while(result.next()){
                System.out.println(result.getInt(1) + "," + result.getString(4) + " " + result.getString(5));
            }
            
            e.closePs();

            e.deleteFromDB();

            result = e.getAllEmployees();
            while(result.next()){
                System.out.println(result.getInt(1) + "," + result.getString(4) + " " + result.getString(5));
            }

            e.closePs();

            /**                             Customer                               */

            System.out.println("\n-------Customer--------");

            Customer c;

            /*
            * Create a new Customer 
            * Get All Customers in the Database
            * Close Prepared Statement of Customer After done with Result Set
            * Delete Customer from the DB
            * Get All Customers in the Database
            * Close Prepared Statement of Customer After done with Result Set
            */
            c = new Customer("Shayna", "Bergeron", "504-999-9999", conn);
            result = c.getAllCustomers();
            while(result.next()){
                System.out.println(result.getInt(1) + "," + result.getString(2) + " " + result.getString(3));
            }

            c.closePs();

            c.deleteFromDB();

            result = c.getAllCustomers();
            while(result.next()){
                System.out.println(result.getInt(1) + "," + result.getString(2) + " " + result.getString(3));
            }

            c.closePs();

            /**                             POSITION                               */

            System.out.println("\n-------POSITION--------");

            Position p;

            /*
            * Create a new Position 
            * Get All Positions in the Database
            * Close Prepared Statement of Position After done with Result Set
            * Delete position from the DB
            * Get All Positions in the Database
            * Close Prepared Statement of Position After done with Result Set
            */
            p = new Position("Back-end Developer", "Description lol.", 65000, 95000, conn);
            result = p.getAllPositions();
            while(result.next()){
                System.out.println(result.getInt(1) + "," + result.getString(2) + ":" + result.getString(3));
            }
            p.closePs();

            p.deleteFromDB();

            result = p.getAllPositions();
            while(result.next()){
                System.out.println(result.getInt(1) + "," + result.getString(2) + ":" + result.getString(3));
            }

            p.closePs();

            /**                             SKILL REQUIRED                               */

            System.out.println("\n-------SKILL REQUIRED  --------");

            SkillRequired sr;

            /*
            * Create a new skill requirement (skill id 4322) for position 4 in the DB
            * Get All Skill Requirements for position 4 in the Database
            * Close Prepared Statement of Position After done with Result Set
            * Delete this new skill requirement just created
            * Get All Skill Requirements for position 4 in the Database
            * Close Prepared Statement of Position After done with Result Set
            */
            sr = new SkillRequired(4, 4322, conn);
            result = p.getAllSkillRequired(4);
            while(result.next()){
                System.out.println(result.getInt(2));
            }
            sr.closePs();

            sr.deleteFromDB();

            result = p.getAllSkillRequired(4);
            while(result.next()){
                System.out.println(result.getInt(2));
            }
            sr.closePs();

            //Close DB Connection
            ct.closeConnection();
		}
		catch(Exception e){
			System.out.println(e);
		}
    }
}