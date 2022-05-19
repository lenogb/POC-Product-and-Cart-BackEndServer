package com.cart.exceptionhandler;

import com.cart.exception.CartException;

import io.grpc.Status;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;

@GrpcAdvice
public class ExceptionHandler {
	
	
	@GrpcExceptionHandler
    public Status handleInvalidArgument(CartException e) {
        return Status.INVALID_ARGUMENT
        		.withDescription(e.getMessage()).withCause(e);
    }
	
	@GrpcExceptionHandler
    public Status handleInvalidArgument(Throwable e) {
        return Status.UNKNOWN.withDescription(e.getLocalizedMessage()).withCause(e);
    }
}
