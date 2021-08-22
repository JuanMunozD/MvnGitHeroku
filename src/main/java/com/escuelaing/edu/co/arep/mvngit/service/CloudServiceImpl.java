/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escuelaing.edu.co.arep.mvngit.service;

// https://cloud.iexapis.com/stable/stock/MSFT/intraday-prices?token=pk_322dc2363c054dafae6a9e5008595302
// https://cloud.iexapis.com/stable/stock/MSFT/chart/1d?token=pk_322dc2363c054dafae6a9e5008595302
// https://cloud.iexapis.com/stable/stock/MSFT/chart/1w?token=pk_322dc2363c054dafae6a9e5008595302
// https://cloud.iexapis.com/stable/stock/MSFT/chart/1m?token=pk_322dc2363c054dafae6a9e5008595302


/**
 *
 * @author Juan Sebastian
 */
public class CloudServiceImpl implements ActionService {
    
    private final StringBuffer responseApi;

    public CloudServiceImpl(StringBuffer responseApi) {
        this.responseApi = responseApi;
    }
    
    @Override
    public StringBuffer formResponseIntraDay() {
        return this.responseApi;
    }

    @Override
    public StringBuffer formResponseDay() {
        return this.responseApi;
    }

    @Override
    public StringBuffer formResponseMonth() {
        return this.responseApi;
    }

    @Override
    public StringBuffer formResponseWeek() {
        return this.responseApi;
    }

}
