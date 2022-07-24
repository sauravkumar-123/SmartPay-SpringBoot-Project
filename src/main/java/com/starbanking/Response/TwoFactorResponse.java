package com.starbanking.Response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class TwoFactorResponse {

	private String Status;
	private String Details;
}
