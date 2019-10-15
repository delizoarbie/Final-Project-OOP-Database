
public class Employee extends Person {
	private String username;
	private String pass;
	private String position;

	public Employee(int ID, String fName, String mName, String lName, String email, String birthday,
			String address,int number, String gender, String username, String pass, String position) {
		super(ID,  fName, mName, lName,  email,  birthday, address,number, gender);
				this.pass=pass;
				this.position=position;
				this.username=username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
	
}


