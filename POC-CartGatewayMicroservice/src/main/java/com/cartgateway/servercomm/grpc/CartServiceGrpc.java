package com.cartgateway.servercomm.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.45.0)",
    comments = "Source: schema.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class CartServiceGrpc {

  private CartServiceGrpc() {}

  public static final String SERVICE_NAME = "com.cart.grpc.compiled.CartService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.cartgateway.servercomm.grpc.Request,
      com.cartgateway.servercomm.grpc.ListResponse> getAddToCartMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "addToCart",
      requestType = com.cartgateway.servercomm.grpc.Request.class,
      responseType = com.cartgateway.servercomm.grpc.ListResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cartgateway.servercomm.grpc.Request,
      com.cartgateway.servercomm.grpc.ListResponse> getAddToCartMethod() {
    io.grpc.MethodDescriptor<com.cartgateway.servercomm.grpc.Request, com.cartgateway.servercomm.grpc.ListResponse> getAddToCartMethod;
    if ((getAddToCartMethod = CartServiceGrpc.getAddToCartMethod) == null) {
      synchronized (CartServiceGrpc.class) {
        if ((getAddToCartMethod = CartServiceGrpc.getAddToCartMethod) == null) {
          CartServiceGrpc.getAddToCartMethod = getAddToCartMethod =
              io.grpc.MethodDescriptor.<com.cartgateway.servercomm.grpc.Request, com.cartgateway.servercomm.grpc.ListResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "addToCart"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cartgateway.servercomm.grpc.Request.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cartgateway.servercomm.grpc.ListResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CartServiceMethodDescriptorSupplier("addToCart"))
              .build();
        }
      }
    }
    return getAddToCartMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cartgateway.servercomm.grpc.User,
      com.cartgateway.servercomm.grpc.ListResponse> getGetAllItemsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getAllItems",
      requestType = com.cartgateway.servercomm.grpc.User.class,
      responseType = com.cartgateway.servercomm.grpc.ListResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cartgateway.servercomm.grpc.User,
      com.cartgateway.servercomm.grpc.ListResponse> getGetAllItemsMethod() {
    io.grpc.MethodDescriptor<com.cartgateway.servercomm.grpc.User, com.cartgateway.servercomm.grpc.ListResponse> getGetAllItemsMethod;
    if ((getGetAllItemsMethod = CartServiceGrpc.getGetAllItemsMethod) == null) {
      synchronized (CartServiceGrpc.class) {
        if ((getGetAllItemsMethod = CartServiceGrpc.getGetAllItemsMethod) == null) {
          CartServiceGrpc.getGetAllItemsMethod = getGetAllItemsMethod =
              io.grpc.MethodDescriptor.<com.cartgateway.servercomm.grpc.User, com.cartgateway.servercomm.grpc.ListResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getAllItems"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cartgateway.servercomm.grpc.User.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cartgateway.servercomm.grpc.ListResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CartServiceMethodDescriptorSupplier("getAllItems"))
              .build();
        }
      }
    }
    return getGetAllItemsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cartgateway.servercomm.grpc.Request,
      com.cartgateway.servercomm.grpc.ListResponse> getRemoveItemMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "removeItem",
      requestType = com.cartgateway.servercomm.grpc.Request.class,
      responseType = com.cartgateway.servercomm.grpc.ListResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cartgateway.servercomm.grpc.Request,
      com.cartgateway.servercomm.grpc.ListResponse> getRemoveItemMethod() {
    io.grpc.MethodDescriptor<com.cartgateway.servercomm.grpc.Request, com.cartgateway.servercomm.grpc.ListResponse> getRemoveItemMethod;
    if ((getRemoveItemMethod = CartServiceGrpc.getRemoveItemMethod) == null) {
      synchronized (CartServiceGrpc.class) {
        if ((getRemoveItemMethod = CartServiceGrpc.getRemoveItemMethod) == null) {
          CartServiceGrpc.getRemoveItemMethod = getRemoveItemMethod =
              io.grpc.MethodDescriptor.<com.cartgateway.servercomm.grpc.Request, com.cartgateway.servercomm.grpc.ListResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "removeItem"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cartgateway.servercomm.grpc.Request.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cartgateway.servercomm.grpc.ListResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CartServiceMethodDescriptorSupplier("removeItem"))
              .build();
        }
      }
    }
    return getRemoveItemMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cartgateway.servercomm.grpc.User,
      com.cartgateway.servercomm.grpc.StringResult> getCheckOutMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "checkOut",
      requestType = com.cartgateway.servercomm.grpc.User.class,
      responseType = com.cartgateway.servercomm.grpc.StringResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cartgateway.servercomm.grpc.User,
      com.cartgateway.servercomm.grpc.StringResult> getCheckOutMethod() {
    io.grpc.MethodDescriptor<com.cartgateway.servercomm.grpc.User, com.cartgateway.servercomm.grpc.StringResult> getCheckOutMethod;
    if ((getCheckOutMethod = CartServiceGrpc.getCheckOutMethod) == null) {
      synchronized (CartServiceGrpc.class) {
        if ((getCheckOutMethod = CartServiceGrpc.getCheckOutMethod) == null) {
          CartServiceGrpc.getCheckOutMethod = getCheckOutMethod =
              io.grpc.MethodDescriptor.<com.cartgateway.servercomm.grpc.User, com.cartgateway.servercomm.grpc.StringResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "checkOut"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cartgateway.servercomm.grpc.User.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cartgateway.servercomm.grpc.StringResult.getDefaultInstance()))
              .setSchemaDescriptor(new CartServiceMethodDescriptorSupplier("checkOut"))
              .build();
        }
      }
    }
    return getCheckOutMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CartServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CartServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CartServiceStub>() {
        @java.lang.Override
        public CartServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CartServiceStub(channel, callOptions);
        }
      };
    return CartServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CartServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CartServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CartServiceBlockingStub>() {
        @java.lang.Override
        public CartServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CartServiceBlockingStub(channel, callOptions);
        }
      };
    return CartServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CartServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CartServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CartServiceFutureStub>() {
        @java.lang.Override
        public CartServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CartServiceFutureStub(channel, callOptions);
        }
      };
    return CartServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class CartServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void addToCart(com.cartgateway.servercomm.grpc.Request request,
        io.grpc.stub.StreamObserver<com.cartgateway.servercomm.grpc.ListResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAddToCartMethod(), responseObserver);
    }

    /**
     */
    public void getAllItems(com.cartgateway.servercomm.grpc.User request,
        io.grpc.stub.StreamObserver<com.cartgateway.servercomm.grpc.ListResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetAllItemsMethod(), responseObserver);
    }

    /**
     */
    public void removeItem(com.cartgateway.servercomm.grpc.Request request,
        io.grpc.stub.StreamObserver<com.cartgateway.servercomm.grpc.ListResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRemoveItemMethod(), responseObserver);
    }

    /**
     */
    public void checkOut(com.cartgateway.servercomm.grpc.User request,
        io.grpc.stub.StreamObserver<com.cartgateway.servercomm.grpc.StringResult> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCheckOutMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAddToCartMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.cartgateway.servercomm.grpc.Request,
                com.cartgateway.servercomm.grpc.ListResponse>(
                  this, METHODID_ADD_TO_CART)))
          .addMethod(
            getGetAllItemsMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.cartgateway.servercomm.grpc.User,
                com.cartgateway.servercomm.grpc.ListResponse>(
                  this, METHODID_GET_ALL_ITEMS)))
          .addMethod(
            getRemoveItemMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.cartgateway.servercomm.grpc.Request,
                com.cartgateway.servercomm.grpc.ListResponse>(
                  this, METHODID_REMOVE_ITEM)))
          .addMethod(
            getCheckOutMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.cartgateway.servercomm.grpc.User,
                com.cartgateway.servercomm.grpc.StringResult>(
                  this, METHODID_CHECK_OUT)))
          .build();
    }
  }

  /**
   */
  public static final class CartServiceStub extends io.grpc.stub.AbstractAsyncStub<CartServiceStub> {
    private CartServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CartServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CartServiceStub(channel, callOptions);
    }

    /**
     */
    public void addToCart(com.cartgateway.servercomm.grpc.Request request,
        io.grpc.stub.StreamObserver<com.cartgateway.servercomm.grpc.ListResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAddToCartMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getAllItems(com.cartgateway.servercomm.grpc.User request,
        io.grpc.stub.StreamObserver<com.cartgateway.servercomm.grpc.ListResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetAllItemsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void removeItem(com.cartgateway.servercomm.grpc.Request request,
        io.grpc.stub.StreamObserver<com.cartgateway.servercomm.grpc.ListResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRemoveItemMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void checkOut(com.cartgateway.servercomm.grpc.User request,
        io.grpc.stub.StreamObserver<com.cartgateway.servercomm.grpc.StringResult> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCheckOutMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class CartServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<CartServiceBlockingStub> {
    private CartServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CartServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CartServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.cartgateway.servercomm.grpc.ListResponse addToCart(com.cartgateway.servercomm.grpc.Request request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAddToCartMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.cartgateway.servercomm.grpc.ListResponse getAllItems(com.cartgateway.servercomm.grpc.User request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetAllItemsMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.cartgateway.servercomm.grpc.ListResponse removeItem(com.cartgateway.servercomm.grpc.Request request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRemoveItemMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.cartgateway.servercomm.grpc.StringResult checkOut(com.cartgateway.servercomm.grpc.User request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCheckOutMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class CartServiceFutureStub extends io.grpc.stub.AbstractFutureStub<CartServiceFutureStub> {
    private CartServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CartServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CartServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cartgateway.servercomm.grpc.ListResponse> addToCart(
        com.cartgateway.servercomm.grpc.Request request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAddToCartMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cartgateway.servercomm.grpc.ListResponse> getAllItems(
        com.cartgateway.servercomm.grpc.User request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetAllItemsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cartgateway.servercomm.grpc.ListResponse> removeItem(
        com.cartgateway.servercomm.grpc.Request request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRemoveItemMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cartgateway.servercomm.grpc.StringResult> checkOut(
        com.cartgateway.servercomm.grpc.User request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCheckOutMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ADD_TO_CART = 0;
  private static final int METHODID_GET_ALL_ITEMS = 1;
  private static final int METHODID_REMOVE_ITEM = 2;
  private static final int METHODID_CHECK_OUT = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final CartServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(CartServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ADD_TO_CART:
          serviceImpl.addToCart((com.cartgateway.servercomm.grpc.Request) request,
              (io.grpc.stub.StreamObserver<com.cartgateway.servercomm.grpc.ListResponse>) responseObserver);
          break;
        case METHODID_GET_ALL_ITEMS:
          serviceImpl.getAllItems((com.cartgateway.servercomm.grpc.User) request,
              (io.grpc.stub.StreamObserver<com.cartgateway.servercomm.grpc.ListResponse>) responseObserver);
          break;
        case METHODID_REMOVE_ITEM:
          serviceImpl.removeItem((com.cartgateway.servercomm.grpc.Request) request,
              (io.grpc.stub.StreamObserver<com.cartgateway.servercomm.grpc.ListResponse>) responseObserver);
          break;
        case METHODID_CHECK_OUT:
          serviceImpl.checkOut((com.cartgateway.servercomm.grpc.User) request,
              (io.grpc.stub.StreamObserver<com.cartgateway.servercomm.grpc.StringResult>) responseObserver);
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

  private static abstract class CartServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CartServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.cartgateway.servercomm.grpc.GrpcProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("CartService");
    }
  }

  private static final class CartServiceFileDescriptorSupplier
      extends CartServiceBaseDescriptorSupplier {
    CartServiceFileDescriptorSupplier() {}
  }

  private static final class CartServiceMethodDescriptorSupplier
      extends CartServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    CartServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (CartServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CartServiceFileDescriptorSupplier())
              .addMethod(getAddToCartMethod())
              .addMethod(getGetAllItemsMethod())
              .addMethod(getRemoveItemMethod())
              .addMethod(getCheckOutMethod())
              .build();
        }
      }
    }
    return result;
  }
}
