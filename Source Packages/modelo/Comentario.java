/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package modelo;

import java.util.Date;

public class Comentario {
    
    private int idNook;
    private int idComentario;
    private Date fecha;
    private String autor;
    private String texto; 
    private double valoracionMedia;
   
    public Comentario(){
        
    }
    
    public Comentario(int idNook, int idComentario, Date fecha, String autor, String texto, double valoracionMedia) {
        this.idNook = idNook;
        this.idComentario = idComentario;
        this.fecha = fecha;
        this.autor = autor;
        this.texto = texto;
        this.valoracionMedia = valoracionMedia;
    }

    
    public int getIdNook() {
        return idNook;
    }

    public void setIdNook(int idNook) {
        this.idNook = idNook;
    }
    
    public int getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
    
    public double getValoracionMedia() {
        return valoracionMedia;
    }

    public void setValoracionMedia(double valoracionMedia) {
        this.valoracionMedia = valoracionMedia;
    }

}