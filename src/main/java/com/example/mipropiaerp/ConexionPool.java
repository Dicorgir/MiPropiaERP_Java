// Este archivo pertenece al paquete com.example.mipropiaerp
package com.example.mipropiaerp;

// Importa las clases necesarias de la biblioteca Hikari para la gestión de conexiones a la base de datos
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

// Importa la clase Connection y SQLException de java.sql para el manejo de conexiones y excepciones SQL
import java.sql.Connection;
import java.sql.SQLException;

// Definición de la clase ConexionPool
public class ConexionPool {

    // URL de conexión a la base de datos MySQL, con el puerto 3307 y la base de datos "erp"
    private static final String JDBC_URL = "jdbc:mysql://localhost:3307/erp";

    // Usuario y contraseña para la conexión a la base de datos
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Tamaño máximo del pool de conexiones
    private static final int MAX_POOL_SIZE = 10;

    // Configuración de HikariCP
    private static final HikariConfig config;

    // Fuente de datos HikariDataSource que administra el pool de conexiones
    private static final HikariDataSource dataSource;

    // Bloque estático que se ejecuta al cargar la clase
    static {
        // Inicialización de la configuración de HikariCP
        config = new HikariConfig();
        config.setJdbcUrl(JDBC_URL);
        config.setUsername(USER);
        config.setPassword(PASSWORD);
        config.setMaximumPoolSize(MAX_POOL_SIZE);

        // Creación de la fuente de datos HikariDataSource con la configuración proporcionada
        dataSource = new HikariDataSource(config);
    }

    // Método que devuelve una conexión del pool
    public static Connection obtenerConexion() throws SQLException {
        return dataSource.getConnection();
    }

    // Método para cerrar la fuente de datos (pool de conexiones) cuando ya no se necesita
    public static void close() {
        if (dataSource != null) {
            dataSource.close();
        }
    }
}
