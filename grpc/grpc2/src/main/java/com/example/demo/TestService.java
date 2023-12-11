package com.example.demo;

import com.example.proto.RequestMessage;
import com.example.proto.ResponseMessageStyle1;
import com.example.proto.ResponseMessageStyle2;
import com.example.proto.MyProtoServiceGrpc.MyProtoServiceImplBase;

import io.grpc.stub.StreamObserver;

/*
 * 서버 구동을 위한 서비스 생성
 	* proto 파일에서 정해준 클래스 이름에는, *Gprc 값이 붙은 1차 클래스가 만들어지고 실제 서비스를 구동하기 위해서는 *ImplBase 클래스를 사용하면 됨
*/
public class TestService extends MyProtoServiceImplBase{
	// StreamObserver : 서버에서 수신받은 데이터를 어떻게 할지에 대한 클래스
	@Override
	public void giveMeData1(RequestMessage request, StreamObserver<ResponseMessageStyle1> observer) {
		
		System.out.println(request.getId());
		System.out.println(request.getName());
		System.out.println(request.getPrice());

		ResponseMessageStyle1 res = ResponseMessageStyle1.newBuilder().setStatus("good").setResult("ok").build();
		observer.onNext(res); // onNext : 응답할 모양을 담아서 발행
		observer.onCompleted(); // onCompleted : 해당 응답이 끝났음을 알려줌
	}

	@Override
	public void giveMeData2(RequestMessage request, StreamObserver<ResponseMessageStyle2> observer) {
		
		System.out.println(request.getId());
		System.out.println(request.getName());
		System.out.println(request.getPrice());		
		
		ResponseMessageStyle2 res = ResponseMessageStyle2.newBuilder().setStatus("good").setResult("ok")
				.setStatusCode(200f).setYourRequest(request).build();
		observer.onNext(res);
		observer.onCompleted();
	}
}
