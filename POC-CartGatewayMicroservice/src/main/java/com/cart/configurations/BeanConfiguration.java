package com.cart.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cartservice.grpc.CartServiceGrpc;
import com.cartservice.grpc.CartServiceGrpc.CartServiceBlockingStub;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Configuration
public class BeanConfiguration {

	@Value("80")
	private Integer serverPort;
	
	@Value("localhost")
	private String serverHost;
	
	@Bean
	public CartServiceBlockingStub stub() {
		ManagedChannel channel = ManagedChannelBuilder.forAddress(serverHost, serverPort)
				.usePlaintext()
				.build();

		return CartServiceGrpc.newBlockingStub(channel);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
}