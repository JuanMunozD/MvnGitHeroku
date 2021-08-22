package com.escuelaing.edu.co.arep.mvngit;

import com.escuelaing.edu.co.arep.mvngit.controller.ActionController;
import com.escuelaing.edu.co.arep.mvngit.service.ActionService;
import spark.Request;
import spark.Response;
import static spark.Spark.*;

/**
 * Minimal web app example for Heroku using SparkWeb
 *
 * @author daniel
 */
public class MvngitApplication {
    
    private ActionService gateway;

    /**
     * This main method uses SparkWeb static methods and lambda functions to
     * create a simple Hello World web app. It maps the lambda function to the
     * /hello relative URL.
     */
    public static void main(String[] args) {
        // root is 'src/main/resources', so put files in 'src/main/resources/public'
        staticFiles.location("public"); // Static files
        port(getPort());
        
        ActionController controller= new ActionController();
        
    }

    /**
     * This method reads the default port as specified by the PORT variable in
     * the environment.
     *
     * Heroku provides the port automatically so you need this to run the
     * project on Heroku.
     */
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 8080; //returns default port if heroku-port isn't set (i.e. on localhost)
    }

}