// Este archivo pertenece al paquete com.example.mipropiaerp
package com.example.mipropiaerp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

// Clase principal que extiende de javafx.application.Application
public class HelloApplication extends Application {

    // Método start que se llama al iniciar la aplicación
    @Override
    public void start(Stage stage) throws IOException {
        // Carga el archivo FXML de la interfaz gráfica
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ControlPrincipal.fxml"));

        // Crea una escena con el contenido cargado desde el FXML
        Scene scene = new Scene(fxmlLoader.load());

        // Configura el título de la ventana
        stage.setTitle("MI PROPIA ERP");

        // Establece la escena en la ventana
        stage.setScene(scene);

        // Muestra la ventana
        stage.show();
    }

    // Método principal de la aplicación que inicia la ejecución
    public static void main(String[] args) {
        launch();
    }
}
