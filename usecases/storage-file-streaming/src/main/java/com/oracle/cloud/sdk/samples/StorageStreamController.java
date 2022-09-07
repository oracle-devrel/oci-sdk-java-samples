package com.oracle.cloud.sdk.samples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class StorageStreamController {

	@Autowired
	StorageStreamService storageService;

	private final String BUCKET_NAME = "your-bucket";

	@ResponseBody
	@RequestMapping(value = "/image/{file}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] streamImage(@PathVariable String file,
			@RequestParam(value = "region", defaultValue = "US_ASHBURN_1", required = false) String _region)
			throws Exception {

		return storageService.getBytesFromStorage(BUCKET_NAME, file, _region);
	}

	@RequestMapping(value = "/video/{file}", method = RequestMethod.GET)
	public Mono<ResponseEntity<byte[]>> streamVideo(@PathVariable String file,
			@RequestParam(value = "region", defaultValue = "US_ASHBURN_1", required = false) String _region)
			throws Exception {

		byte[] bytes = storageService.getBytesFromStorage(BUCKET_NAME, file, _region);

		return Mono.just(ResponseEntity.status(HttpStatus.OK).header("Content-Type", "video/mp4")
				.header("Content-Length", String.valueOf(bytes.length)).body(bytes));
	}
}
