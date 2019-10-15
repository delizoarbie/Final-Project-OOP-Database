
public class Person {
		private int ID, number;
		private String fName, mName, lName, email, birthday, address, gender;
		
		public Person(int ID, String fName, String mName, String lName, String email, String birthday, String address, int number, String gender){
			
			this.ID = ID;
			this.fName=fName;
			this.mName=mName;
			this.lName=lName;
			this.email=email;
			this.birthday=birthday;
			this.address=address;
			this.gender=gender;
			this.number= number;
			
		}

		public int getID() {
			return ID;
		}

		public void setID(int iD) {
			ID = iD;
		}

		public int getNumber() {
			return number;
		}

		public void setNumber(int number) {
			this.number = number;
		}

		public String getfName() {
			return fName;
		}

		public void setfName(String fName) {
			this.fName = fName;
		}

		public String getmName() {
			return mName;
		}

		public void setmName(String mName) {
			this.mName = mName;
		}

		public String getlName() {
			return lName;
		}

		public void setlName(String lName) {
			this.lName = lName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getBirthday() {
			return birthday;
		}

		public void setBirthday(String birthday) {
			this.birthday = birthday;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

	}

