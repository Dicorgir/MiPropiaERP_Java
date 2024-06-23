// Este archivo pertenece al paquete com.example.mipropiaerp
package com.example.mipropiaerp;

// Importa las clases necesarias de JavaFX para propiedades
import javafx.beans.property.*;

// Definición de la clase Proveedor
public class Proveedor {

    // Propiedades para los distintos atributos del proveedor utilizando JavaFX
    private final IntegerProperty idProveedor = new SimpleIntegerProperty();
    private final StringProperty nombre = new SimpleStringProperty();
    private final StringProperty direccion = new SimpleStringProperty();
    private final StringProperty telefono = new SimpleStringProperty();

    // Constructor que inicializa las propiedades con los valores proporcionados
    public Proveedor(int idProveedor, String nombre, String direccion, String telefono) {
        this.idProveedor.set(idProveedor);
        this.nombre.set(nombre);
        this.direccion.set(direccion);
        this.telefono.set(telefono);
    }

    // Métodos getter y setter para idProveedor
    public int getIdProveedor() {
        return idProveedor.get();
    }

    public IntegerProperty idProveedorProperty() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor.set(idProveedor);
    }

    // Métodos getter y setter para nombre
    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    // Métodos getter y setter para direccion
    public String getDireccion() {
        return direccion.get();
    }

    public StringProperty direccionProperty() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
    }

    // Métodos getter y setter para telefono
    public String getTelefono() {
        return telefono.get();
    }

    public StringProperty telefonoProperty() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono.set(telefono);
    }

    // Puedes agregar más métodos según sea necesario

    // Método toString que devuelve el valor de la propiedad de cadena "nombre"
    @Override
    public String toString() {
        return nombre.get();
    }
}
