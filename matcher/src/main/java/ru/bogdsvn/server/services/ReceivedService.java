package ru.bogdsvn.server.services;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import ru.bogdsvn.proto.MyHello;
import ru.bogdsvn.proto.MyServiceHelloGrpc;

@GrpcService
public class ReceivedService extends MyServiceHelloGrpc.MyServiceHelloImplBase {



    @Override
    public void sayHello(MyHello.HelloRequest req, StreamObserver<MyHello.HelloResponse> responseObserver) {
        MyHello.HelloResponse response = MyHello.HelloResponse.newBuilder().setMessage("Hello ==> " + req.getName()).build();
        responseObserver.onNext(response);
        System.out.println("Received: " + response);
        responseObserver.onCompleted();
    }

}
