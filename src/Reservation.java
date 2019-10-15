
public class Reservation {
	private int reservationId;
	private String resDate;
	private String vanCode;
	
		public Reservation(int reservationId,String resDate, String vanCode) {
			
			this.reservationId=reservationId;
			this.resDate=resDate;
			this.vanCode=vanCode;
		}

		public int getReservationId() {
			return reservationId;
		}

		public void setReservationId(int reservationId) {
			this.reservationId = reservationId;
		}

		public String getResDate() {
			return resDate;
		}

		public void setResDate(String resDate) {
			this.resDate = resDate;
		}

		public String getVanCode() {
			return vanCode;
		}

		public void setVanCode(String vanCode) {
			this.vanCode = vanCode;
		}

}
