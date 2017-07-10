package models;

/**
 * Created by apesteguia on 10/07/2017.
 */

public class Activo {

    private String id;
    private String descripcion;
    private Integer centroCostos;
    private Integer tipo;
    private Double peso;
    private Integer marca;
    private Integer estado;
    private String ubicacion;
    private String responsable;
    private String fechaCompra;
    private String proveedor;

    //Campos de ayuda
    private String centroCostosNombre;
    private String tipoNombre;
    private String marcaNombre;
    private String estadoNombre;
    private String responsableNombre;
    private String proveedorNombre;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCentroCostos() {
        return centroCostos;
    }

    public void setCentroCostos(Integer centroCostos) {
        this.centroCostos = centroCostos;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Integer getMarca() {
        return marca;
    }

    public void setMarca(Integer marca) {
        this.marca = marca;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getCentroCostosNombre() {
        return centroCostosNombre;
    }

    public void setCentroCostosNombre(String centroCostosNombre) {
        this.centroCostosNombre = centroCostosNombre;
    }

    public String getTipoNombre() {
        return tipoNombre;
    }

    public void setTipoNombre(String tipoNombre) {
        this.tipoNombre = tipoNombre;
    }

    public String getMarcaNombre() {
        return marcaNombre;
    }

    public void setMarcaNombre(String marcaNombre) {
        this.marcaNombre = marcaNombre;
    }

    public String getEstadoNombre() {
        return estadoNombre;
    }

    public void setEstadoNombre(String estadoNombre) {
        this.estadoNombre = estadoNombre;
    }

    public String getResponsableNombre() {
        return responsableNombre;
    }

    public void setResponsableNombre(String responsableNombre) {
        this.responsableNombre = responsableNombre;
    }

    public String getProveedorNombre() {
        return proveedorNombre;
    }

    public void setProveedorNombre(String proveedorNombre) {
        this.proveedorNombre = proveedorNombre;
    }
}
