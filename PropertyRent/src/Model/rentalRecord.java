package Model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import Model.DateTime;
import Model.rentalProperty;

public class rentalRecord {
	// variables
	private String recordID;
	private DateTime rentDate;
	private DateTime estimatedReturnDate;
	private DateTime actualReturnDate;
	private double rentalFee;
	private double lateFee;
	private boolean isReturn;
	private rentalProperty rP;
	private int numberOfDays;
	private String customerID;

	// constructor
	public rentalRecord(rentalProperty rP, String customerID, DateTime rentDate, DateTime eReturn) {
		this.rP = rP;
		this.numberOfDays = DateTime.diffDays(eReturn, rentDate);
		this.recordID = rP.getPropertyId() + "_" + customerID + "_" + rentDate.getEightDigitDate();
		this.rentDate = rentDate;
		this.estimatedReturnDate = eReturn;
		this.isReturn = false;
		this.customerID = customerID;
	}

	public rentalRecord(rentalProperty RP, String recordid, DateTime rentDate, DateTime eReturnDate,
			DateTime aReturnDate, double rentalFee, double lateFee) {
		this.rP = RP;
		this.recordID = recordid;
		this.rentDate = rentDate;
		this.estimatedReturnDate = eReturnDate;
		this.actualReturnDate = aReturnDate;
		this.rentalFee = rentalFee;
		this.lateFee = lateFee;
	}

	public DateTime getRentDate() {
		return rentDate;
	}

	public DateTime getActualReturnDate() {
		return actualReturnDate;
	}

	public void setActualReturnDate(DateTime actualReturnDate) {
		this.actualReturnDate = actualReturnDate;
	}

	public DateTime getEstReturnDate() {
		return estimatedReturnDate;
	}

	public void setEstReturnDate(DateTime eReturnDate) {
		this.estimatedReturnDate = eReturnDate;
	}

	public double getRentalFee() {
		return rentalFee;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setRentalFee() {
		double rentalFee;
		int actualDay = DateTime.diffDays(this.actualReturnDate, this.rentDate);
		if (actualDay > this.numberOfDays) {
			if (this.rP instanceof apartment) {
				if (this.rP.getNumOfBedrooms() == 1) {
					rentalFee = 143 * this.numberOfDays;
					this.rentalFee = rentalFee;
				}
				if (this.rP.getNumOfBedrooms() == 2) {
					rentalFee = 210 * this.numberOfDays;
					this.rentalFee = rentalFee;
				}
				// 3-bedroom apartment
				if (this.rP.getNumOfBedrooms() == 3) {
					rentalFee = 319 * this.numberOfDays;
					this.rentalFee = rentalFee;
				}
			} else {
				// rP is premiumSuite
				rentalFee = 554 * this.numberOfDays;
				this.rentalFee = rentalFee;
			}
		} else {
			if (this.rP instanceof apartment) {
				// 1-bedroom apartment
				if (this.rP.getNumOfBedrooms() == 1) {
					rentalFee = 143 * actualDay;
					this.rentalFee = rentalFee;
				}
				// 2-bedroom apartment
				if (this.rP.getNumOfBedrooms() == 2) {
					rentalFee = 210 * actualDay;
					this.rentalFee = rentalFee;
				}
				// 3-bedroom apartment
				if (this.rP.getNumOfBedrooms() == 3) {
					rentalFee = 319 * actualDay;
					this.rentalFee = rentalFee;
				}
			} else {
				// rP is premiumSuite
				rentalFee = 554 * actualDay;
				this.rentalFee = rentalFee;
			}
		}
	}

	public double getLateFee() {
		return lateFee;
	}

	public void setLateFee() {
		double lateFee;
		DateTime dt = new DateTime();
		int delay = dt.diffDays(this.actualReturnDate, this.estimatedReturnDate);
		if (this.rP instanceof apartment && delay > 0) {
			if (this.rP.getNumOfBedrooms() == 1) {
				lateFee = delay * 143 * 1.15;
				this.lateFee = lateFee;
			} else if (this.rP.getNumOfBedrooms() == 2) {
				lateFee = delay * 210 * 1.15;
				this.lateFee = lateFee;
			} else if (this.rP.getNumOfBedrooms() == 3) {
				lateFee = delay * 319 * 1.15;
				this.lateFee = lateFee;
			}

		} else if (this.rP instanceof premiumSuite && delay > 0) {
			lateFee = delay * 662;
			this.lateFee = lateFee;
		} else
			this.lateFee = 0;
	}

	public boolean isReturn() {
		return isReturn;
	}

	public void setIsReturn() {
		this.isReturn = true;
	}

	public void setIsRented() {
		this.isReturn = false;
	}

	public String toString() {
		if (this.isReturn = false)
			return this.recordID + ":" + this.rentDate + ":" + this.estimatedReturnDate;
		else
			return this.recordID + ":" + this.rentDate + ":" + this.estimatedReturnDate + ":" + this.actualReturnDate
					+ ":" + this.rentalFee + ":" + this.lateFee;
	}

	public String getDetails() {
		String details = null;

		if (this.getActualReturnDate() != null) {
			details = String.format("%-30s  %7s\n", "Record ID:", this.recordID)
					+ String.format("%-30s  %7s\n", "Rent Date:", this.rentDate)
					+ String.format("%-30s  %7s\n", "Estimated Return Date:", this.estimatedReturnDate)
					+ String.format("%-30s  %7s\n", "Actual Return Date:", this.actualReturnDate)
					+ String.format("%-30s  %7s\n", "Rental Fee:", this.getRentalFee())
					+ String.format("%-30s  %7s\n", "Late Fee:", this.getLateFee());

		} else {
			details = String.format("%-30s  %7s\n", "Record ID:", this.recordID)
					+ String.format("%-30s  %7s\n", "Rent Date:", this.rentDate)
					+ String.format("%-30s  %7s\n", "Estimated Return Date:", this.estimatedReturnDate);

		}
		return details;
	}
}
