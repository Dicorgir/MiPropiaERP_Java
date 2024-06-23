// Este archivo pertenece al paquete com.example.mipropiaerp
package com.example.mipropiaerp;

// Importa las clases necesarias de JavaFX para propiedades y Date
import javafx.beans.property.*;
import java.sql.Date;

// Definición de la clase Venta
public class Venta {

    // Propiedades para los distintos atributos de la venta utilizando JavaFX
    private final IntegerProperty idCliente;
    private final IntegerProperty idProveedores;
    private final IntegerProperty idProductos;
    private final IntegerProperty idVentas;
    private final IntegerProperty cantidadVentas;
    private final ObjectProperty<Date> fechaVentas;

    // Constructor que inicializa las propiedades con los valores proporcionados
    public Venta(int idCliente, int idProveedores, int idProductos, int idVentas, int cantidadVentas, Date fechaVentas) {
        this.idCliente = new SimpleIntegerProperty(idCliente);
        this.idProveedores = new SimpleIntegerProperty(idProveedores);
        this.idProductos = new SimpleIntegerProperty(idProductos);
        this.idVentas = new SimpleIntegerProperty(idVentas);
        this.cantidadVentas = new SimpleIntegerProperty(cantidadVentas);
        this.fechaVentas = new SimpleObjectProperty<>(fechaVentas);
    }

    // Métodos getter y setter para idCliente
    public int getIdCliente() {
        return idCliente.get();
    }

    public void setIdCliente(int idCliente) {
        this.idCliente.set(idCliente);
    }

    public IntegerProperty idClienteProperty() {
        return idCliente;
    }

    // Métodos getter y setter para idProveedores
    public int getIdProveedores() {
        return idProveedores.get();
    }

    public void setIdProveedores(int idProveedores) {
        this.idProveedores.set(idProveedores);
    }

    public IntegerProperty idProveedoresProperty() {
        return idProveedores;
    }

    // Métodos getter y setter para idProductos
    public int getIdProductos() {
        return idProductos.get();
    }

    public void setIdProductos(int idProductos) {
        this.idProductos.set(idProductos);
    }

    public IntegerProperty idProductosProperty() {
        return idProductos;
    }

    // Métodos getter y setter para idVentas
    public int getIdVentas() {
        return idVentas.get();
    }

    public void setIdVentas(int idVentas) {
        this.idVentas.set(idVentas);
    }

    public IntegerProperty idVentasProperty() {
        return idVentas;
    }

    // Métodos getter y setter para cantidadVentas
    public int getCantidadVentas() {
        return cantidadVentas.get();
    }

    public void setCantidadVentas(int cantidadVentas) {
        this.cantidadVentas.set(cantidadVentas);
    }

    public IntegerProperty cantidadVentasProperty() {
        return cantidadVentas;
    }

    // Métodos getter y setter para fechaVentas
    public Date getFechaVentas() {
        return fechaVentas.get();
    }

    public void setFechaVentas(Date fechaVentas) {
        this.fechaVentas.set(fechaVentas);
    }

    public ObjectProperty<Date> fechaVentasProperty() {
        return fechaVentas;
    }
}
