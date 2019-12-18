package com.metapack.bowling;

import com.google.common.collect.Iterables;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import javax.inject.Singleton;

@Singleton
public class BowlingServiceImpl implements BowlingService {

    private ConcurrentMap<String, List<FramePlay>> scoreCards = new ConcurrentHashMap<>();

    @Override
    public FrameResult rollBall(String scoreCardId, int numberOfPins) {
        List<FramePlay> framePlays = scoreCards.computeIfAbsent(scoreCardId, (key) -> Collections
            .emptyList());

        synchronized (framePlays) {
            FramePlay lastFrame = Iterables.getLast(framePlays);
            if (lastFrame.isComplete()) {
                framePlays.add(FramePlay.newFramePlay(numberOfPins));
            } else {
                lastFrame.secondRoll(numberOfPins);
            }
            return calculateFrameResult(framePlays, framePlays.size() - 1);
        }
    }

    @Override
    public int getScore(String scoreCardId, int frame) {
        return 0;
    }

    @Override
    public int getScore(String scoreCardId) {
        return 0;
    }

    private FrameResult calculateFrameResult(List<FramePlay> framePlays, int frameNumber) {
        if (framePlays
    }
}
