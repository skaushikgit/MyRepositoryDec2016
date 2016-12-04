package com.example.saurabhkaushik.skeletonapplication.Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saurabhkaushik on 03/12/16.
 */

public class CaseStudyListModel {
    List<CaseStudyModel> list = new ArrayList<>();

    public List<CaseStudyModel> getList() {
        return list;
    }

    public void setList(List<CaseStudyModel> list) {
        this.list = list;
    }
}
