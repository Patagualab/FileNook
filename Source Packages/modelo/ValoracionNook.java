/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package modelo;

import java.util.Date;

public class ValoracionNook {

    private int nook;
    private String usuario;
    private int puntuacion;
    private Date fecha;
    
    public ValoracionNook(){
        
    }
   
    public ValoracionNook(int nook, String usuario, int puntuacion, Date fecha) {
        this.nook = nook;
        this.usuario = usuario;
        this.puntuacion = puntuacion;
        this.fecha = fecha;
    }


    public int getNook() {
        return nook;
    }

    public void setNook(int nook) {
        this.nook = nook;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

}
    

