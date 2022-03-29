package com.cartservice.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.45.0)",
    comments = "Source: schema.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class CartServiceGrpc {

  private CartServiceGrpc() {}

  public static final String SERVICE_NAME = "com.cartgateway.grpc.compiled.CartService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.cartservice.grpc.Request,
      com.cartservice.grpc.ListOfItems> getAddToCartMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "addToCart",
      requestType = com.cartservice.grpc.Request.class,
      responseType = com.cartservice.grpc.ListOfItems.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cartservice.grpc.Request,
      com.cartservice.grpc.ListOfItems> getAddToCartMethod() {
    io.grpc.MethodDescriptor<com.cartservice.grpc.Request, com.cartservice.grpc.ListOfItems> getAddToCartMethod;
    if ((getAddToCartMethod = CartServiceGrpc.getAddToCartMethod) == null) {
      synchronized (CartServiceGrpc.class) {
        if ((getAddToCartMethod = CartServiceGrpc.getAddToCartMethod) == null) {
          CartServiceGrpc.getAddToCartMethod = getAddToCartMethod =
              io.grpc.MethodDescriptor.<com.cartservice.grpc.Request, com.cartservice.grpc.ListOfItems>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "addToCart"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cartservice.grpc.Request.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cartservice.grpc.ListOfItems.getDefaultInstance()))
              .setSchemaDescriptor(new CartServiceMethodDescriptorSupplier("addToCart"))
              .build();
        }
      }
    }
    return getAddToCartMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cartservice.grpc.Request,
      com.cartservice.grpc.ListOfItems> getListAllItemsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "listAllItems",
      requestType = com.cartservice.grpc.Request.class,
      responseType = com.cartservice.grpc.ListOfItems.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cartservice.grpc.Request,
      com.cartservice.grpc.ListOfItems> getListAllItemsMethod() {
    io.grpc.MethodDescriptor<com.cartservice.grpc.Request, com.cartservice.grpc.ListOfItems> getListAllItemsMethod;
    if ((getListAllItemsMethod = CartServiceGrpc.getListAllItemsMethod) == null) {
      synchronized (CartServiceGrpc.class) {
        if ((getListAllItemsMethod = CartServiceGrpc.getListAllItemsMethod) == null) {
          CartServiceGrpc.getListAllItemsMethod = getListAllItemsMethod =
              io.grpc.MethodDescriptor.<com.cartservice.grpc.Request, com.cartservice.grpc.ListOfItems>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "listAllItems"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cartservice.grpc.Request.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cartservice.grpc.ListOfItems.getDefaultInstance()))
              .setSchemaDescriptor(new CartServiceMethodDescriptorSupplier("listAllItems"))
              .build();
        }
      }
    }
    return getListAllItemsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cartservice.grpc.Request,
      com.cartservice.grpc.ListOfItems> getDeleteItemMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "deleteItem",
      requestType = com.cartservice.grpc.Request.class,
      responseType = com.cartservice.grpc.ListOfItems.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cartservice.grpc.Request,
      com.cartservice.grpc.ListOfItems> getDeleteItemMethod() {
    io.grpc.MethodDescriptor<com.cartservice.grpc.Request, com.cartservice.grpc.ListOfItems> getDeleteItemMethod;
    if ((getDeleteItemMethod = CartServiceGrpc.getDeleteItemMethod) == null) {
      synchronized (CartServiceGrpc.class) {
        if ((getDeleteItemMethod = CartServiceGrpc.getDeleteItemMethod) == null) {
          CartServiceGrpc.getDeleteItemMethod = getDeleteItemMethod =
              io.grpc.MethodDescriptor.<com.cartservice.grpc.Request, com.cartservice.grpc.ListOfItems>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "deleteItem"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cartservice.grpc.Request.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cartservice.grpc.ListOfItems.getDefaultInstance()))
              .setSchemaDescriptor(new CartServiceMethodDescriptorSupplier("deleteItem"))
              .build();
        }
      }
    }
    return getDeleteItemMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cartservice.grpc.Request,
      com.cartservice.grpc.ListOfItems> getUpdateItemOnTheCartMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "updateItemOnTheCart",
      requestType = com.cartservice.grpc.Request.class,
      responseType = com.cartservice.grpc.ListOfItems.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cartservice.grpc.Request,
      com.cartservice.grpc.ListOfItems> getUpdateItemOnTheCartMethod() {
    io.grpc.MethodDescriptor<com.cartservice.grpc.Request, com.cartservice.grpc.ListOfItems> getUpdateItemOnTheCartMethod;
    if ((getUpdateItemOnTheCartMethod = CartServiceGrpc.getUpdateItemOnTheCartMethod) == null) {
      synchronized (CartServiceGrpc.class) {
        if ((getUpdateItemOnTheCartMethod = CartServiceGrpc.getUpdateItemOnTheCartMethod) == null) {
          CartServiceGrpc.getUpdateItemOnTheCartMethod = getUpdateItemOnTheCartMethod =
              io.grpc.MethodDescriptor.<com.cartservice.grpc.Request, com.cartservice.grpc.ListOfItems>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "updateItemOnTheCart"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cartservice.grpc.Request.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cartservice.grpc.ListOfItems.getDefaultInstance()))
              .setSchemaDescriptor(new CartServiceMethodDescriptorSupplier("updateItemOnTheCart"))
              .build();
        }
      }
    }
    return getUpdateItemOnTheCartMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cartservice.grpc.Order,
      com.cartservice.grpc.Order> getCheckOutMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "checkOut",
      requestType = com.cartservice.grpc.Order.class,
      responseType = com.cartservice.grpc.Order.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cartservice.grpc.Order,
      com.cartservice.grpc.Order> getCheckOutMethod() {
    io.grpc.MethodDescriptor<com.cartservice.grpc.Order, com.cartservice.grpc.Order> getCheckOutMethod;
    if ((getCheckOutMethod = CartServiceGrpc.getCheckOutMethod) == null) {
      synchronized (CartServiceGrpc.class) {
        if ((getCheckOutMethod = CartServiceGrpc.getCheckOutMethod) == null) {
          CartServiceGrpc.getCheckOutMethod = getCheckOutMethod =
              io.grpc.MethodDescriptor.<com.cartservice.grpc.Order, com.cartservice.grpc.Order>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "checkOut"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cartservice.grpc.Order.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cartservice.grpc.Order.getDefaultInstance()))
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
    public void addToCart(com.cartservice.grpc.Request request,
        io.grpc.stub.StreamObserver<com.cartservice.grpc.ListOfItems> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAddToCartMethod(), responseObserver);
    }

    /**
     */
    public void listAllItems(com.cartservice.grpc.Request request,
        io.grpc.stub.StreamObserver<com.cartservice.grpc.ListOfItems> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListAllItemsMethod(), responseObserver);
    }

    /**
     */
    public void deleteItem(com.cartservice.grpc.Request request,
        io.grpc.stub.StreamObserver<com.cartservice.grpc.ListOfItems> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteItemMethod(), responseObserver);
    }

    /**
     */
    public void updateItemOnTheCart(com.cartservice.grpc.Request request,
        io.grpc.stub.StreamObserver<com.cartservice.grpc.ListOfItems> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateItemOnTheCartMethod(), responseObserver);
    }

    /**
     */
    public void checkOut(com.cartservice.grpc.Order request,
        io.grpc.stub.StreamObserver<com.cartservice.grpc.Order> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCheckOutMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAddToCartMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.cartservice.grpc.Request,
                com.cartservice.grpc.ListOfItems>(
                  this, METHODID_ADD_TO_CART)))
          .addMethod(
            getListAllItemsMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.cartservice.grpc.Request,
                com.cartservice.grpc.ListOfItems>(
                  this, METHODID_LIST_ALL_ITEMS)))
          .addMethod(
            getDeleteItemMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.cartservice.grpc.Request,
                com.cartservice.grpc.ListOfItems>(
                  this, METHODID_DELETE_ITEM)))
          .addMethod(
            getUpdateItemOnTheCartMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.cartservice.grpc.Request,
                com.cartservice.grpc.ListOfItems>(
                  this, METHODID_UPDATE_ITEM_ON_THE_CART)))
          .addMethod(
            getCheckOutMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.cartservice.grpc.Order,
                com.cartservice.grpc.Order>(
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
    public void addToCart(com.cartservice.grpc.Request request,
        io.grpc.stub.StreamObserver<com.cartservice.grpc.ListOfItems> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAddToCartMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listAllItems(com.cartservice.grpc.Request request,
        io.grpc.stub.StreamObserver<com.cartservice.grpc.ListOfItems> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListAllItemsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteItem(com.cartservice.grpc.Request request,
        io.grpc.stub.StreamObserver<com.cartservice.grpc.ListOfItems> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteItemMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateItemOnTheCart(com.cartservice.grpc.Request request,
        io.grpc.stub.StreamObserver<com.cartservice.grpc.ListOfItems> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateItemOnTheCartMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void checkOut(com.cartservice.grpc.Order request,
        io.grpc.stub.StreamObserver<com.cartservice.grpc.Order> responseObserver) {
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
    public com.cartservice.grpc.ListOfItems addToCart(com.cartservice.grpc.Request request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAddToCartMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.cartservice.grpc.ListOfItems listAllItems(com.cartservice.grpc.Request request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListAllItemsMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.cartservice.grpc.ListOfItems deleteItem(com.cartservice.grpc.Request request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteItemMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.cartservice.grpc.ListOfItems updateItemOnTheCart(com.cartservice.grpc.Request request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateItemOnTheCartMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.cartservice.grpc.Order checkOut(com.cartservice.grpc.Order request) {
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
    public com.google.common.util.concurrent.ListenableFuture<com.cartservice.grpc.ListOfItems> addToCart(
        com.cartservice.grpc.Request request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAddToCartMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cartservice.grpc.ListOfItems> listAllItems(
        com.cartservice.grpc.Request request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListAllItemsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cartservice.grpc.ListOfItems> deleteItem(
        com.cartservice.grpc.Request request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteItemMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cartservice.grpc.ListOfItems> updateItemOnTheCart(
        com.cartservice.grpc.Request request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateItemOnTheCartMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cartservice.grpc.Order> checkOut(
        com.cartservice.grpc.Order request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCheckOutMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ADD_TO_CART = 0;
  private static final int METHODID_LIST_ALL_ITEMS = 1;
  private static final int METHODID_DELETE_ITEM = 2;
  private static final int METHODID_UPDATE_ITEM_ON_THE_CART = 3;
  private static final int METHODID_CHECK_OUT = 4;

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
          serviceImpl.addToCart((com.cartservice.grpc.Request) request,
              (io.grpc.stub.StreamObserver<com.cartservice.grpc.ListOfItems>) responseObserver);
          break;
        case METHODID_LIST_ALL_ITEMS:
          serviceImpl.listAllItems((com.cartservice.grpc.Request) request,
              (io.grpc.stub.StreamObserver<com.cartservice.grpc.ListOfItems>) responseObserver);
          break;
        case METHODID_DELETE_ITEM:
          serviceImpl.deleteItem((com.cartservice.grpc.Request) request,
              (io.grpc.stub.StreamObserver<com.cartservice.grpc.ListOfItems>) responseObserver);
          break;
        case METHODID_UPDATE_ITEM_ON_THE_CART:
          serviceImpl.updateItemOnTheCart((com.cartservice.grpc.Request) request,
              (io.grpc.stub.StreamObserver<com.cartservice.grpc.ListOfItems>) responseObserver);
          break;
        case METHODID_CHECK_OUT:
          serviceImpl.checkOut((com.cartservice.grpc.Order) request,
              (io.grpc.stub.StreamObserver<com.cartservice.grpc.Order>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
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
      return com.cartservice.grpc.GrpcProto.getDescriptor();
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
              .addMethod(getListAllItemsMethod())
              .addMethod(getDeleteItemMethod())
              .addMethod(getUpdateItemOnTheCartMethod())
              .addMethod(getCheckOutMethod())
              .build();
        }
      }
    }
    return result;
  }
}
