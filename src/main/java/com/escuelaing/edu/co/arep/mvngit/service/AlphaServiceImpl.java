/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escuelaing.edu.co.arep.mvngit.service;

// https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=MSFT&interval=1min&apikey=CX3DG08ISX9HLJCV Intra-day
// https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol="+name+"&apikey=CX3DG08ISX9HLJCV Diaria
// https://www.alphavantage.co/query?function=TIME_SERIES_WEEKLY&symbol=MSFT&apikey=CX3DG08ISX9HLJCV Semanal
// https://www.alphavantage.co/query?function=TIME_SERIES_MONTHLY&symbol=MSFT&apikey=CX3DG08ISX9HLJCV Mensual
/**
 *
 * @author Juan Sebastian
 */
public class AlphaServiceImpl implements ActionService {

    private final StringBuffer responseApi;

    public AlphaServiceImpl(StringBuffer responseApi) {
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
