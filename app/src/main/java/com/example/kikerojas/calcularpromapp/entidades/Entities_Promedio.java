package com.example.kikerojas.calcularpromapp.entidades;

import java.util.Date;

/**
 * Created by kikerojas on 30/09/2016.
 */

public class Entities_Promedio {

    private String mTipoUnidad;
    private String mFecha;
    private Double mPromedio;
    private Double mPromedio2;
    private Double mPromedio3;
    private Double mTotal;



    public Entities_Promedio() {

    }

    public String getmTipoUnidad() {
        return mTipoUnidad;
    }

    public void setmTipoUnidad(String mTipoUnidad) {
        this.mTipoUnidad = mTipoUnidad;
    }

    public String getmFecha() {
        return mFecha;
    }

    public void setmFecha(String mFecha) {
        this.mFecha = mFecha;
    }

    public Double getmPromedio() {
        return mPromedio;
    }

    public void setmPromedio(Double mPromedio) {
        this.mPromedio = mPromedio;
    }

    public Double getmPromedio2() {
        return mPromedio2;
    }

    public void setmPromedio2(Double mPromedio2) {
        this.mPromedio2 = mPromedio2;
    }

    public Double getmTotal() {
        return mTotal;
    }

    public Double getmPromedio3() {
        return mPromedio3;
    }

    public void setmPromedio3(Double mPromedio3) {
        this.mPromedio3 = mPromedio3;
    }


    public void setmTotal(Double mTotal) {
        this.mTotal = mTotal;
    }

    public Entities_Promedio(String mTipoUnidad,String mFecha, Double mPromedio, Double mPromedio2,Double mPromedio3, Double mTotal) {
        this.mTipoUnidad = mTipoUnidad;
        this.mFecha = mFecha;
        this.mPromedio = mPromedio;
        this.mPromedio2 = mPromedio2;
        this.mPromedio3 = mPromedio3;
        this.mTotal = mTotal;
    }
}
