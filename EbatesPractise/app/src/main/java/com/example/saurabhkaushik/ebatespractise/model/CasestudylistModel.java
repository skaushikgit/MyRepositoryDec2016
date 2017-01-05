package com.example.saurabhkaushik.ebatespractise.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saurabhkaushik on 30/11/16.
 */
public class CasestudylistModel {
    @SerializedName("casestudies")
    @Expose
    private List<Casestudy> casestudies = new ArrayList<Casestudy>();

    /**
     *
     * @return
     * The casestudies
     */
    public List<Casestudy> getCasestudies() {
        return casestudies;
    }

    /**
     *
     * @param casestudies
     * The casestudies
     */
    public void setCasestudies(List<Casestudy> casestudies) {
        this.casestudies = casestudies;
    }
}
