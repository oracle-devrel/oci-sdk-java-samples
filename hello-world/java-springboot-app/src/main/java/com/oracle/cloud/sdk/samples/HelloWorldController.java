package com.oracle.cloud.sdk.samples;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.bmc.Region;
import com.oracle.bmc.auth.AuthenticationDetailsProvider;
import com.oracle.bmc.core.ComputeClient;
import com.oracle.bmc.core.model.Instance;
import com.oracle.bmc.core.requests.ListInstancesRequest;
import com.oracle.bmc.core.responses.ListInstancesResponse;

@RestController
public class HelloWorldController {
	@RequestMapping("/")  
	public String hello()   
	{  
		return "Hello World";  
	} 
	
	@RequestMapping("/regions")
    public List<Region> getRegions() {
    	return Arrays.asList(Region.values());
    }

    @RequestMapping("/instances")
    public List<Instance> instances(@RequestParam(value="region", defaultValue = "US_ASHBURN_1", required=false) String _region, @RequestParam("compartmentId") String compartmentId) throws Exception {
    	AuthenticationDetailsProvider adp = OCIConfig.getAuthenticationDetailsProvider();
    	Region region = Region.valueOf(_region);

    	ListInstancesRequest request = ListInstancesRequest.builder()
    			.compartmentId(compartmentId)
    			.build(); 

    	ComputeClient computeClient = ComputeClient.builder().build(adp);
    	computeClient.setRegion(region);
    	ListInstancesResponse listInstancesResponse = computeClient.listInstances(request);
    	return listInstancesResponse.getItems();
    }
}

