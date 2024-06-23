package com.example.mipropiaerp;

import javafx.beans.property.*;

public class Producto {
    private final SimpleIntegerProperty idProd;
    private final StringProperty nombre;
    private final StringProperty descripcion;
    private final DoubleProperty precio;
    private final IntegerProperty stock;

    public Producto(int idProd, String nombre, String descripcion, double precio, int stock) {
        this.idProd = new SimpleIntegerProperty(idProd);
        this.nombre = new SimpleStringProperty(nombre);
        this.descripcion = new SimpleStringProperty(descripcion);
        this.precio = new SimpleDoubleProperty(precio);
        this.stock = new SimpleIntegerProperty(stock);
    }

    // Métodos getter
    public String getNombre() {
        return nombre.get();
    }

    public String getDescripcion() {
        return descripcion.get();
    }

    public double getPrecio() {
        return precio.get();
    }

    public int getStock() {
        return stock.get();
    }

    public int getIdProd() {
        return idProd.get();
    }

    // Métodos property (necesarios para el binding con JavaFX)
    public StringProperty nombreProperty() {
        return nombre;
    }

    public StringProperty descripcionProperty() {
        return descripcion;
    }

    public DoubleProperty precioProperty() {
        return precio;
    }

    public IntegerProperty stockProperty() {
        return stock;
    }

    public SimpleIntegerProperty idProdProperty() {
        return idProd;
    }

    // Métodos setter
    public void setIdProd(int idProd) {
        this.idProd.set(idProd);
    }
}
