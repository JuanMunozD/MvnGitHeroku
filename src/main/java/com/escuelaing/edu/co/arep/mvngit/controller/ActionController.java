/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escuelaing.edu.co.arep.mvngit.controller;

import com.escuelaing.edu.co.arep.mvngit.cache.CacheHelper;
import com.escuelaing.edu.co.arep.mvngit.controller.reponse.StandardResponse;
import com.escuelaing.edu.co.arep.mvngit.controller.reponse.StatusResponse;
import com.escuelaing.edu.co.arep.mvngit.facade.FacadeAction;
import com.escuelaing.edu.co.arep.mvngit.service.AlphaServiceImpl;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheManagerBuilder;
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
    
    private CacheHelper cache = new CacheHelper();

    public ActionController() {

        get("/action/day/:name", (Request req, Response res) -> {
            String name = req.params(":name");
            
            if (cache.getFacadeActionCache().containsKey(name + " day")
            		&& !cache.getFacadeActionCache().get(name + " day").obtener().toString().contains("Error Message")
            		&& !cache.getFacadeActionCache().get(name + " day").obtener().toString().contains("Note")
            		) {
            	System.out.println("### SI entro cache ");
            	facadeAction = cache.getFacadeActionCache().get(name + " day");
            }else {
            	System.out.println("### NO entro cache ");
                facadeAction = new FacadeAction(name, "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" + name + "&apikey=CX3DG08ISX9HLJCV");
                // calcular api
                facadeAction.consultar();
                // guardar en cache 
                cache.getFacadeActionCache().put(name + " day", facadeAction);
            }
            
            alphaService = new AlphaServiceImpl(facadeAction.obtener());

            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(alphaService.formResponseDay().toString());
            
            res.type("application/json");
            return new Gson().toJson(
                    new StandardResponse(StatusResponse.SUCCESS, jsonElement));

        });
        
        get("/action/week/:name", (Request req, Response res) -> {
            String name = req.params(":name");
            
            if (cache.getFacadeActionCache().containsKey(name + " week")
            		&& !cache.getFacadeActionCache().get(name + " week").obtener().toString().contains("Error Message")
            		&& !cache.getFacadeActionCache().get(name + " week").obtener().toString().contains("Note")
            		) {
            	System.out.println("### SI entro cache ");
            	facadeAction = cache.getFacadeActionCache().get(name + " week");
            }else {
            	System.out.println("### NO entro cache ");
            	facadeAction = new FacadeAction(name, "https://www.alphavantage.co/query?function=TIME_SERIES_WEEKLY&symbol=" + name + "&apikey=CX3DG08ISX9HLJCV");
                // calcular api
                facadeAction.consultar();
                // guardar en cache 
                cache.getFacadeActionCache().put(name + " week", facadeAction);
            }
            alphaService = new AlphaServiceImpl(facadeAction.obtener());

            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(alphaService.formResponseDay().toString());
            
            res.type("application/json");
            return new Gson().toJson(
                    new StandardResponse(StatusResponse.SUCCESS, jsonElement));

        });
        
        
        get("/action/month/:name", (Request req, Response res) -> {
            String name = req.params(":name");

            if (cache.getFacadeActionCache().containsKey(name + " month")
            		&& !cache.getFacadeActionCache().get(name + " month").obtener().toString().contains("Error Message")
            		&& !cache.getFacadeActionCache().get(name + " month").obtener().toString().contains("Note")
            		) {
            	System.out.println("### SI entro cache ");
            	facadeAction = cache.getFacadeActionCache().get(name + " month");
            }else {
            	System.out.println("### NO entro cache ");
            	facadeAction = new FacadeAction(name, "https://www.alphavantage.co/query?function=TIME_SERIES_MONTHLY&symbol=" + name + "&apikey=CX3DG08ISX9HLJCV");
                // calcular api
                facadeAction.consultar();
                // guardar en cache 
                cache.getFacadeActionCache().put(name + " month", facadeAction);
            }
            
            alphaService = new AlphaServiceImpl(facadeAction.obtener());

            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(alphaService.formResponseDay().toString());
            
            res.type("application/json");
            return new Gson().toJson(
                    new StandardResponse(StatusResponse.SUCCESS, jsonElement));

        });
        
        get("/action/interday/:name", (Request req, Response res) -> {
            String name = req.params(":name");
            
            if (cache.getFacadeActionCache().containsKey(name + " interday")
            		&& !cache.getFacadeActionCache().get(name + " interday").obtener().toString().contains("Error Message")
            		&& !cache.getFacadeActionCache().get(name + " interday").obtener().toString().contains("Note")
            		) {
            	System.out.println("### SI entro cache ");
            	facadeAction = cache.getFacadeActionCache().get(name + " interday");
            }else {
            	System.out.println("### NO entro cache ");
            	facadeAction = new FacadeAction(name, "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=" + name + "&interval=1min&apikey=CX3DG08ISX9HLJCV");
                // calcular api
                facadeAction.consultar();
                // guardar en cache 
                cache.getFacadeActionCache().put(name + " interday", facadeAction);
            }
            
            alphaService = new AlphaServiceImpl(facadeAction.obtener());

            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(alphaService.formResponseDay().toString());
            
            res.type("application/json");
            return new Gson().toJson(
                    new StandardResponse(StatusResponse.SUCCESS, jsonElement));

        });
        
    }

}
