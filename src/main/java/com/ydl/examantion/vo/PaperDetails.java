package com.ydl.examantion.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PaperDetails {
    private String testPaperName;

    private String subject;

    private String startTime;

    private String endTime;
}
