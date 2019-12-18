package com.metapack.bowling;

import com.google.common.base.Preconditions;
import java.util.Optional;

class FramePlay {
    int firstRollPins = 0;
    Integer secondRollPins;

    public static FramePlay newFramePlay(int pins) {
        Preconditions.checkArgument(pins > 0 && pins <= 10);

        FramePlay framePlay = new FramePlay();
        framePlay.firstRollPins = pins;

        return framePlay;
    }

    public boolean isComplete() {
        return secondRollPins != null || firstRollPins == 10;
    }

    public int getScore() {
        return  firstRollPins + Optional.ofNullable(secondRollPins).orElse(0);
    }

    boolean isStrike() {
        return (firstRollPins == 10 && secondRollPins == null);
    }

    boolean isSpare() {
        return (secondRollPins != null && secondRollPins + firstRollPins == 10);
    }


    public FramePlay secondRoll(int pins) {
        Preconditions.checkArgument(!isComplete());
        Preconditions.checkArgument(pins > 0 && pins + firstRollPins <= 10);

        FramePlay framePlay = new FramePlay();
        framePlay.firstRollPins = this.firstRollPins;
        framePlay.secondRollPins = pins;

        return framePlay;
    }
}
