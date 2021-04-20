package com.ydl.examantion.vo;


import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;

@Data
@Accessors(chain = true)
public class PaperResVo {
    private PaperAttribute paperAttribute;

    private ArrayList<ProblemResVo> problemList;

    private PaperDetails paperDetails;
}
