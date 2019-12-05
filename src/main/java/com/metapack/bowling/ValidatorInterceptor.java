package com.metapack.bowling;

import io.envoyproxy.pgv.ReflectiveValidatorIndex;
import io.envoyproxy.pgv.grpc.ValidatingServerInterceptor;
import io.grpc.ServerInterceptor;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;


@Factory
public class ValidatorInterceptor  {
    @Bean
    ServerInterceptor validatorInterceptor() {
        return new ValidatingServerInterceptor(new ReflectiveValidatorIndex());
    }
}
