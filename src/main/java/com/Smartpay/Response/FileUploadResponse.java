package com.Smartpay.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FileUploadResponse {

	private String fileName;
	private String fileDownloadUri;
	private String fileType;
	private long size;
}
