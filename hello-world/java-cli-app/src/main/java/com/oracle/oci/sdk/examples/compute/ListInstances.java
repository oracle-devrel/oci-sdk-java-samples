package com.oracle.oci.sdk.examples.compute;

import java.util.List;

import com.oracle.bmc.Region;
import com.oracle.bmc.auth.AuthenticationDetailsProvider;
import com.oracle.bmc.core.ComputeClient;
import com.oracle.bmc.core.model.Instance;
import com.oracle.bmc.core.requests.ListInstancesRequest;
import com.oracle.bmc.core.responses.ListInstancesResponse;
import com.oracle.oci.sdk.OCIConfig;

/*
 * Sample code to get list of instances in particular region, compartment.
 * You may still add more select criteria in ListInstancesRequest like compartmentId.
 * 
 * Sample Output (Masked):
 * Instance: ocid1.instance.oc1.iad.anu...xru2a
 */

public class ListInstances {
    public static void main(String args[]) throws Exception {
    	
    	AuthenticationDetailsProvider adp = OCIConfig.getAuthenticationDetailsProvider();
    	Region region = Region.US_ASHBURN_1;
    	String compartmentId = "ocid1.tenancy.oc1..aaaaaaaasu7rvefmsyk5kqczfmdqi5clpddejfjk2attdqnk6sbk72wajq5q";  // TODO: Set your compartment ID here.

    	ListInstancesRequest request = ListInstancesRequest.builder()
        		.compartmentId(compartmentId)
        		.build(); 
        
        List<Instance> instances = getInstances(adp, region, request);
        
        instances.stream().forEach((Instance instance) -> {
        	System.out.println("Instance: " + instance.getId());
        });
    }
    
    public static List<Instance> getInstances(AuthenticationDetailsProvider adp, Region region, ListInstancesRequest request) throws Exception {
        ComputeClient computeClient = ComputeClient.builder().build(adp);
        computeClient.setRegion(region);
        ListInstancesResponse listInstancesResponse = computeClient.listInstances(request);
        return listInstancesResponse.getItems();
    }
}
