package com.poc.microservices.product.util;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.validation.ObjectError;

import lombok.Data;

@Data
@Component
public class objectModel {
	private List<ObjectError> errors;
	private Object[] aguments;

	public String errorsToString() {
		return "[Errors=" + errors + "]";
	}

	public String argumentsToString() {
		return "[Input arguments=" + Arrays.toString(aguments) + "]";
	}
}
