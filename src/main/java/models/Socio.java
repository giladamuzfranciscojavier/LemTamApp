package models;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="socios")
public class Socio implements Serializable {
    
    @Id
    @Column(name="id")
    private int id;
    
    @Column(name="nombre")
    private String nombre;
    
    public Socio(){}

    public Socio(String nombre){
        this.nombre = nombre;        
        this.id = this.hashCode();
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (this.nombre != null ? this.nombre.hashCode() : 0);
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
        final Socio other = (Socio) obj;
        return (this.nombre == null) ? (other.nombre == null) : this.nombre.equals(other.nombre);
    }
    
    
    
}
