// Este archivo pertenece al paquete com.example.mipropiaerp
package com.example.mipropiaerp;

// Importa las clases necesarias de JavaFX para propiedades
import javafx.beans.property.*;

// Definición de la clase Cliente
public class Cliente {

    // Propiedades para los distintos atributos del cliente utilizando JavaFX
    private final IntegerProperty idCliente;
    private final StringProperty nombre;
    private final StringProperty direccion;
    private final StringProperty telefono;
    private final StringProperty dni;

    // Constructor que inicializa las propiedades con los valores proporcionados
    public Cliente(int idCliente, String nombre, String direccion, String telefono, String dni) {
        this.idCliente = new SimpleIntegerProperty(idCliente);
        this.nombre = new SimpleStringProperty(nombre);
        this.direccion = new SimpleStringProperty(direccion);
        this.telefono = new SimpleStringProperty(telefono);
        this.dni = new SimpleStringProperty(dni);
    }

    // Métodos getter y setter para idCliente
    public int getIdCliente() {
        return idCliente.get();
    }

    public void setIdCliente(int idCliente) {
        this.idCliente.set(idCliente);
    }

    // Métodos getter y setter para nombre
    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    // Métodos getter y setter para direccion
    public String getDireccion() {
        return direccion.get();
    }

    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
    }

    // Métodos getter y setter para telefono
    public String getTelefono() {
        return telefono.get();
    }

    public void setTelefono(String telefono) {
        this.telefono.set(telefono);
    }

    // Métodos getter y setter para dni
    public String getDni() {
        return dni.get();
    }

    public void setDni(String dni) {
        this.dni.set(dni);
    }

    // Métodos de propiedad para vinculación con JavaFX
    public IntegerProperty idClienteProperty() {
        return idCliente;
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public StringProperty direccionProperty() {
        return direccion;
    }

    public StringProperty telefonoProperty() {
        return telefono;
    }

    public StringProperty dniProperty() {
        return dni;
    }
}
