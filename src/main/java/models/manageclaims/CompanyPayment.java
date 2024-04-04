package models.manageclaims;

import lombok.*;


@Getter
@Setter
public class CompanyPayment {
	
	private String paymeneType;
	
	private String paymentMethod;
	
	private String companyPayoutAmount;
	
	private String claimStatus;
	
	private String internalStatus;
	
}
