/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escuelaing.edu.co.arep.mvngit.controller;

import com.escuelaing.edu.co.arep.mvngit.controller.reponse.StandardResponse;
import com.escuelaing.edu.co.arep.mvngit.controller.reponse.StatusResponse;
import com.escuelaing.edu.co.arep.mvngit.facade.FacadeAction;
import com.escuelaing.edu.co.arep.mvngit.service.AlphaServiceImpl;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.json.JSONObject;
import spark.Request;
import spark.Response;
import static spark.Spark.*;

/**
 *
 * @author Juan Sebastian
 */
// https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=IBM&interval=1min&apikey=CX3DG08ISX9HLJCV Intra-day
// https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol="+name+"&apikey=CX3DG08ISX9HLJCV Diaria
// https://www.alphavantage.co/query?function=TIME_SERIES_WEEKLY&symbol=IBM&apikey=CX3DG08ISX9HLJCV Semanal
// https://www.alphavantage.co/query?function=TIME_SERIES_MONTHLY&symbol=IBM&apikey=CX3DG08ISX9HLJCV Mensual
public class ActionController {

    FacadeAction facadeAction;
    AlphaServiceImpl alphaService;

    public ActionController() {

        get("/action/day/:name", (Request req, Response res) -> {
            String name = req.params(":name");
            facadeAction = new FacadeAction(name, "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" + name + "&apikey=CX3DG08ISX9HLJCV");
            alphaService = new AlphaServiceImpl(facadeAction.consultar());

            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(alphaService.formResponseDay().toString());
            
            res.type("application/json");
            return new Gson().toJson(
                    new StandardResponse(StatusResponse.SUCCESS, jsonElement));

        });
        
        get("/action/week/:name", (Request req, Response res) -> {
            String name = req.params(":name");
            facadeAction = new FacadeAction(name, "https://www.alphavantage.co/query?function=TIME_SERIES_WEEKLY&symbol=" + name + "&apikey=CX3DG08ISX9HLJCV");
            alphaService = new AlphaServiceImpl(facadeAction.consultar());

            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(alphaService.formResponseDay().toString());
            
            res.type("application/json");
            return new Gson().toJson(
                    new StandardResponse(StatusResponse.SUCCESS, jsonElement));

        });
        
        
        get("/action/month/:name", (Request req, Response res) -> {
            String name = req.params(":name");
            facadeAction = new FacadeAction(name, "https://www.alphavantage.co/query?function=TIME_SERIES_MONTHLY&symbol=" + name + "&apikey=CX3DG08ISX9HLJCV");
            alphaService = new AlphaServiceImpl(facadeAction.consultar());

            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(alphaService.formResponseDay().toString());
            
            res.type("application/json");
            return new Gson().toJson(
                    new StandardResponse(StatusResponse.SUCCESS, jsonElement));

        });
        
        get("/action/interday/:name", (Request req, Response res) -> {
            String name = req.params(":name");
            facadeAction = new FacadeAction(name, "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=" + name + "&interval=1min&apikey=CX3DG08ISX9HLJCV");
            alphaService = new AlphaServiceImpl(facadeAction.consultar());

            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(alphaService.formResponseDay().toString());
            
            res.type("application/json");
            return new Gson().toJson(
                    new StandardResponse(StatusResponse.SUCCESS, jsonElement));

        });
        
    }

}
