package example.employee;

/**
 * Employee Model class.
 * 
 * @author Manoj Faria
 *
 */
public class Employee{
	
	private String id;
	private String name;

	public Employee()
	{
		this.id = null;
		this.name = null;
	}
	
	public Employee(String id, String name)
	{
		this.id = id;
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
