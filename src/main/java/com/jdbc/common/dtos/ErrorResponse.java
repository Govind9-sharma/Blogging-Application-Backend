package com.jdbc.common.dtos;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class ErrorResponse {
	
	private String message;
}
