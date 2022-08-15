package com.oracle.oci.sdk.examples.region;

import java.util.Arrays;
import java.util.List;

import com.oracle.bmc.Region;

/*
 * Sample code to get all available regions from Region Enum. Note: This list depends on the SDK version.
 * 
 * Sample Output:
 * yny - ap-chuncheon-1
 * mel - ap-melbourne-1
 * hyd - ap-hyderabad-1
 * bom - ap-mumbai-1
 * kix - ap-osaka-1
 * icn - ap-seoul-1
 * syd - ap-sydney-1
 * nrt - ap-tokyo-1
 * yul - ca-montreal-1
 * yyz - ca-toronto-1
 * ams - eu-amsterdam-1
 * fra - eu-frankfurt-1
 * zrh - eu-zurich-1
 * jed - me-jeddah-1
 * dxb - me-dubai-1
 * gru - sa-saopaulo-1
 * lhr - uk-london-1
 * iad - us-ashburn-1
 * phx - us-phoenix-1
 * sjc - us-sanjose-1
 * cwl - uk-cardiff-1
 * scl - sa-santiago-1
 * vcp - sa-vinhedo-1
 * mtz - il-jerusalem-1
 * mrs - eu-marseille-1
 * sin - ap-singapore-1
 * auh - me-abudhabi-1
 * lin - eu-milan-1
 * arn - eu-stockholm-1
 * jnb - af-johannesburg-1
 * lfi - us-langley-1
 * luf - us-luke-1
 * ric - us-gov-ashburn-1
 * pia - us-gov-chicago-1
 * tus - us-gov-phoenix-1
 * ltn - uk-gov-london-1
 * brs - uk-gov-cardiff-1
 * nja - ap-chiyoda-1
 * ukb - ap-ibaraki-1
 * mct - me-dcc-muscat-1
 * wga - ap-dcc-canberra-1
 */

public class ListRegions {
    public static void main(String args[]) throws Exception {
    	
    	getRegions().stream().forEach((Region region) -> {
    		System.out.println(region.getRegionCode() + " - " + region.getRegionId());
    	});
    }
    
    public static List<Region> getRegions() {
    	return Arrays.asList(Region.values());
    }
}
