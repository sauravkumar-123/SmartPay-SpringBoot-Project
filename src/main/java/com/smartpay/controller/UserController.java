package com.smartpay.controller;

import java.io.IOException;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.smartpay.fileupload.util.FileStorageService;
import com.smartpay.fileupload.model.MerchantDocuments;
import com.smartpay.fileupload.service.DocumentsUploadService;
import com.smartpay.model.Merchant;
import com.smartpay.model.User;
import com.smartpay.response.Response;
import com.smartpay.service.MerchantService;
import com.smartpay.service.UserService;

import io.swagger.annotations.ApiOperation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import org.springframework.security.access.prepost.PreAuthorize;

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
    private UserService userService;

    @Autowired
    private DocumentsUploadService documentsUploadService;

    @PreAuthorize("permitAll()")
    @ApiOperation("User Registration API")
    @RequestMapping(value = "/saveUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> registerUser(@Valid @RequestBody User user) {
        logger.info("Entred into RegistrationController::registerUser()");
        logger.info("Request Payload to registerUser {} ", user);
        User result = userService.registerUser(user);
        if (null != result) {
            logger.debug("User Registration Response {} " + result);
            return new ResponseEntity<Response>(new Response(LocalDateTime.now(), true, "User Details Saved", result), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<Response>(new Response(LocalDateTime.now(), false, "Unable To Save User Details", result),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

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
                    new Response(LocalDateTime.now(), true, "User Upgraded To Merchant Profile", merchantDetails), HttpStatus.OK);
        } else {
            return new ResponseEntity<Response>(
                    new Response(LocalDateTime.now(), false, "Unable to Update Merchant Profile", merchantDetails),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation("Upload Merchnat Documents To Verify With Admin")
    @RequestMapping(value = "/uploadDocuments/{identificationNo}", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> uploadMerchantDocuments(@PathVariable("identificationNo") String identificationNo,
            @Valid @RequestParam("aadharCard") MultipartFile aadharCard,
            @Valid @RequestParam("panCard") MultipartFile panCard,
            @Valid @RequestParam("cancelCheque") MultipartFile cancelCheque) {
        logger.info("Entred into UserController::uploadMerchantDocuments()");
        MultipartFile[] files = new MultipartFile[3];
        files[0] = aadharCard;
        files[1] = panCard;
        files[2] = cancelCheque;
        logger.info("Request Payload to uploadMerchantDocuments {} ", identificationNo, files);
        MerchantDocuments result = documentsUploadService.uploadDocumentsForBankingService(identificationNo, files);
        if (null != result) {
            logger.debug("Documents Uplaod Response {}", result);
            return new ResponseEntity<Response>(
                    new Response(LocalDateTime.now(), true, "Upload Success...Wait For Approval", result.getIsApproved()), HttpStatus.OK);
        } else {
            return new ResponseEntity<Response>(new Response(LocalDateTime.now(), false, "Documents Upload Failed...Try Again", null),
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
