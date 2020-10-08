
package modelo;

import java.sql.Blob;
import java.util.Date;

public class Documento {

    private int nook;
    private String nombre;
    private String resumen;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Blob documento;
    
    public Documento(){
        
    }
   
    public Documento(int nook, String nombre, String resumen, Date fechaCreacion, Date fechaModificacion, Blob documento) {
        this.nook = nook;
        this.nombre = nombre;
        this.resumen = resumen;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.documento = documento;
    }

    public int getNook() {
        return nook;
    }

    public void setNook(int nook) {
        this.nook = nook;
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

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }
    
    public Blob getDocumento() {
        return documento;
    }

    public void setDocumento(Blob documento) {
        this.documento = documento;
    }
}