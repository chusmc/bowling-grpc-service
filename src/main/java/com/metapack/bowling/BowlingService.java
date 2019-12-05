package com.metapack.bowling;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public interface BowlingService  {
    FrameResult rollBall(String scoreCardId, @Max(10) @Min(0) int numberOfPins);
    int getScore(String scoreCardId, @Max(10) @Min(0) int frame);
    int getScore(String scoreCardId);
}
