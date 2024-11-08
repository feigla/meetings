package ru.bogdsvn.grcp.proximity;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.66.0)",
    comments = "Source: proximity.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ProximityServiceGrpc {

  private ProximityServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "ProximityService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<ru.bogdsvn.grcp.proximity.Proximity.ProfileList.Profile,
      ru.bogdsvn.grcp.proximity.Proximity.ProfileList> getGetNearbyProfilesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getNearbyProfiles",
      requestType = ru.bogdsvn.grcp.proximity.Proximity.ProfileList.Profile.class,
      responseType = ru.bogdsvn.grcp.proximity.Proximity.ProfileList.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ru.bogdsvn.grcp.proximity.Proximity.ProfileList.Profile,
      ru.bogdsvn.grcp.proximity.Proximity.ProfileList> getGetNearbyProfilesMethod() {
    io.grpc.MethodDescriptor<ru.bogdsvn.grcp.proximity.Proximity.ProfileList.Profile, ru.bogdsvn.grcp.proximity.Proximity.ProfileList> getGetNearbyProfilesMethod;
    if ((getGetNearbyProfilesMethod = ProximityServiceGrpc.getGetNearbyProfilesMethod) == null) {
      synchronized (ProximityServiceGrpc.class) {
        if ((getGetNearbyProfilesMethod = ProximityServiceGrpc.getGetNearbyProfilesMethod) == null) {
          ProximityServiceGrpc.getGetNearbyProfilesMethod = getGetNearbyProfilesMethod =
              io.grpc.MethodDescriptor.<ru.bogdsvn.grcp.proximity.Proximity.ProfileList.Profile, ru.bogdsvn.grcp.proximity.Proximity.ProfileList>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getNearbyProfiles"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ru.bogdsvn.grcp.proximity.Proximity.ProfileList.Profile.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ru.bogdsvn.grcp.proximity.Proximity.ProfileList.getDefaultInstance()))
              .setSchemaDescriptor(new ProximityServiceMethodDescriptorSupplier("getNearbyProfiles"))
              .build();
        }
      }
    }
    return getGetNearbyProfilesMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ProximityServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ProximityServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ProximityServiceStub>() {
        @java.lang.Override
        public ProximityServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ProximityServiceStub(channel, callOptions);
        }
      };
    return ProximityServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ProximityServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ProximityServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ProximityServiceBlockingStub>() {
        @java.lang.Override
        public ProximityServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ProximityServiceBlockingStub(channel, callOptions);
        }
      };
    return ProximityServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ProximityServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ProximityServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ProximityServiceFutureStub>() {
        @java.lang.Override
        public ProximityServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ProximityServiceFutureStub(channel, callOptions);
        }
      };
    return ProximityServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void getNearbyProfiles(ru.bogdsvn.grcp.proximity.Proximity.ProfileList.Profile request,
        io.grpc.stub.StreamObserver<ru.bogdsvn.grcp.proximity.Proximity.ProfileList> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetNearbyProfilesMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service ProximityService.
   */
  public static abstract class ProximityServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ProximityServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service ProximityService.
   */
  public static final class ProximityServiceStub
      extends io.grpc.stub.AbstractAsyncStub<ProximityServiceStub> {
    private ProximityServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProximityServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ProximityServiceStub(channel, callOptions);
    }

    /**
     */
    public void getNearbyProfiles(ru.bogdsvn.grcp.proximity.Proximity.ProfileList.Profile request,
        io.grpc.stub.StreamObserver<ru.bogdsvn.grcp.proximity.Proximity.ProfileList> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetNearbyProfilesMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service ProximityService.
   */
  public static final class ProximityServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ProximityServiceBlockingStub> {
    private ProximityServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProximityServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ProximityServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public ru.bogdsvn.grcp.proximity.Proximity.ProfileList getNearbyProfiles(ru.bogdsvn.grcp.proximity.Proximity.ProfileList.Profile request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetNearbyProfilesMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service ProximityService.
   */
  public static final class ProximityServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<ProximityServiceFutureStub> {
    private ProximityServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProximityServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ProximityServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ru.bogdsvn.grcp.proximity.Proximity.ProfileList> getNearbyProfiles(
        ru.bogdsvn.grcp.proximity.Proximity.ProfileList.Profile request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetNearbyProfilesMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_NEARBY_PROFILES = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_NEARBY_PROFILES:
          serviceImpl.getNearbyProfiles((ru.bogdsvn.grcp.proximity.Proximity.ProfileList.Profile) request,
              (io.grpc.stub.StreamObserver<ru.bogdsvn.grcp.proximity.Proximity.ProfileList>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getGetNearbyProfilesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              ru.bogdsvn.grcp.proximity.Proximity.ProfileList.Profile,
              ru.bogdsvn.grcp.proximity.Proximity.ProfileList>(
                service, METHODID_GET_NEARBY_PROFILES)))
        .build();
  }

  private static abstract class ProximityServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ProximityServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return ru.bogdsvn.grcp.proximity.Proximity.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ProximityService");
    }
  }

  private static final class ProximityServiceFileDescriptorSupplier
      extends ProximityServiceBaseDescriptorSupplier {
    ProximityServiceFileDescriptorSupplier() {}
  }

  private static final class ProximityServiceMethodDescriptorSupplier
      extends ProximityServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    ProximityServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ProximityServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ProximityServiceFileDescriptorSupplier())
              .addMethod(getGetNearbyProfilesMethod())
              .build();
        }
      }
    }
    return result;
  }
}
