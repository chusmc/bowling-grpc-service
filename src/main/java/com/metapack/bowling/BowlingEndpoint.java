package com.metapack.bowling;

import io.grpc.stub.StreamObserver;
import javax.inject.Singleton;

@Singleton
public class BowlingEndpoint extends BowlingScoreGrpc.BowlingScoreImplBase {

    private final BowlingService bowlingService;

    public BowlingEndpoint(BowlingService bowlingService) {
        this.bowlingService = bowlingService;
    }

    @Override
    public void rollBall(com.metapack.bowling.RollBallRequest request,
                         StreamObserver<com.metapack.bowling.RollBallReply> responseObserver) {

        final FrameResult frameResult = bowlingService.rollBall(request.getScoreCard().getScoreCardId(), request.getNumberOfPins());

        RollBallReply reply = RollBallReply.newBuilder().setCurrentFrame(frameResult.getFrameNumber()).setScore(frameResult.getScore()).setScoreCard(
            ScoreCard.newBuilder().setScoreCardId(request.getScoreCard().getScoreCardId()).build()).build();

        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void score(com.metapack.bowling.ScoreRequest request,
          io.grpc.stub.StreamObserver<com.metapack.bowling.ScoreReply> responseObserver) {
        final int score = bowlingService.getScore(request.getScoreCard().getScoreCardId());

        ScoreReply reply = ScoreReply.newBuilder().setScore(score).build();

        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
