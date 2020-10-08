/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package modelo;

public class ClasificacionCategorias {

    private int idNook;
    private String categoria;
    
   
    public ClasificacionCategorias(){
        
    }
       
    public ClasificacionCategorias(int idNook, String categoria) {
        this.idNook = idNook;
        this.categoria = categoria;
    }


    public int getIdNook() {
        return idNook;
    }

    public void setIdNook(int idNook) {
        this.idNook = idNook;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

}