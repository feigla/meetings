package ru.bogdsvn.grcp.profile;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.66.0)",
    comments = "Source: profile.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ProfileServiceGrpc {

  private ProfileServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "ProfileService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<ru.bogdsvn.grcp.profile.ProfileOuterClass.Profile,
      ru.bogdsvn.grcp.profile.ProfileOuterClass.Preference> getGetPreferenceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getPreference",
      requestType = ru.bogdsvn.grcp.profile.ProfileOuterClass.Profile.class,
      responseType = ru.bogdsvn.grcp.profile.ProfileOuterClass.Preference.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ru.bogdsvn.grcp.profile.ProfileOuterClass.Profile,
      ru.bogdsvn.grcp.profile.ProfileOuterClass.Preference> getGetPreferenceMethod() {
    io.grpc.MethodDescriptor<ru.bogdsvn.grcp.profile.ProfileOuterClass.Profile, ru.bogdsvn.grcp.profile.ProfileOuterClass.Preference> getGetPreferenceMethod;
    if ((getGetPreferenceMethod = ProfileServiceGrpc.getGetPreferenceMethod) == null) {
      synchronized (ProfileServiceGrpc.class) {
        if ((getGetPreferenceMethod = ProfileServiceGrpc.getGetPreferenceMethod) == null) {
          ProfileServiceGrpc.getGetPreferenceMethod = getGetPreferenceMethod =
              io.grpc.MethodDescriptor.<ru.bogdsvn.grcp.profile.ProfileOuterClass.Profile, ru.bogdsvn.grcp.profile.ProfileOuterClass.Preference>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getPreference"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ru.bogdsvn.grcp.profile.ProfileOuterClass.Profile.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ru.bogdsvn.grcp.profile.ProfileOuterClass.Preference.getDefaultInstance()))
              .setSchemaDescriptor(new ProfileServiceMethodDescriptorSupplier("getPreference"))
              .build();
        }
      }
    }
    return getGetPreferenceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ru.bogdsvn.grcp.profile.ProfileOuterClass.Profile,
      ru.bogdsvn.grcp.profile.ProfileOuterClass.Bio> getGetBioMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getBio",
      requestType = ru.bogdsvn.grcp.profile.ProfileOuterClass.Profile.class,
      responseType = ru.bogdsvn.grcp.profile.ProfileOuterClass.Bio.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ru.bogdsvn.grcp.profile.ProfileOuterClass.Profile,
      ru.bogdsvn.grcp.profile.ProfileOuterClass.Bio> getGetBioMethod() {
    io.grpc.MethodDescriptor<ru.bogdsvn.grcp.profile.ProfileOuterClass.Profile, ru.bogdsvn.grcp.profile.ProfileOuterClass.Bio> getGetBioMethod;
    if ((getGetBioMethod = ProfileServiceGrpc.getGetBioMethod) == null) {
      synchronized (ProfileServiceGrpc.class) {
        if ((getGetBioMethod = ProfileServiceGrpc.getGetBioMethod) == null) {
          ProfileServiceGrpc.getGetBioMethod = getGetBioMethod =
              io.grpc.MethodDescriptor.<ru.bogdsvn.grcp.profile.ProfileOuterClass.Profile, ru.bogdsvn.grcp.profile.ProfileOuterClass.Bio>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getBio"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ru.bogdsvn.grcp.profile.ProfileOuterClass.Profile.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ru.bogdsvn.grcp.profile.ProfileOuterClass.Bio.getDefaultInstance()))
              .setSchemaDescriptor(new ProfileServiceMethodDescriptorSupplier("getBio"))
              .build();
        }
      }
    }
    return getGetBioMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ru.bogdsvn.grcp.profile.ProfileOuterClass.Profile,
      ru.bogdsvn.grcp.profile.ProfileOuterClass.Response> getSaveProfileMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "saveProfile",
      requestType = ru.bogdsvn.grcp.profile.ProfileOuterClass.Profile.class,
      responseType = ru.bogdsvn.grcp.profile.ProfileOuterClass.Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ru.bogdsvn.grcp.profile.ProfileOuterClass.Profile,
      ru.bogdsvn.grcp.profile.ProfileOuterClass.Response> getSaveProfileMethod() {
    io.grpc.MethodDescriptor<ru.bogdsvn.grcp.profile.ProfileOuterClass.Profile, ru.bogdsvn.grcp.profile.ProfileOuterClass.Response> getSaveProfileMethod;
    if ((getSaveProfileMethod = ProfileServiceGrpc.getSaveProfileMethod) == null) {
      synchronized (ProfileServiceGrpc.class) {
        if ((getSaveProfileMethod = ProfileServiceGrpc.getSaveProfileMethod) == null) {
          ProfileServiceGrpc.getSaveProfileMethod = getSaveProfileMethod =
              io.grpc.MethodDescriptor.<ru.bogdsvn.grcp.profile.ProfileOuterClass.Profile, ru.bogdsvn.grcp.profile.ProfileOuterClass.Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "saveProfile"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ru.bogdsvn.grcp.profile.ProfileOuterClass.Profile.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ru.bogdsvn.grcp.profile.ProfileOuterClass.Response.getDefaultInstance()))
              .setSchemaDescriptor(new ProfileServiceMethodDescriptorSupplier("saveProfile"))
              .build();
        }
      }
    }
    return getSaveProfileMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ProfileServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ProfileServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ProfileServiceStub>() {
        @java.lang.Override
        public ProfileServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ProfileServiceStub(channel, callOptions);
        }
      };
    return ProfileServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ProfileServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ProfileServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ProfileServiceBlockingStub>() {
        @java.lang.Override
        public ProfileServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ProfileServiceBlockingStub(channel, callOptions);
        }
      };
    return ProfileServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ProfileServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ProfileServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ProfileServiceFutureStub>() {
        @java.lang.Override
        public ProfileServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ProfileServiceFutureStub(channel, callOptions);
        }
      };
    return ProfileServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void getPreference(ru.bogdsvn.grcp.profile.ProfileOuterClass.Profile request,
        io.grpc.stub.StreamObserver<ru.bogdsvn.grcp.profile.ProfileOuterClass.Preference> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetPreferenceMethod(), responseObserver);
    }

    /**
     */
    default void getBio(ru.bogdsvn.grcp.profile.ProfileOuterClass.Profile request,
        io.grpc.stub.StreamObserver<ru.bogdsvn.grcp.profile.ProfileOuterClass.Bio> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetBioMethod(), responseObserver);
    }

    /**
     */
    default void saveProfile(ru.bogdsvn.grcp.profile.ProfileOuterClass.Profile request,
        io.grpc.stub.StreamObserver<ru.bogdsvn.grcp.profile.ProfileOuterClass.Response> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSaveProfileMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service ProfileService.
   */
  public static abstract class ProfileServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ProfileServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service ProfileService.
   */
  public static final class ProfileServiceStub
      extends io.grpc.stub.AbstractAsyncStub<ProfileServiceStub> {
    private ProfileServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProfileServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ProfileServiceStub(channel, callOptions);
    }

    /**
     */
    public void getPreference(ru.bogdsvn.grcp.profile.ProfileOuterClass.Profile request,
        io.grpc.stub.StreamObserver<ru.bogdsvn.grcp.profile.ProfileOuterClass.Preference> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetPreferenceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getBio(ru.bogdsvn.grcp.profile.ProfileOuterClass.Profile request,
        io.grpc.stub.StreamObserver<ru.bogdsvn.grcp.profile.ProfileOuterClass.Bio> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetBioMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void saveProfile(ru.bogdsvn.grcp.profile.ProfileOuterClass.Profile request,
        io.grpc.stub.StreamObserver<ru.bogdsvn.grcp.profile.ProfileOuterClass.Response> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSaveProfileMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service ProfileService.
   */
  public static final class ProfileServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ProfileServiceBlockingStub> {
    private ProfileServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProfileServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ProfileServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public ru.bogdsvn.grcp.profile.ProfileOuterClass.Preference getPreference(ru.bogdsvn.grcp.profile.ProfileOuterClass.Profile request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetPreferenceMethod(), getCallOptions(), request);
    }

    /**
     */
    public ru.bogdsvn.grcp.profile.ProfileOuterClass.Bio getBio(ru.bogdsvn.grcp.profile.ProfileOuterClass.Profile request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetBioMethod(), getCallOptions(), request);
    }

    /**
     */
    public ru.bogdsvn.grcp.profile.ProfileOuterClass.Response saveProfile(ru.bogdsvn.grcp.profile.ProfileOuterClass.Profile request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSaveProfileMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service ProfileService.
   */
  public static final class ProfileServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<ProfileServiceFutureStub> {
    private ProfileServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProfileServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ProfileServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ru.bogdsvn.grcp.profile.ProfileOuterClass.Preference> getPreference(
        ru.bogdsvn.grcp.profile.ProfileOuterClass.Profile request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetPreferenceMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ru.bogdsvn.grcp.profile.ProfileOuterClass.Bio> getBio(
        ru.bogdsvn.grcp.profile.ProfileOuterClass.Profile request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetBioMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ru.bogdsvn.grcp.profile.ProfileOuterClass.Response> saveProfile(
        ru.bogdsvn.grcp.profile.ProfileOuterClass.Profile request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSaveProfileMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_PREFERENCE = 0;
  private static final int METHODID_GET_BIO = 1;
  private static final int METHODID_SAVE_PROFILE = 2;

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
        case METHODID_GET_PREFERENCE:
          serviceImpl.getPreference((ru.bogdsvn.grcp.profile.ProfileOuterClass.Profile) request,
              (io.grpc.stub.StreamObserver<ru.bogdsvn.grcp.profile.ProfileOuterClass.Preference>) responseObserver);
          break;
        case METHODID_GET_BIO:
          serviceImpl.getBio((ru.bogdsvn.grcp.profile.ProfileOuterClass.Profile) request,
              (io.grpc.stub.StreamObserver<ru.bogdsvn.grcp.profile.ProfileOuterClass.Bio>) responseObserver);
          break;
        case METHODID_SAVE_PROFILE:
          serviceImpl.saveProfile((ru.bogdsvn.grcp.profile.ProfileOuterClass.Profile) request,
              (io.grpc.stub.StreamObserver<ru.bogdsvn.grcp.profile.ProfileOuterClass.Response>) responseObserver);
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
          getGetPreferenceMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              ru.bogdsvn.grcp.profile.ProfileOuterClass.Profile,
              ru.bogdsvn.grcp.profile.ProfileOuterClass.Preference>(
                service, METHODID_GET_PREFERENCE)))
        .addMethod(
          getGetBioMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              ru.bogdsvn.grcp.profile.ProfileOuterClass.Profile,
              ru.bogdsvn.grcp.profile.ProfileOuterClass.Bio>(
                service, METHODID_GET_BIO)))
        .addMethod(
          getSaveProfileMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              ru.bogdsvn.grcp.profile.ProfileOuterClass.Profile,
              ru.bogdsvn.grcp.profile.ProfileOuterClass.Response>(
                service, METHODID_SAVE_PROFILE)))
        .build();
  }

  private static abstract class ProfileServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ProfileServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return ru.bogdsvn.grcp.profile.ProfileOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ProfileService");
    }
  }

  private static final class ProfileServiceFileDescriptorSupplier
      extends ProfileServiceBaseDescriptorSupplier {
    ProfileServiceFileDescriptorSupplier() {}
  }

  private static final class ProfileServiceMethodDescriptorSupplier
      extends ProfileServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    ProfileServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (ProfileServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ProfileServiceFileDescriptorSupplier())
              .addMethod(getGetPreferenceMethod())
              .addMethod(getGetBioMethod())
              .addMethod(getSaveProfileMethod())
              .build();
        }
      }
    }
    return result;
  }
}
