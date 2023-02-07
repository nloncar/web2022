package beans;

public class Manager extends User{

	private Product center;

	public Manager() {
		super();
	}
	
	public Manager(Product center) {
		super();
		this.center = center;
	}

	public Manager(String username, String password, String name, String surname, String birthday, String gender) {
		super(username, password, name, surname, birthday, gender, "manager");
	}
	
	public Product getCenter() {
		return center;
	}

	public void setCenter(Product center) {
		this.center = center;
	}

}
