/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.util.Date;

public class Mensaje {

    private int idMensaje;
    private String asunto;
    private String texto;
    private Date fecha;
    private int leido;
    private String tipo;
    private String autor;
    private String destinatario;
   
    public Mensaje(){
        
    }
    
    public Mensaje(int idMensaje, String asunto, String texto, Date fecha, int leido, String tipo, String autor, String destinatario) {
        this.idMensaje = idMensaje;
        this.asunto = asunto;
        this.texto = texto;
        this.fecha = fecha;
        this.leido = leido;
        this.tipo = tipo;
        this.autor = autor;
        this.destinatario = destinatario;
    }


    public int getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(int idMensaje) {
        this.idMensaje = idMensaje;
    }
    
    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getLeido() {
        return leido;
    }

    public void setLeido(int leido) {
        this.leido = leido;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }
}
    

