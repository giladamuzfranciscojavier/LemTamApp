package models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name="prestamos")
public class Prestamo implements Serializable{
            
    @Id
    @Column(name="id")
    private int id;
    
    @Column(name="socio")
    private int socio;
    
    @Column(name="prenda")
    private int prenda;
    
    @Column(name="fecha_inicio")
    private Date fechaInicio;
    
    @Column(name="fecha_fin")
    private Date fechaFin;
    
    
    public Prestamo(){}

    public Prestamo(int socio, int prenda, Date fechafin){
        this.socio=socio;
        this.prenda=prenda;
        this.fechaInicio= new Date(System.currentTimeMillis());
        this.fechaFin=fechafin;         
        id = this.hashCode();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.socio;
        hash = 29 * hash + this.prenda;
        hash = 29 * hash + (this.fechaInicio != null ? this.fechaInicio.hashCode() : 0);
        hash = 29 * hash + (this.fechaFin != null ? this.fechaFin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Prestamo other = (Prestamo) obj;
        if (this.socio != other.socio) {
            return false;
        }
        if (this.prenda != other.prenda) {
            return false;
        }
        if (this.fechaInicio != other.fechaInicio && (this.fechaInicio == null || !this.fechaInicio.equals(other.fechaInicio))) {
            return false;
        }
        return this.fechaFin == other.fechaFin || (this.fechaFin != null && this.fechaFin.equals(other.fechaFin));
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }   

    public int getSocio() {
        return socio;
    }

    public void setSocio(int socio) {
        this.socio = socio;
    }

    public int getPrenda() {
        return prenda;
    }

    public void setPrenda(int prenda) {
        this.prenda = prenda;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    
    
}
