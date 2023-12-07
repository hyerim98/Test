package com.grpc.server;

import com.grpc.GreeterGrpc;
import com.grpc.GreeterGrpc.GreeterImplBase;
import com.grpc.HelloReply;
import com.grpc.HelloRequest;

import io.grpc.stub.StreamObserver;

// 클라이언트가 이름(name) 정보를 보내면, 서버는 name 유저의 주소를 반환하는 시나리오
public class HelloServiceImpl extends GreeterGrpc.GreeterImplBase{
	@Override
	public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
		/**
		 * 예) DataBase 테이블에서 값을 조회한다.
		 **/
		String getAddress = "서울 강남구 역삼동 110 번지";
		
		String sendName = request.getName();
		
		System.out.println("=====================================================");
		System.out.println("요청이 옴.");
		System.out.println("=====================================================");
		System.out.println("request : "+request);
		System.out.println("요청 유저명 : " + sendName);
		
		
		HelloReply helloReply = HelloReply.newBuilder()
				.setMessage("User's Address Value : "+getAddress)
				.build();
		
		responseObserver.onNext(helloReply);
		responseObserver.onCompleted();
		
		System.out.println("=====================================================");
		System.out.println("GRPC 호출이 종료 됨.");
		System.out.println("=====================================================");
	}
}
