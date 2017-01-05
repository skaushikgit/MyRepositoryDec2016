package com.example.saurabhkaushik.ebatespractise.rest;

import com.example.saurabhkaushik.ebatespractise.model.Casestudy;
import com.example.saurabhkaushik.ebatespractise.model.CasestudylistModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by saurabhkaushik on 01/12/16.
 */
public interface ApiInterface {
    @GET
    Call<Casestudy> getCaseStudy(@Query("api_key") String apikey);

    @GET
    Call<CasestudylistModel> getListOfCaseStudy(@Query("api_key") String apikey);
}
