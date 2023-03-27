package com.oracle.cloud.sdk.samples;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.oracle.bmc.Region;
import com.oracle.bmc.auth.AuthenticationDetailsProvider;
import com.oracle.bmc.auth.ConfigFileAuthenticationDetailsProvider;

@Service
public class OCIConfig {
	
	@Value("${oci.profile:DEFAULT}")
	private String profile;

	@Value("${oci.region:US_ASHBURN_1}")
	private String region;

	private static final Region DEFAULT_REGION = Region.US_ASHBURN_1;
	
	public AuthenticationDetailsProvider getAuthenticationDetailsProvider() throws Exception {
		AuthenticationDetailsProvider authenticationDetailsProvider =
                new ConfigFileAuthenticationDetailsProvider(getProfile());
		return authenticationDetailsProvider;
	}
	
	public Region getRegion() {
		if(region == null || Region.valueOf(region) == null)
			return DEFAULT_REGION;
		
		return Region.valueOf(region);
	}
	
	public String getProfile() {
		return profile;
	}
}