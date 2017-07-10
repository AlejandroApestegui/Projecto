package models;

/**
 * Created by apesteguia on 10/07/2017.
 */

public class Miscelaneo {

    public Miscelaneo() {

    }

    public Miscelaneo(Integer id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    private Integer id;
    private String descripcion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
