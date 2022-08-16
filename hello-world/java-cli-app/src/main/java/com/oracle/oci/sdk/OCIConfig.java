package com.oracle.oci.sdk;

import com.oracle.bmc.auth.AuthenticationDetailsProvider;
import com.oracle.bmc.auth.ConfigFileAuthenticationDetailsProvider;

public class OCIConfig {
	private static final String PROFILE_DEFAULT = "DEFAULT";
	
	public static AuthenticationDetailsProvider getAuthenticationDetailsProvider() throws Exception {
		AuthenticationDetailsProvider authenticationDetailsProvider =
                new ConfigFileAuthenticationDetailsProvider(PROFILE_DEFAULT);
		return authenticationDetailsProvider;
	}
}