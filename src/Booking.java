
public class Booking {
	private int bookingID;
	private String bookDStart;
	private String bookDEnd;
	private String bookInsurance;
	private int bookNpass;
	private int transactionNum;
	private String vanCode;
	
		public Booking(int bookingID, String bookDStart, String bookDEnd, String bookInsurance, int bookNpass, int transactionNum,  String vanCode) {
			
			this.bookingID=bookingID;
			this.bookDStart=bookDStart;
			this.bookDEnd=bookDEnd;
			this.bookInsurance=bookInsurance;
			this.bookNpass=bookNpass;
			this.transactionNum=transactionNum;
			this.vanCode=vanCode;
			
		}

}
