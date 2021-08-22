/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escuelaing.edu.co.arep.mvngit.facade;

import com.escuelaing.edu.co.arep.mvngit.entity.ActionResponseApi;
import java.io.IOException;
import java.net.ProtocolException;

/**
 *
 * @author Juan Sebastian
 */
public class FacadeAction {
    
    private final ActionResponseApi actionResponseApi;

    public FacadeAction(String name, String urlApi) {
        super();
        this.actionResponseApi = new ActionResponseApi(name, urlApi);
    }
    
    public void consultar() throws ProtocolException, IOException{
         this.actionResponseApi.consultar();
    }
    
    public StringBuffer obtener() {
    	return this.actionResponseApi.getResponseApiData();
    }

    
}
