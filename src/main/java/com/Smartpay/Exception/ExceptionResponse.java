package com.Smartpay.Exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {

	private LocalDateTime timestamp;
	private boolean processStatus;
	private String message;
	private Object datasource;
}
