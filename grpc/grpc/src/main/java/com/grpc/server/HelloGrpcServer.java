package com.grpc.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

// grpc 서버를 스타트
public class HelloGrpcServer {

	public static void main(String[] args) {
		Server grpcServer = ServerBuilder
				.forPort(8087)
				.addService(new HelloServiceImpl()).build();
		
		try {
			grpcServer.start();
			grpcServer.awaitTermination();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
