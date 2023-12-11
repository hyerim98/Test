package com.example.demo;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;

/* 서비스를 사용하는 서버 클래스 */
public class TestGrpcServer {
	private Server server;

    public void runServer() {
        int port = 10777;
        try {
            server = ServerBuilder.forPort(port)
            .addService(new TestService()) // addService : 프로토콜에 대한 정의를 이어간다
            //.addService()  이런식으로 n개의 서비스를 등록..
            .build()
            .start();
            server.awaitTermination();  //요게 없으면 1번 실행하고 끝납니다. 계속 수신대기 하는 기능 입니다.
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            try {
                if(server != null) {
                    server.shutdownNow();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
    }
}
