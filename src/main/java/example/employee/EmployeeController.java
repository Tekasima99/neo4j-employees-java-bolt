package example.employee;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;

import com.google.gson.Gson;

import example.util.JsonTransformer;
import spark.servlet.SparkApplication;

/**
 * 
 * @author Manoj Faria
 * @version Draft
 * 
 * Controller class responsible for handling all incoming RESTful requests.
 * 
 */
public class EmployeeController implements SparkApplication {

	private static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Employee.class);
	private EmployeeDao employeeDao = null;
	
	public EmployeeController(EmployeeDao employeeDao)
	{
		this.employeeDao = employeeDao;
	}
	
	public void init() {

		// Get a list of all employees
		get("/employees", "application/json", (request, response) -> { 
			return this.employeeDao.getEmployees();
		}, new JsonTransformer());
		
		// Retrieve employee(s) using employee id
		get("/employees/:id", (request, response) -> {
			String id = request.params(":id");
			return this.employeeDao.getEmployee(id);
		}, new JsonTransformer());

		// Add a new employee
		post("/employees", (request, response) -> {

			//Employee emp = new Employee (request.queryParams("id"), request.queryParams("name"));
			final Employee emp = new Gson().fromJson(request.body(), Employee.class);
			this.employeeDao.addEmployee(emp);
			return "Successfully added employee: " + emp.getName();
		});
		
		//Handling unexpected requests/scenarios
		exception(Exception.class, (e, request, response) ->{
			logger.error(e.getMessage(), e);
			response.status(500);
			response.body("Server Error: " + e.getMessage());
		}) ;

	}
}
