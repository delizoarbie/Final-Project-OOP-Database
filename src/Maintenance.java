
public class Maintenance {
	private int garageCode;
	private String mainType;
	private String address;
	private String mainDate;
	private int cost;
	private String vanCode;
	
		public Maintenance(int garageCode, String mainType, String address, String mainDate, int cost, String vanCode) {
			
			this.garageCode=garageCode;
			this.mainType=mainType;
			this.address=address;
			this.mainDate=mainDate;
			this.cost=cost;
			this.vanCode=vanCode;
			
		}

		public int getGarageCode() {
			return garageCode;
		}

		public void setGarageCode(int garageCode) {
			this.garageCode = garageCode;
		}

		public String getMainType() {
			return mainType;
		}

		public void setMainType(String mainType) {
			this.mainType = mainType;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getMainDate() {
			return mainDate;
		}

		public void setMainDate(String mainDate) {
			this.mainDate = mainDate;
		}

		public int getCost() {
			return cost;
		}

		public void setCost(int cost) {
			this.cost = cost;
		}

		public String getVanCode() {
			return vanCode;
		}

		public void setVanCode(String vanCode) {
			this.vanCode = vanCode;
		}
} 
