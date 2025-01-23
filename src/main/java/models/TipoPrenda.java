package models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Arrays;

public class TipoPrenda implements Serializable {
    
    private int id;
    
    private byte[] foto;
    
    private String tipo;
    
    private String color;
    
    private String talla;
    
    public TipoPrenda(){}    
    
    public TipoPrenda(String tipo, byte[] foto, String color, String talla){
        this.tipo = tipo;
        this.foto = foto;        
        this.color = color;
        this.talla = talla;
        
        this.id = this.hashCode();
        
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (this.tipo != null ? this.tipo.hashCode() : 0);
        hash = 17 * hash + (this.color != null ? this.color.hashCode() : 0);
        hash = 17 * hash + (this.talla != null ? this.talla.hashCode() : 0);
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
        final TipoPrenda other = (TipoPrenda) obj;
        if ((this.tipo == null) ? (other.tipo != null) : !this.tipo.equals(other.tipo)) {
            return false;
        }
        if ((this.color == null) ? (other.color != null) : !this.color.equals(other.color)) {
            return false;
        }
        return (this.talla == null) ? (other.talla == null) : this.talla.equals(other.talla);
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
