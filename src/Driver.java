
public class Driver extends Person {
	private int guardianNum;
	private String guardianName;
	private String vanCode;
	private String insurance;
	
	public Driver(int ID, String fName, String mName, String lName, String email, String birthday, String address,
			int number, String gender, int guardianNum, String guardianName, String vanCode, String insurance) {
		super(ID, fName, mName, lName, email, birthday, address, number, gender);
		 	
			this.guardianNum=guardianNum;
			this.guardianName=guardianName;
			this.vanCode=vanCode;
			this.insurance=insurance;
	}

	public int getGuardianNum() {
		return guardianNum;
	}

	public void setGuardianNum(int guardianNum) {
		this.guardianNum = guardianNum;
	}

	public String getGuardianName() {
		return guardianName;
	}

	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}

	public String getVanCode() {
		return vanCode;
	}

	public void setVanCode(String vanCode) {
		this.vanCode = vanCode;
	}

	public String getInsurance() {
		return insurance;
	}

	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}

}
