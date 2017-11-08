package br.com.quilometragem.app;

import com.orm.SugarRecord;

/**
 * Created by 16254867 on 01/11/2017.
 */

public class Quilometragem extends SugarRecord {

    private double km;
    private int idMes;
    private String mes;

    //Construtores

    public Quilometragem(){

    }

    public Quilometragem(double km, int idMes,String mes){
        this.km = km;
        this.idMes = idMes;
        this.mes = mes;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }


    public double getKm() {
        return km;
    }

    public void setKm(double km) {
        this.km = km;
    }

    public int getIdMes() {
        return idMes;
    }

    public void setIdMes(int idMes) {
        this.idMes = idMes;
    }
}
