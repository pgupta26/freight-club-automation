package models.managedefaults;

import lombok.*;

@Getter
@Setter
public class ManageDefaults {

	private String defaultLTLServiceLevel;
	
	private String defaultPickupLocationType;
	
	private String defaultParcelPickupLocation;
	
	private String defaultLTLPickupLocation;

	private String defaultDropoffLocationType;

	private String defaultDropoffLocation;

	private String defaultPickupWindow;

	private String defaultSpecialInstructions;

	private String defaultLandingPage;

	private String defaultLabelFileName;

	private boolean dontShowServiceLabelCheckbox;

	private boolean includeCustomerPOCheckbox;

	private String preferredTrackingView;

	private String contactPhoneNumberType;

}
