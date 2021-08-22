/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escuelaing.edu.co.arep.mvngit.entity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 *
 * @author Juan Sebastian
 */
public class ActionResponseApi {
    private String name;
    private StringBuffer responseApiData;
    private String urlApi;
    private final String USER_AGENT = "Mozilla/5.0";

    public ActionResponseApi(String name, String urlApi) {
        this.name = name;
        this.urlApi = urlApi;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StringBuffer getResponseApiData() {
        return responseApiData;
    }

    public void setResponseApiData(StringBuffer responseApiData) {
        this.responseApiData = responseApiData;
    }

    public String getUrlApi() {
        return urlApi;
    }

    public void setUrlApi(String urlApi) {
        this.urlApi = urlApi;
    }
    
    public void consultar() throws MalformedURLException, ProtocolException, IOException{
        URL obj = new URL(urlApi);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        
        //The following invocation perform the connection implicitly before getting the code
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        
        StringBuffer response = new StringBuffer();
        
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("GET request not worked");
        }
        this.responseApiData =  response;
    }

    @Override
    public String toString() {
        return "ActionResponseApi{" + "name=" + name + ", responseApiData=" + responseApiData + ", urlApi=" + urlApi + ", USER_AGENT=" + USER_AGENT + '}';
    }

}
