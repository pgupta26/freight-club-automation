package models.manageclaims;

import lombok.*;

@Getter
@Setter
public class CarrierPayment {
	
	private String paymeneType;
	
	private String paymentSource;
	
	private String carrierPaymentAmount;
	
	private String overpaymentType;
	
	private String overpayment;
	
	private String carrierChequeNumber;
	
	private String paymentDate;
	
	private String claimStatus;
	
	private String internalStatus;
	
}
