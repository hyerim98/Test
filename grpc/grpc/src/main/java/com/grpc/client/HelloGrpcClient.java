package com.grpc.client;

import com.grpc.GreeterGrpc;
import com.grpc.HelloReply;
import com.grpc.HelloRequest;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class HelloGrpcClient {

	public static void main(String[] args) {
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8087)
				.usePlaintext()
				.build();
		
		// GreeterGrpc.newBlockingStub을 이용하여 서버에 요청을 보냄
		GreeterGrpc.GreeterBlockingStub blockingStub = GreeterGrpc.newBlockingStub(channel);
		
		HelloReply helloReply = blockingStub.sayHello(HelloRequest.newBuilder()
				.setName("Mingki")
				.build());
		
		System.out.println("*****************************************************");
		System.out.println("요청 결과가 옴.");
		System.out.println("response : " + helloReply);
		System.out.println("");
		System.out.println("response : " + helloReply.getMessage());
		System.out.println("*****************************************************");
		
		channel.isShutdown();
	}

}
