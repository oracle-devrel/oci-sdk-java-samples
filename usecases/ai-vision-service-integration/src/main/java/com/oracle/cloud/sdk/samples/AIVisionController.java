package com.oracle.cloud.sdk.samples;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.bmc.Region;
import com.oracle.bmc.aivision.AIServiceVisionClient;
import com.oracle.bmc.aivision.model.AnalyzeDocumentDetails;
import com.oracle.bmc.aivision.model.AnalyzeDocumentResult;
import com.oracle.bmc.aivision.model.AnalyzeImageDetails;
import com.oracle.bmc.aivision.model.AnalyzeImageResult;
import com.oracle.bmc.aivision.model.DocumentClassificationFeature;
import com.oracle.bmc.aivision.model.DocumentFeature;
import com.oracle.bmc.aivision.model.DocumentKeyValueDetectionFeature;
import com.oracle.bmc.aivision.model.DocumentLanguage;
import com.oracle.bmc.aivision.model.DocumentLanguageClassificationFeature;
import com.oracle.bmc.aivision.model.DocumentTableDetectionFeature;
import com.oracle.bmc.aivision.model.DocumentTextDetectionFeature;
import com.oracle.bmc.aivision.model.ImageClassificationFeature;
import com.oracle.bmc.aivision.model.ImageFeature;
import com.oracle.bmc.aivision.model.ImageObjectDetectionFeature;
import com.oracle.bmc.aivision.model.ImageTextDetectionFeature;
import com.oracle.bmc.aivision.model.ObjectStorageDocumentDetails;
import com.oracle.bmc.aivision.model.ObjectStorageImageDetails;
import com.oracle.bmc.aivision.requests.AnalyzeDocumentRequest;
import com.oracle.bmc.aivision.requests.AnalyzeImageRequest;
import com.oracle.bmc.aivision.responses.AnalyzeDocumentResponse;
import com.oracle.bmc.aivision.responses.AnalyzeImageResponse;
import com.oracle.bmc.auth.AuthenticationDetailsProvider;
import com.oracle.bmc.objectstorage.ObjectStorageClient;
import com.oracle.bmc.objectstorage.requests.GetNamespaceRequest;
import com.oracle.bmc.objectstorage.responses.GetNamespaceResponse;

@RestController
public class AIVisionController {

	@ResponseBody
	@RequestMapping(value = "/ai/vision/doc", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public AnalyzeDocumentResult processDoc(
			@RequestParam(value = "region", defaultValue = "US_ASHBURN_1", required = false) String _region,
			@RequestParam(value = "bucket", defaultValue = "your-bucket", required = false) String _bucket,
			@RequestParam(value = "object", required = true) String _object
			) throws Exception {

		AuthenticationDetailsProvider provider = OCIConfig.getAuthenticationDetailsProvider();
		final AIServiceVisionClient aiServiceVisionClient = new AIServiceVisionClient(provider);
		aiServiceVisionClient.setRegion(Region.valueOf(_region));
		List<DocumentFeature> features = new ArrayList<>();
		AnalyzeDocumentResponse response;
		
        GetNamespaceResponse namespaceResponse =
                new ObjectStorageClient(provider).getNamespace(GetNamespaceRequest.builder().build());
        String namespace = namespaceResponse.getValue();

		features.add(DocumentClassificationFeature.builder().build());
		features.add(DocumentKeyValueDetectionFeature.builder().build());
		features.add(DocumentLanguageClassificationFeature.builder().build());
		features.add(DocumentTableDetectionFeature.builder().build());
		features.add(DocumentTextDetectionFeature.builder().build());
		
		ObjectStorageDocumentDetails objectStorageDocumentDetails = ObjectStorageDocumentDetails.builder()
				.bucketName(_bucket).namespaceName(namespace).objectName(_object)
				.build();

		AnalyzeDocumentDetails analyzeDocumentDetails = AnalyzeDocumentDetails.builder().features(features)
				.document(objectStorageDocumentDetails).language(DocumentLanguage.Eng)
				.build();

		AnalyzeDocumentRequest request = AnalyzeDocumentRequest.builder()
				.analyzeDocumentDetails(analyzeDocumentDetails)
				.build();
		
		response = aiServiceVisionClient.analyzeDocument(request);
		return response.getAnalyzeDocumentResult();
	}

	@ResponseBody
	@RequestMapping(value = "/ai/vision/image", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public AnalyzeImageResult processImage(
			@RequestParam(value = "region", defaultValue = "US_ASHBURN_1", required = false) String _region,
			@RequestParam(value = "bucket", defaultValue = "your-bucket", required = false) String _bucket,
			@RequestParam(value = "object", required = true) String _object
			) throws Exception {

		AuthenticationDetailsProvider provider = OCIConfig.getAuthenticationDetailsProvider();
		final AIServiceVisionClient aiServiceVisionClient = new AIServiceVisionClient(provider);
		aiServiceVisionClient.setRegion(Region.valueOf(_region));
		List<ImageFeature> features = new ArrayList<>();
		AnalyzeImageResponse response;
		
        GetNamespaceResponse namespaceResponse =
                new ObjectStorageClient(provider).getNamespace(GetNamespaceRequest.builder().build());
        String namespace = namespaceResponse.getValue();

		features.add(ImageClassificationFeature.builder().build());
		features.add(ImageTextDetectionFeature.builder().build());
		features.add(ImageObjectDetectionFeature.builder().build());
		
		ObjectStorageImageDetails objectStorageDocumentDetails = ObjectStorageImageDetails.builder()
				.bucketName(_bucket).namespaceName(namespace).objectName(_object)
				.build();
		
		AnalyzeImageDetails analyzeDocumentDetails = AnalyzeImageDetails.builder().features(features)
				.image(objectStorageDocumentDetails)
				.build();

		AnalyzeImageRequest request = AnalyzeImageRequest.builder()
				.analyzeImageDetails(analyzeDocumentDetails)
				.build();
		
		response = aiServiceVisionClient.analyzeImage(request);
		return response.getAnalyzeImageResult();
	}
}
