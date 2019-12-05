package com.metapack.bowling;
import io.grpc.ManagedChannel;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.micronaut.context.annotation.*;
import io.micronaut.grpc.annotation.GrpcChannel;
import io.micronaut.grpc.server.GrpcServerChannel;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import javax.inject.Inject;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.function.Executable;

@MicronautTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BowlingEndpointTest {

    @Inject
    BowlingScoreGrpc.BowlingScoreBlockingStub blockingStub;

    @Test
    @DisplayName("Given a scoreCard, when I knock an invalid number of pins, then INVALID_ARGUMENT error is thrown")
    void testRollingBallWithInvalidNumberOfPinsShouldReturnAnError() {
        final RollBallRequest rollBallRequest = RollBallRequest.newBuilder()
            .setScoreCard(ScoreCard.newBuilder().setScoreCardId("TestScore").build())
            .setNumberOfPins(11)
            .build();

        Executable call = () -> blockingStub.rollBall(rollBallRequest);
        StatusRuntimeException error = assertThrows(StatusRuntimeException.class, call);
        assertEquals(Status.INVALID_ARGUMENT.getCode(), error.getStatus().getCode());
    }

    @Test
    @Order(1)
    @DisplayName("Given a scoreCard, when I roll the first ball and knock some pins, then the score for the frame should be the number of pins")
    void testFirstRollingBallWithValidNumberOfPinsShouldReturnFirstFrameWithScore() {
        final RollBallRequest rollBallRequest = RollBallRequest.newBuilder()
            .setScoreCard(ScoreCard.newBuilder().setScoreCardId("TestScore").build())
            .setNumberOfPins(1)
            .build();

        RollBallReply reply = blockingStub.rollBall(rollBallRequest);
        assertEquals(1, reply.getCurrentFrame());
        assertEquals( 1, reply.getScore());
    }

    @Test
    @Order(2)
    @DisplayName("Given a scoreCard, when I roll the second ball and knock some pins, then the score for the frame should be the sum of the first and the second number of pins")
    void testSecondRollingBallWithValidNumberOfPinsShouldReturnFirstFrameWithScore() {
        final RollBallRequest rollBallRequest = RollBallRequest.newBuilder()
            .setScoreCard(ScoreCard.newBuilder().setScoreCardId("TestScore").build())
            .setNumberOfPins(4)
            .build();

        RollBallReply reply = blockingStub.rollBall(rollBallRequest);
        assertEquals(1, reply.getCurrentFrame());
        assertEquals( 5, reply.getScore());
    }
}

@Factory
class Clients {
    @Bean
    BowlingScoreGrpc.BowlingScoreBlockingStub blockingStub(@GrpcChannel(GrpcServerChannel.NAME) ManagedChannel channel) {
        return BowlingScoreGrpc.newBlockingStub(channel);
    }
}
