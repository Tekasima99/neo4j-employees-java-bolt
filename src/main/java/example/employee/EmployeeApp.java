package example.employee;

/**
 * Initiates the employee API service
 * @author Manoj Faria
 *
 */
public class EmployeeApp {
	public static void main(String[] args)
	{
		//TODO: Read neo4j connection info from an external config file
		//TODO: Handle plain text passwords (encrypt it)
		
		final EmployeeDao dao = new EmployeeDao("localhost", "7687", "neo4j", "neo4j");
		new EmployeeController(dao).init(); 
	}
}
