
public class Repair {
	private int repairCode;
	private String repairType;
	private String repDate;
	private int repCost;
	private String vanCode;
	
		public Repair(int repairCode, String repairType, String repDate, int repCost, String vanCode) {
			
			this.repairCode=repairCode;
			this.repairType=repairType;
			this.repDate=repDate;
			this.repCost=repCost;
			this.vanCode=vanCode;
		}

		public int getRepairCode() {
			return repairCode;
		}

		public void setRepairCode(int repairCode) {
			this.repairCode = repairCode;
		}

		public String getRepairType() {
			return repairType;
		}

		public void setRepairType(String repairType) {
			this.repairType = repairType;
		}

		public String getRepDate() {
			return repDate;
		}

		public void setRepDate(String repDate) {
			this.repDate = repDate;
		}

		public int getRepCost() {
			return repCost;
		}

		public void setRepCost(int repCost) {
			this.repCost = repCost;
		}

		public String getVanCode() {
			return vanCode;
		}

		public void setVanCode(String vanCode) {
			this.vanCode = vanCode;
		}

}
