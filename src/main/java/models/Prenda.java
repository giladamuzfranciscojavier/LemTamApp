package models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name="prendas")
public class Prenda implements Serializable {
    
    @Id
    @Column(name="id")
    private int id;
    
    @Lob
    @Column(name="foto")
    private byte[] foto;
    
    @Column(name="tipo")
    private String tipo;
    
    @Column(name="color")
    private String color;
    
    @Column(name="talla")
    private String talla;
    
    @Column(name="disponible")
    private boolean disponible;
    
    public Prenda(){}    
    
    public Prenda(String tipo, byte[] foto, String color, String talla){
        this.tipo = tipo;
        this.foto = foto;        
        this.color = color;
        this.talla = talla;
        disponible=true;
    }

    @Override
    public String toString() {
        return "Prenda{" + "tipo=" + tipo + ", color=" + color + ", talla=" + talla + '}';
    }

    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.id;
        hash = 43 * hash + Objects.hashCode(this.tipo);
        hash = 43 * hash + Objects.hashCode(this.color);
        hash = 43 * hash + Objects.hashCode(this.talla);
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
        final Prenda other = (Prenda) obj;
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        if (!Objects.equals(this.color, other.color)) {
            return false;
        }
        return Objects.equals(this.talla, other.talla);
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }       
    
}
