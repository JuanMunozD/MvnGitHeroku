package com.escuelaing.edu.co.arep.mvngit;

import com.escuelaing.edu.co.arep.mvngit.entity.ActionResponseApi;
import java.io.IOException;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest
        extends TestCase {
    
    ActionResponseApi actionResponseApi;

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testActionResponseApiDay() {
        
        String name = "MSFT";
        String url = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" + name + "&apikey=CX3DG08ISX9HLJCV";
        
        actionResponseApi = new ActionResponseApi(name, url);
        
        try{
            actionResponseApi.consultar();
            assertTrue(true);
        }catch(IOException e){
            assertTrue(false);
        }

        assertTrue(actionResponseApi.getResponseApiData().toString().contains("Meta Data"));
    }

    public void testActionResponseApiWeek() {
        
        String name = "MSFT";
        String url = "https://www.alphavantage.co/query?function=TIME_SERIES_WEEKLY&symbol=" + name + "&apikey=CX3DG08ISX9HLJCV";
        
        actionResponseApi = new ActionResponseApi(name, url);
        
        try{
            actionResponseApi.consultar();
            assertTrue(true);
        }catch(IOException e){
            assertTrue(false);
        }

        assertTrue(actionResponseApi.getResponseApiData().toString().contains("Meta Data"));
    }   
    
    public void testActionResponseApiMonth() {
        
        String name = "MSFT";
        String url = "https://www.alphavantage.co/query?function=TIME_SERIES_MONTHLY&symbol=" + name + "&apikey=CX3DG08ISX9HLJCV";
        
        actionResponseApi = new ActionResponseApi(name, url);
        
        try{
            actionResponseApi.consultar();
            assertTrue(true);
        }catch(IOException e){
            assertTrue(false);
        }

        assertTrue(actionResponseApi.getResponseApiData().toString().contains("Meta Data"));
    }    
    
    public void testActionResponseApiIntraDay() {
        
        String name = "MSFT";
        String url = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=" + name + "&interval=1min&apikey=CX3DG08ISX9HLJCV";
        
        
        actionResponseApi = new ActionResponseApi(name, url);
        
        try{
            actionResponseApi.consultar();
            assertTrue(true);
        }catch(IOException e){
            assert(false);
        }

        assertTrue(actionResponseApi.getResponseApiData().toString().contains("Meta Data"));
    }    


}
