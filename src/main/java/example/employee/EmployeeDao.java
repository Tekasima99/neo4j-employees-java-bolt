package example.employee;

import static org.neo4j.driver.v1.Values.parameters;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;

/**
 * Data access service abstracting backend neo4j data access
 * 
 * @author Manoj Faria
 *
 */
public class EmployeeDao {
	
	private final Driver driver;
	
	public EmployeeDao(String serverName, String portNumber, String userName, String password )
	{
		driver = GraphDatabase.driver( "bolt://" + serverName + ":" + portNumber, AuthTokens.basic( userName, password ) );
	}
	
	/**
	 * Retrieve a list of all employees.
	 * 
	 * @return
	 */
	public List<Employee> getEmployees() {
		Session session = driver.session();

		StatementResult result = session.run( "MATCH (emp:Employee) " +
		                                      "RETURN emp.id AS id , emp.name AS name");
		List<Employee> employees = new ArrayList<Employee>();
		Employee emp;
		while ( result.hasNext() )
		{
		    Record record = result.next();
		    emp = new Employee();
		    emp.setId(record.get( "id" ).asString());
    		emp.setName(record.get( "name" ).asString());
    		employees.add(emp);
		}
		session.close();
		return employees;
	}
	
	/**
	 * 
	 * Retrieve all employees matching the given empId.
	 *  
	 * @param empId
	 * @return
	 */
	public List<Employee> getEmployee(String empId) {
		
		//TODO: Once database constraints and validations are in place, modify the code to return a single employee instance.
		
		Session session = driver.session();

		StatementResult result = session.run( "MATCH (emp:Employee) WHERE emp.id = \"" + empId + "\" " +
		                                      "RETURN emp.id AS id , emp.name AS name");
		List<Employee> employees = new ArrayList<Employee>();
		Employee emp;
		while ( result.hasNext() )
		{
		    Record record = result.next();
		    emp = new Employee();
		    emp.setId(record.get( "id" ).asString());
    		emp.setName(record.get( "name" ).asString());
    		employees.add(emp);
		}
		session.close();
		return employees;
	}

	/**
	 * Add a new employee instance
	 * 
	 * @param emp Employee to be added
	 */
	public void addEmployee(Employee emp) {
		Session session = driver.session();
		session.run( "CREATE (emp:Employee {id: {id}, name: {name}})",
		        parameters( "id", emp.getId(), "name", emp.getName() ) );
		
	}

}
