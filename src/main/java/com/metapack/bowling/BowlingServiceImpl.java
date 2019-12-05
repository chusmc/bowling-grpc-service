package com.metapack.bowling;

import javax.inject.Singleton;

@Singleton
public class BowlingServiceImpl implements BowlingService {
    @Override
    public FrameResult rollBall(String scoreCardId, int numberOfPins) {
        return null;

    }

    @Override
    public int getScore(String scoreCardId, int frame) {
        return 0;
    }

    @Override
    public int getScore(String scoreCardId) {
        return 0;
    }
}
