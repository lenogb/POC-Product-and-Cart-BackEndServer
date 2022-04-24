package com.cartgateway.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cartgateway.servercomm.grpc.CartServiceGrpc;
import com.cartgateway.servercomm.grpc.CartServiceGrpc.CartServiceBlockingStub;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Configuration
public class GrpcConfiguration {

	@Value("${GRPC-SERVER-PORT:9999}")
	private Integer serverPort;
	
	@Value("${GRPC-SERVER-HOST:localhost}")
	private String serverHost;
	
	@Bean
	public CartServiceBlockingStub stub() {
		ManagedChannel channel = ManagedChannelBuilder.forAddress(serverHost, serverPort)
				.usePlaintext()
				.build();

		return CartServiceGrpc.newBlockingStub(channel);
	}
	
	
}