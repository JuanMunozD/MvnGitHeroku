/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escuelaing.edu.co.arep.mvngit.controller;

import static spark.Spark.*;

/**
 *
 * @author Juan Sebastian
 */

public class ActionController {
    
    public ActionController() {
        
        get("/hello/:name", (req,res)->{
            return "Hello, "+ req.params(":name");
        });
    }
    
}
