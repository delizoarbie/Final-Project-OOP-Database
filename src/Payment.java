
public class Payment {
	private int paymentNo;
	private String paymentType;
	private String paymentDate;
	private int amount;
	private String category;
	private int employeeID;
	private int transactionNo;
	
		public Payment(int paymentNo, String paymentType, String paymentDate, int amount, String category, int employeeID, int transactionNo) {
			
			this.paymentNo=paymentNo;
			this.paymentType=paymentType;
			this.paymentDate=paymentDate;
			this.amount=amount;
			this.category=category;
			this.employeeID=employeeID;
			this.transactionNo=transactionNo;
		}

		public int getPaymentNo() {
			return paymentNo;
		}

		public void setPaymentNo(int paymentNo) {
			this.paymentNo = paymentNo;
		}

		public String getPaymentType() {
			return paymentType;
		}

		public void setPaymentType(String paymentType) {
			this.paymentType = paymentType;
		}

		public String getPaymentDate() {
			return paymentDate;
		}

		public void setPaymentDate(String paymentDate) {
			this.paymentDate = paymentDate;
		}

		public int getAmount() {
			return amount;
		}

		public void setAmount(int amount) {
			this.amount = amount;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public int getEmployeeID() {
			return employeeID;
		}

		public void setEmployeeID(int employeeID) {
			this.employeeID = employeeID;
		}

		public int getTransactionNo() {
			return transactionNo;
		}

		public void setTransactionNo(int transactionNo) {
			this.transactionNo = transactionNo;
		}

}
