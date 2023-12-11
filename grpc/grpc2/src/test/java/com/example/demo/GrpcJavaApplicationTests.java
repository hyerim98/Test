package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.proto.MyProtoServiceGrpc;
import com.example.proto.RequestMessage;
import com.example.proto.ResponseMessageStyle1;
import com.example.proto.ResponseMessageStyle2;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@SpringBootTest
public class GrpcJavaApplicationTests {
	@Test
    void contextLoads() {
        String url = "127.0.0.1:10777";

        try {
            ManagedChannel channel = ManagedChannelBuilder.forTarget(url)
                .usePlaintext()
                .build();	
            RequestMessage req = RequestMessage.newBuilder().setId("첫번째 메시지").setName("첫번째 메시지2").setPrice(1234f).build();
            RequestMessage req2 = RequestMessage.newBuilder().setId("두번째 메시지").setName("두번째 메시지2").setPrice(554f).build();

            MyProtoServiceGrpc.MyProtoServiceBlockingStub stub = MyProtoServiceGrpc.newBlockingStub(channel);

            ResponseMessageStyle1 response1 = stub.giveMeData1(req);
            ResponseMessageStyle2 response2 = stub.giveMeData2(req2);

            assertThat(response1);
            assertThat(response2);
            
            System.out.println(response1.getStatus());
            System.out.println(response2.getStatusCode());
            System.out.println(response2.getYourRequest().getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
