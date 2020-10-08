/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.io.Serializable;
import javax.servlet.http.Part;

public class Usuario  implements Serializable{

    private String nombre;
    private String apellidos;
    private String nombreUsuario;
    private String clave;
    private String correo;
    private Part imagenPerfil;
   
    public Usuario(){
      nombre="";
      apellidos="";
      nombreUsuario="";
      clave="";
      correo="";
      imagenPerfil=null;
   
    }
    
    public Usuario(String nombre, String apellidos, String nombreUsuario, String clave, String correo, Part imagenPerfil) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nombreUsuario = nombreUsuario;
        this.clave = clave;
        this.correo = correo;
        this.imagenPerfil = imagenPerfil;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Part getImagenPerfil() {
        return imagenPerfil;
    }

    public void setImagenPerfil(Part imagenPerfil) {
        this.imagenPerfil = imagenPerfil;
    }
}
    

