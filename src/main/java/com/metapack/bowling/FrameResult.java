package com.metapack.bowling;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FrameResult {
    private int score;
    private int frameNumber;
}
