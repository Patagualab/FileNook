/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.sql.Blob;
import java.util.Date;

public class Nook {

    private int idNook;
    private String nombre;
    private String resumen;
    private String autor;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private int descargas;
    private double valoracionMedia;
    
    public Nook(){
        
    }
    
    public Nook(int idNook, String nombre, String resumen, String autor, Date fechaCreacion, Date fechaModificacion, int descargas, double valoracionMedia) {
        this.idNook = idNook;
        this.nombre = nombre;
        this.resumen = resumen;
        this.autor = autor;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.descargas = descargas;
        this.valoracionMedia = valoracionMedia;
    }


    public int getIdNook() {
        return idNook;
    }

    public void setIdNook(int idNook) {
        this.idNook = idNook;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaModificaion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public int getDescargas() {
        return descargas;
    }

    public void setDescargas(int descargas) {
        this.descargas = descargas;
    }
    
    public double getValoracionMedia() {
        return valoracionMedia;
    }

    public void setValoracionMedia(double valoracionMedia) {
        this.valoracionMedia = valoracionMedia;
    }
    
}
    

