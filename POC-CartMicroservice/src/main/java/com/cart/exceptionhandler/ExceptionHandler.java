package com.cart.exceptionhandler;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cart.exception.NotFoundException;
import com.cart.exception.QuantityExceededException;
import com.cart.exception.UnacceptableInputException;
import com.cart.exception.UnavailableItemException;

import io.grpc.Status;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;

@GrpcAdvice
public class ExceptionHandler {
	
	
	@GrpcExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    public Status handleInvalidArgument(UnacceptableInputException e) {
        return Status.INVALID_ARGUMENT
        		.withDescription(e.getErrorfound().getMessage()+"- "+e.getLocalizedMessage()).withCause(e);
    }
	
	@GrpcExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    public Status handleInvalidArgument(NotFoundException e) {
        return Status.NOT_FOUND.withDescription(e.getErrorfound().getMessage()+"- "+e.getLocalizedMessage()).withCause(e);
    }
	
	@GrpcExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    public Status handleInvalidArgument(UnavailableItemException e) {
        return Status.UNAVAILABLE.withDescription(e.getErrorfound().getMessage()+"- "+e.getLocalizedMessage()).withCause(e);
    }
	
	@GrpcExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    public Status handleInvalidArgument(QuantityExceededException e) {
        return Status.OUT_OF_RANGE.withDescription(e.getErrorfound().getMessage()+"- "+e.getLocalizedMessage()).withCause(e);
    }
	
	@GrpcExceptionHandler
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public Status handleInvalidArgument(SQLException e) {
        return Status.PERMISSION_DENIED.withDescription(e.getLocalizedMessage()).withCause(e);
    }
	
	@GrpcExceptionHandler
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Status handleInvalidArgument(Throwable e) {
        return Status.UNKNOWN.withDescription(e.getLocalizedMessage()).withCause(e);
    }
}
