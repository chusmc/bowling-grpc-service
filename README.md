# bowling-grpc-service
POC of a gprc service using the Micronaut grpc integration, using as an example the bowling score Kata

Main challenge was to make the grpc-validation work using an open source project in https://github.com/envoyproxy/protoc-gen-validate 
There was some confusing documentation on how to configure the code generation in gradle
And finding out that I had to create a io.grpc.ServerInterceptor bean, to provide the validation layer.


