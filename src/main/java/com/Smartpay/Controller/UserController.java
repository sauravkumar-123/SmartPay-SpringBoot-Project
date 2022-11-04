package com.Smartpay.Controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Smartpay.FileUpload.FileStorageService;
import com.Smartpay.FileUpload.MerchantDocuments;
import com.Smartpay.FileUpload.Service.DocumentsUploadService;
import com.Smartpay.Model.Merchant;
import com.Smartpay.Request.MerchantDocumentsRequest;
import com.Smartpay.Response.Response;
import com.Smartpay.Service.MerchantService;

import io.swagger.annotations.ApiOperation;

@RestController
@Validated
@RequestMapping("/v1/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private FileStorageService fileStorageService;

	@Autowired
	private MerchantService merchantService;

	@Autowired
	private DocumentsUploadService documentsUploadService;

	@ApiOperation("Update User Profile To Merchant Profile To Start Banking Services")
	@RequestMapping(value = "/updateProfile/{userName}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> updateUserTOMerchantProfile(@PathVariable("userName") String userName,
			@Valid @RequestBody Merchant merchantRequest
	/*
	 * @Valid @RequestParam("aadharcardImageFilePath") @NotBlank(message =
	 * "Invalid File Path") MultipartFile aadharcardImageFilePath,
	 * 
	 * @Valid @RequestParam("pancardImageFilePath") @NotBlank(message =
	 * "Invalid File Path") MultipartFile pancardImageFilePath
	 */) {
		logger.info("Entred into UserController::updateUserTOMerchantProfile()");
		logger.info("Request Payload to updateUserTOMerchantProfile {} ", userName, merchantRequest);
		Merchant merchantDetails = merchantService.updateUserProfileToMerchant(userName, merchantRequest);
		if (null != merchantDetails) {
			logger.debug("Profile Update Response {}", merchantDetails);
			return new ResponseEntity<Response>(
					new Response(true, "User Upgraded To Merchant Profile", merchantDetails), HttpStatus.OK);
		} else {
			return new ResponseEntity<Response>(
					new Response(false, "Unable to Update Merchant Profile", merchantDetails),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation("Upload Merchnat Documents To Verify With Admin")
	@RequestMapping(value = "/uploadDocuments/{identificationNo}", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> uploadMerchantDocuments(@PathVariable("identificationNo") String identificationNo,
			@Valid @ModelAttribute MerchantDocumentsRequest merchantDocumentsRequest) {
		logger.info("Entred into UserController::uploadMerchantDocuments()");
		logger.info("Request Payload to uploadMerchantDocuments {} ", identificationNo,
				merchantDocumentsRequest.getDocuments());
		MerchantDocuments result = documentsUploadService.uploadDocumentsForBankingService(identificationNo,
				merchantDocumentsRequest);
		if (null != result) {
			logger.debug("Documents Uplaod Response {}", result);
			return new ResponseEntity<Response>(
					new Response(true, "Upload Success...Wait For Approval", result.getIsApproved()), HttpStatus.OK);
		} else {
			return new ResponseEntity<Response>(new Response(false, "Documents Upload Failed...Try Again", null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation("Download Merchant Uploaded Documents")
	@GetMapping("/downloadFile/{fileName:.+}")
	public ResponseEntity<Resource> downloadImageFile(@PathVariable(value = "fileName") String fileName,
			HttpServletRequest request) {
		logger.info("Entred into UserController::downloadImageFile()");
		logger.info("Request Payload to downloadImageFile {} ", fileName, request);
		// Load file as Resource
		Resource resource = fileStorageService.loadFileAsResource(fileName);
		// Try to determine file's content type
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
			logger.info("Could not determine file type.");
		}

		// Fallback to the default content type if type could not be determined
		if (contentType == null) {
			contentType = "application/octet-stream";
		}
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}

}
