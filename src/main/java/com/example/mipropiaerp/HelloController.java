package com.example.mipropiaerp;

import javafx.application.HostServices;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import net.sf.jasperreports.engine.*;


import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.util.List;


public class HelloController {
    // ELEMENTOS DE LA ERP 
    public TabPane tabPanePrincipal;
    public Tab InventarioTab;
    public TableView<Producto> tablaInventarios;
    public TableColumn<Producto, Integer> colIDProdInvent;
    public TableColumn<Producto, String> colNombreInvent;
    public TableColumn<Producto, String> colDescripcionInvent;
    public TableColumn<Producto, Double> colPrecioInvent;
    public TableColumn<Producto, Integer> colCantidadInvent;

    // Campos de la pestaña "Inventarios"
    public TextField TextFieldNombreProducto;
    public TextArea AreaDescripcionProducto;
    public TextField TextFieldPreciProducto;
    public TextField TextFieldCantidadProducto;
    public Button BotonLimpiarProducto;
    public Button BotonAgregarProducto;
    public Button BotonActualizarProducto;
    public Button BotonEliminarProducto;
    // Botones de Reporte
    public Button BotonFacturas;


    private ObservableList<Producto> productosData = FXCollections.observableArrayList();


    public TextField BuscadorDNI;
    // ELEMENTOS DE PESTAÑA PROVEEDOR
    public TextField TextFieldNombreProveedor;
    public TextField TextFieldDirectProveedor;
    public TextField TextFieldTelefonoProveedor;
    public Button BotonRegistrarProve;
    public Button BotonEliminarProve;
    public Button BotonEditarProve;
    public Button BotonLimpiarProve;
    public TextField TextFieldNombrePedido;
    public TextField TextFieldCantidadPedido;
    public ComboBox<Proveedor> ComboSelecProve;
    public Button BotonAgregarPedido;

    public Tab ProveedoresTab;
    public TableView<Proveedor> tablaProveedores;
    public TableColumn<Proveedor, Integer> colIDProvProve;
    public TableColumn<Proveedor, String> colNombreProve;
    public TableColumn<Proveedor, String> colDireccionProve;
    public TableColumn<Proveedor, String> colTelefonoProve;
    private ObservableList<Proveedor> proveedoresData = FXCollections.observableArrayList();
    // Campos de la pestaña "Clientes"
    public TextField TextFieldNombreClient;
    public TextField TextFieldDirectClient;
    public TextField TextFieldTelClient;
    public TextField TextFieldDNIClient;
    public TextField BuscadorNombre;
    public TextField BuscadorDireccion;
    public Tab ClientesTab;
    public TableView<Cliente> tablaClientes;
    public TableColumn<Cliente, String> colNombreClient;
    public TableColumn<Cliente, String> colDireccionClient;
    public TableColumn<Cliente, String> colDNIClient;
    public TableColumn<Cliente, String> colIDClient;
    public TableColumn<Cliente, String> colTelefonoClient;
    private ObservableList<Cliente> clientesData = FXCollections.observableArrayList();
    public Button BotonRegistrarCliente;
    public Button BotonEliminarCliente;
    public Button BotonLimpiarCliente;
    public Button BotonEditarCliente;

    // ELEMENTOS DE VENTAS
    public ComboBox<String> SeleccionarProductoVentas;
    public ComboBox<String> SeleccionarClienteVentas;
    public ComboBox<String> SeleccionarProveedorVentas;
    public DatePicker DatePickerFechaVentas;
    public TextField TextFieldCantidadVentas;
    public TableColumn<Venta, Integer> colIdClienteVentas;
    public TableColumn<Venta, Integer> colIdProveedoresVentas;
    public TableColumn<Venta, Integer> colIdProductosVentas;
    public TableColumn<Venta, Integer> coldIdVentas;
    public Tab ReportesTab;
    public Button BotonGenerarInformeFinan;
    public Button BotonHistorialVentas;
    public Button BotonInventarioActual;
    public Tab VentasTab;
    public TableView<Venta> tablaVentas;
    public TableColumn<Venta, Integer> colCantidadVentas;
    public TableColumn<Venta, String> colFechaVentas;
    private ObservableList<Venta> listaVentas = FXCollections.observableArrayList();

    public Button BotonRegistrarVenta;

    @FXML
    private void initialize() {
        acti();
        // Configuración del evento al hacer clic en la tabla
        tablaInventarios.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() == 1) { // Verifica que sea un clic simple
                mostrarDetallesProducto(tablaInventarios.getSelectionModel().getSelectedItem());
            }
        });

        // Configuración del evento al hacer clic en el botón "BotonEliminarProducto"
        BotonEliminarProducto.setOnAction(this::eliminarProducto);

        // Configuración del evento al hacer clic en el botón "BotonAgregarProducto"
        BotonAgregarProducto.setOnAction(this::agregarProducto);


        // Configuración del evento al hacer clic en la tabla de clientes
        tablaClientes.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() == 1) { // Verifica que sea un clic simple
                mostrarDetallesCliente(tablaClientes.getSelectionModel().getSelectedItem());
            }
        });

        // Llamada al método para cargar clientes al iniciar la aplicación
        cargarClientesDesdeBD();

        // Configuración del evento al cambiar texto en los campos de búsqueda
        BuscadorNombre.textProperty().addListener((observable, oldValue, newValue) -> buscarClientePorNombre(newValue));
        BuscadorDireccion.textProperty().addListener((observable, oldValue, newValue) -> buscarClientePorDireccion(newValue));
        BuscadorDNI.textProperty().addListener((observable, oldValue, newValue) -> buscarClientePorDNI(newValue));

        // Llamada al método para cargar clientes al iniciar la aplicación
        cargarClientesDesdeBD();
        
        cargarProveedoresDesdeBD();

        // Configuración del evento al hacer clic en la tabla de proveedores
        tablaProveedores.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() == 1) {
                mostrarDetallesProveedor(tablaProveedores.getSelectionModel().getSelectedItem());
            }
        });
        BotonLimpiarProve.setOnAction(event -> limpiarCamposProveedor());
        cargarProveedoresEnComboBox();

        // Aquí puedes llamar a los métodos para cargar datos en los ComboBox
        // Llamada a los métodos para cargar datos en ComboBox
        cargarProductosEnComboBox();
        cargarClientesEnComboBox();
        cargarProveedoresEnComboBox2();

        // Configura las celdas de las columnas con los atributos de la clase Venta
        colIdClienteVentas.setCellValueFactory(cellData -> cellData.getValue().idClienteProperty().asObject());
        colIdProveedoresVentas.setCellValueFactory(cellData -> cellData.getValue().idProveedoresProperty().asObject());
        colIdProductosVentas.setCellValueFactory(cellData -> cellData.getValue().idProductosProperty().asObject());
        coldIdVentas.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getIdVentas()).asObject());
        colCantidadVentas.setCellValueFactory(cellData -> cellData.getValue().cantidadVentasProperty().asObject());
        colFechaVentas.setCellValueFactory(cellData -> cellData.getValue().fechaVentasProperty().asString());

        // Asigna la lista de ventas a la tabla
        tablaVentas.setItems(listaVentas);
    }
    private HostServices hostServices;

    // Método para establecer HostServices, se llama desde la clase principal de la aplicación
    public void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices;
    }
    public void onBotonFacturasClick() throws JRException, SQLException {
        Connection con = ConexionPool.obtenerConexion();
        JasperReport archivo = JasperCompileManager.compileReport("src/main/resources/Jasper/FacturasVentas.jrxml");
        JasperPrint print = JasperFillManager.fillReport(archivo, null, con);
        JasperExportManager.exportReportToPdfFile(print, "src/main/resources/PDF/FacturasPDF.pdf");
        System.out.println("PDF GENERADO");
        ConexionPool.close();
    }
    public void onBotonInventarioClick() throws JRException, SQLException {
        Connection con = ConexionPool.obtenerConexion();
        JasperReport archivo = JasperCompileManager.compileReport("src/main/resources/Jasper/InventarioActual.jrxml");
        JasperPrint print = JasperFillManager.fillReport(archivo, null, con);
        JasperExportManager.exportReportToPdfFile(print, "src/main/resources/PDF/InventarioPDF.pdf");
        System.out.println("PDF GENERADO");
        ConexionPool.close();
    }
    private void cargarProductosEnComboBox() {
        SeleccionarProductoVentas.getItems().clear(); // Limpiar el ComboBox antes de cargar los productos

        // Obtener nombres de productos desde la base de datos o la lista productosData
        List<String> nombresProductos = obtenerNombresProductosDesdeBD();

        // Agregar nombres de productos al ComboBox
        SeleccionarProductoVentas.getItems().addAll(nombresProductos);
    }
    private List<String> obtenerNombresProductosDesdeBD() {
        List<String> nombresProductos = new ArrayList<>();

        // Obtener nombres de productos desde la base de datos
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/erp", "root", "");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT nombre FROM productos")) {

            while (rs.next()) {
                String nombreProducto = rs.getString("nombre");
                nombresProductos.add(nombreProducto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nombresProductos;
    }
    private void cargarClientesEnComboBox() {
        SeleccionarClienteVentas.getItems().clear(); // Limpiar el ComboBox antes de cargar los clientes

        // Obtener nombres de clientes desde la base de datos o la lista clientesData
        List<String> nombresClientes = obtenerNombresClientesDesdeBD();

        // Agregar nombres de clientes al ComboBox
        SeleccionarClienteVentas.getItems().addAll(nombresClientes);
    }

    private List<String> obtenerNombresClientesDesdeBD() {
        List<String> nombresClientes = new ArrayList<>();

        // Obtener nombres de clientes desde la base de datos
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/erp", "root", "");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT nombre FROM Cliente")) {

            while (rs.next()) {
                String nombreCliente = rs.getString("nombre");
                nombresClientes.add(nombreCliente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nombresClientes;
    }
    private void cargarProveedoresEnComboBox2() {
        ComboSelecProve.getItems().clear(); // Limpiar el ComboBox antes de cargar los proveedores

        // Obtener proveedores desde la base de datos o la lista proveedoresData
        // Agregar proveedores al ComboBox
        ComboSelecProve.getItems().addAll(proveedoresData);
    }
    private void cargarProveedoresEnComboBox() {
        SeleccionarProveedorVentas.getItems().clear(); // Limpiar el ComboBox antes de cargar los proveedores

        // Obtener nombres de proveedores desde la base de datos o la lista proveedoresData
        List<String> nombresProveedores = obtenerNombresProveedoresDesdeBD();

        // Agregar nombres de proveedores al ComboBox
        SeleccionarProveedorVentas.getItems().addAll(nombresProveedores);
    }

    private List<String> obtenerNombresProveedoresDesdeBD() {
        List<String> nombresProveedores = new ArrayList<>();

        // Obtener nombres de proveedores desde la base de datos
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/erp", "root", "");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT nombre FROM Proveedor")) {

            while (rs.next()) {
                String nombreProveedor = rs.getString("nombre");
                nombresProveedores.add(nombreProveedor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nombresProveedores;
    }


    private void limpiarCamposProveedor() {
        TextFieldNombreProveedor.clear();
        TextFieldDirectProveedor.clear();
        TextFieldTelefonoProveedor.clear();
    }

    @FXML
    public void eliminarProveedor(ActionEvent event) {
        // Obtener el proveedor seleccionado
        Proveedor proveedorSeleccionado = tablaProveedores.getSelectionModel().getSelectedItem();

        // Verificar si hay un proveedor seleccionado
        if (proveedorSeleccionado != null) {
            // Eliminar el proveedor de la base de datos
            eliminarProveedorEnBD(proveedorSeleccionado.getIdProveedor());

            // Eliminar el proveedor de la lista
            proveedoresData.remove(proveedorSeleccionado);

            // Limpiar los campos después de eliminar
            limpiarCamposProveedores();
        }
    }

    private void eliminarProveedorEnBD(int idProveedor) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/erp", "root", "")) {
            String sql = "DELETE FROM Proveedor WHERE proveedorId=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, idProveedor);

                // Ejecutar la eliminación
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void agregarPedido(ActionEvent event) {
        // Obtener los valores de los campos
        String nombrePedido = TextFieldNombrePedido.getText();
        int cantidadPedido = Integer.parseInt(TextFieldCantidadPedido.getText());
        String nombreProveedorSeleccionado = String.valueOf(ComboSelecProve.getValue());

        // Asegúrate de que se hayan ingresado valores y se haya seleccionado un proveedor
        if (nombrePedido.isEmpty() || cantidadPedido <= 0 || nombreProveedorSeleccionado == null) {
            // Manejar el caso cuando los campos no estén completos
            // Puedes mostrar un mensaje de error o realizar otras acciones según tus necesidades
            return;
        }

        // Buscar el proveedor correspondiente al nombre seleccionado
        Optional<Proveedor> proveedorOpt = proveedoresData.stream()
                .filter(proveedor -> proveedor.getNombre().equals(nombreProveedorSeleccionado))
                .findFirst();

        // Verificar si se encontró el proveedor
        if (proveedorOpt.isPresent()) {
            Proveedor proveedorSeleccionado = proveedorOpt.get();
            // Agregar el nuevo pedido a la base de datos
            agregarPedidoEnBD(nombrePedido, cantidadPedido, proveedorSeleccionado.getNombre());

            // Limpiar los campos después de agregar
            limpiarCamposPedido();
        } else {
            // Manejar el caso cuando el proveedor no se encuentra
            // Puedes mostrar un mensaje de error o realizar otras acciones según tus necesidades
        }
    }

    private void agregarPedidoEnBD(String nombrePedido, int cantidadPedido, String nombreProveedor) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/erp", "root", "")) {
            String sql = "INSERT INTO Pedido (nombreProducto, cantidad, nombreProveedor) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, nombrePedido);
                statement.setInt(2, cantidadPedido);
                statement.setString(3, nombreProveedor);

                // Ejecutar la inserción
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @FXML
    public void limpiarCamposPedido() {
        TextFieldNombrePedido.clear();
        TextFieldCantidadPedido.clear();
        ComboSelecProve.getSelectionModel().clearSelection();
    }

    @FXML
    public void editarProveedor(ActionEvent event) {
        // Obtener el proveedor seleccionado
        Proveedor proveedorSeleccionado = tablaProveedores.getSelectionModel().getSelectedItem();

        // Verificar si hay un proveedor seleccionado
        if (proveedorSeleccionado != null) {
            // Obtener los nuevos valores de los campos
            String nuevoNombre = TextFieldNombreProveedor.getText();
            String nuevaDireccion = TextFieldDirectProveedor.getText();
            String nuevoTelefono = TextFieldTelefonoProveedor.getText();

            // Actualizar el proveedor en la base de datos
            actualizarProveedorEnBD(proveedorSeleccionado.getIdProveedor(), nuevoNombre, nuevaDireccion, nuevoTelefono);

            // Actualizar la lista y la tabla
            Proveedor proveedorActualizado = new Proveedor(
                    proveedorSeleccionado.getIdProveedor(),
                    nuevoNombre,
                    nuevaDireccion,
                    nuevoTelefono
            );

            // Reemplazar el proveedor antiguo con el actualizado en la lista
            int indiceProveedor = proveedoresData.indexOf(proveedorSeleccionado);
            proveedoresData.set(indiceProveedor, proveedorActualizado);

            // Refrescar la tabla
            tablaProveedores.refresh();
        }
    }

    private void actualizarProveedorEnBD(int idProveedor, String nombre, String direccion, String telefono) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/erp", "root", "")) {
            String sql = "UPDATE Proveedor SET nombre=?, direccion=?, contacto=? WHERE proveedorId=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, nombre);
                statement.setString(2, direccion);
                statement.setString(3, telefono);
                statement.setInt(4, idProveedor);

                // Ejecutar la actualización
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void registrarProveedor(ActionEvent event) {
        // Obtener los valores de los campos
        String nuevoNombre = TextFieldNombreProveedor.getText();
        String nuevaDireccion = TextFieldDirectProveedor.getText();
        String nuevoTelefono = TextFieldTelefonoProveedor.getText();

        // Agregar el nuevo proveedor a la base de datos
        agregarProveedorEnBD(nuevoNombre, nuevaDireccion, nuevoTelefono);

        // Actualizar la lista y la tabla
        Proveedor nuevoProveedor = new Proveedor(0, nuevoNombre, nuevaDireccion, nuevoTelefono); // El 0 es temporal, ya que se generará automáticamente en la base de datos
        proveedoresData.add(nuevoProveedor);

        // Limpiar los campos después de agregar
        limpiarCamposProveedores();
    }

    private void agregarProveedorEnBD(String nombre, String direccion, String telefono) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/erp", "root", "")) {
            String sql = "INSERT INTO Proveedor (nombre, direccion, contacto) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, nombre);
                statement.setString(2, direccion);
                statement.setString(3, telefono);

                // Ejecutar la inserción
                statement.executeUpdate();

                // Obtener el ID generado automáticamente
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int idGenerado = generatedKeys.getInt(1);
                    // Actualizar el proveedor con el ID generado
                    for (Proveedor proveedor : proveedoresData) {
                        if (proveedor.getNombre().equals(nombre) && proveedor.getDireccion().equals(direccion) &&
                                proveedor.getTelefono().equals(telefono)) {
                            proveedor.setIdProveedor(idGenerado);
                            break;
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para limpiar los campos de la pestaña "Proveedores"
    @FXML
    public void limpiarCamposProveedores() {
        TextFieldNombreProveedor.clear();
        TextFieldDirectProveedor.clear();
        TextFieldTelefonoProveedor.clear();
    }

    private void cargarProveedoresDesdeBD() {
        colIDProvProve.setCellValueFactory(cellData -> cellData.getValue().idProveedorProperty().asObject());
        colNombreProve.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        colDireccionProve.setCellValueFactory(cellData -> cellData.getValue().direccionProperty());
        colTelefonoProve.setCellValueFactory(cellData -> cellData.getValue().telefonoProperty());

        // Conexión a la base de datos
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/erp", "root", "")) {
            // Consulta SQL para obtener los proveedores
            String sql = "SELECT proveedorId, nombre, direccion, contacto FROM Proveedor";
            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                // Limpiamos la lista antes de agregar nuevos datos
                proveedoresData.clear();

                // Agregamos los proveedores desde la base de datos
                while (resultSet.next()) {
                    int idProveedor = resultSet.getInt("proveedorId");
                    String nombreProveedor = resultSet.getString("nombre");
                    String direccionProveedor = resultSet.getString("direccion");
                    String telefonoProveedor = resultSet.getString("contacto");
                    proveedoresData.add(new Proveedor(idProveedor, nombreProveedor, direccionProveedor, telefonoProveedor));
                }

                // Establecemos los datos en la tabla de proveedores
                tablaProveedores.setItems(proveedoresData);

                // Llamamos al método para cargar nombres de proveedores en el ComboBox
                cargarProveedoresEnComboBox();

                // Seleccionar el primer proveedor en la tabla (puedes cambiar esto según tus necesidades)
                if (!proveedoresData.isEmpty()) {
                    tablaProveedores.getSelectionModel().selectFirst();
                    mostrarDetallesProveedor(tablaProveedores.getSelectionModel().getSelectedItem());
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void mostrarDetallesProveedor(Proveedor proveedor) {
        if (proveedor != null) {
            TextFieldNombreProveedor.setText(proveedor.getNombre());
            TextFieldDirectProveedor.setText(proveedor.getDireccion());
            TextFieldTelefonoProveedor.setText(proveedor.getTelefono());
        }
    }
    private void buscarClientePorNombre(String nombre) {
        // Implementa la lógica de búsqueda por nombre aquí
        // Puedes filtrar los elementos en la lista y luego actualizar la tabla
        tablaClientes.setItems(clientesData.filtered(cliente -> cliente.getNombre().toLowerCase().contains(nombre.toLowerCase())));
    }

    private void buscarClientePorDireccion(String direccion) {
        // Implementa la lógica de búsqueda por dirección aquí
        // Puedes filtrar los elementos en la lista y luego actualizar la tabla
        tablaClientes.setItems(clientesData.filtered(cliente -> cliente.getDireccion().toLowerCase().contains(direccion.toLowerCase())));
    }

    private void buscarClientePorDNI(String dni) {
        // Implementa la lógica de búsqueda por DNI aquí
        // Puedes filtrar los elementos en la lista y luego actualizar la tabla
        tablaClientes.setItems(clientesData.filtered(cliente -> cliente.getDni().toLowerCase().contains(dni.toLowerCase())));
    }
    @FXML
    public void limpiarCamposCliente(ActionEvent event) {
        limpiarCamposClientes();
    }
    @FXML
    public void editarCliente(ActionEvent event) {
        // Obtener el cliente seleccionado
        Cliente clienteSeleccionado = tablaClientes.getSelectionModel().getSelectedItem();

        // Verificar si hay un cliente seleccionado
        if (clienteSeleccionado != null) {
            // Obtener los nuevos valores de los campos
            String nuevoNombre = TextFieldNombreClient.getText();
            String nuevaDireccion = TextFieldDirectClient.getText();
            String nuevoTelefono = TextFieldTelClient.getText();
            String nuevoDNI = TextFieldDNIClient.getText();

            // Actualizar el cliente en la base de datos
            actualizarClienteEnBD(clienteSeleccionado.getIdCliente(), nuevoNombre, nuevaDireccion, nuevoTelefono, nuevoDNI);

            // Actualizar la lista y la tabla
            Cliente clienteActualizado = new Cliente(
                    clienteSeleccionado.getIdCliente(),
                    nuevoNombre,
                    nuevaDireccion,
                    nuevoTelefono,
                    nuevoDNI
            );

            // Reemplazar el cliente antiguo con el actualizado en la lista
            int indiceCliente = clientesData.indexOf(clienteSeleccionado);
            clientesData.set(indiceCliente, clienteActualizado);

            // Refrescar la tabla
            tablaClientes.refresh();
        }
    }

    private void actualizarClienteEnBD(int idCliente, String nombre, String direccion, String telefono, String dni) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/erp", "root", "")) {
            String sql = "UPDATE Cliente SET nombre=?, direccion=?, contacto=?, dni=? WHERE clienteId=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, nombre);
                statement.setString(2, direccion);
                statement.setString(3, telefono);
                statement.setString(4, dni);
                statement.setInt(5, idCliente);

                // Ejecutar la actualización
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void eliminarCliente(ActionEvent event) {
        // Obtener el cliente seleccionado
        Cliente clienteSeleccionado = tablaClientes.getSelectionModel().getSelectedItem();

        // Verificar si hay un cliente seleccionado
        if (clienteSeleccionado != null) {
            // Eliminar el cliente de la base de datos
            eliminarClienteEnBD(clienteSeleccionado.getIdCliente());

            // Eliminar el cliente de la lista
            clientesData.remove(clienteSeleccionado);

            // Limpiar los campos después de eliminar
            limpiarCamposClientes();
        }
    }

    private void eliminarClienteEnBD(int idCliente) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/erp", "root", "")) {
            String sql = "DELETE FROM Cliente WHERE clienteId=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, idCliente);

                // Ejecutar la eliminación
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void registrarCliente(ActionEvent event) {
        // Obtener los valores de los campos
        String nuevoNombre = TextFieldNombreClient.getText();
        String nuevaDireccion = TextFieldDirectClient.getText();
        String nuevoTelefono = TextFieldTelClient.getText();
        String nuevoDNI = TextFieldDNIClient.getText();

        // Agregar el nuevo cliente a la base de datos
        agregarClienteEnBD(nuevoNombre, nuevaDireccion, nuevoTelefono, nuevoDNI);

        // Actualizar la lista y la tabla
        Cliente nuevoCliente = new Cliente(0, nuevoNombre, nuevaDireccion, nuevoTelefono, nuevoDNI); // El 0 es temporal, ya que se generará automáticamente en la base de datos
        clientesData.add(nuevoCliente);

        // Limpiar los campos después de agregar
        limpiarCamposClientes();
    }

    private void agregarClienteEnBD(String nombre, String direccion, String contacto, String dni) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/erp", "root", "")) {
            String sql = "INSERT INTO Cliente (nombre, direccion, contacto, dni) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, nombre);
                statement.setString(2, direccion);
                statement.setString(3, contacto);
                statement.setString(4, dni);

                // Ejecutar la inserción
                statement.executeUpdate();

                // Obtener el ID generado automáticamente
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int idGenerado = generatedKeys.getInt(1);
                    // Actualizar el cliente con el ID generado
                    for (Cliente cliente : clientesData) {
                        if (cliente.getNombre().equals(nombre) && cliente.getDireccion().equals(direccion) &&
                                cliente.getTelefono().equals(contacto) && cliente.getDni().equals(dni)) {
                            cliente.setIdCliente(idGenerado);
                            break;
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para limpiar los campos de la pestaña "Clientes"
    private void limpiarCamposClientes() {
        TextFieldNombreClient.clear();
        TextFieldDirectClient.clear();
        TextFieldTelClient.clear();
        TextFieldDNIClient.clear();
    }


    private void mostrarDetallesCliente(Cliente cliente) {
        if (cliente != null) {
            TextFieldNombreClient.setText(cliente.getNombre());
            TextFieldDirectClient.setText(cliente.getDireccion());
            TextFieldTelClient.setText(cliente.getTelefono());
            TextFieldDNIClient.setText(cliente.getDni());
        }
    }

    public void cargarClientesDesdeBD() {
        colIDClient.setCellValueFactory(cellData -> cellData.getValue().idClienteProperty().asObject().asString());
        colNombreClient.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        colDireccionClient.setCellValueFactory(cellData -> cellData.getValue().direccionProperty());
        colTelefonoClient.setCellValueFactory(cellData -> cellData.getValue().telefonoProperty());
        colDNIClient.setCellValueFactory(cellData -> cellData.getValue().dniProperty());

        // Conexión a la base de datos
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/erp", "root", "")) {
            // Consulta SQL para obtener los clientes
            String sql = "SELECT clienteId, dni, nombre, direccion, contacto FROM Cliente";
            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                // Limpiamos la lista antes de agregar nuevos datos
                clientesData.clear();

                // Agregamos los clientes desde la base de datos
                while (resultSet.next()) {
                    int idCliente = resultSet.getInt("clienteId");
                    String dni = resultSet.getString("dni");
                    String nombre = resultSet.getString("nombre");
                    String direccion = resultSet.getString("direccion");
                    String contacto = resultSet.getString("contacto");
                    clientesData.add(new Cliente(idCliente, nombre, direccion, contacto, dni));
                }

                // Establecemos los datos en la tabla de clientes
                tablaClientes.setItems(clientesData);

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void agregarProducto(ActionEvent event) {
        // Obtener los valores de los campos
        String nuevoNombre = TextFieldNombreProducto.getText();
        String nuevaDescripcion = AreaDescripcionProducto.getText();
        double nuevoPrecio = Double.parseDouble(TextFieldPreciProducto.getText());
        int nuevaCantidad = Integer.parseInt(TextFieldCantidadProducto.getText());

        // Agregar el nuevo producto a la base de datos
        agregarProductoEnBD(nuevoNombre, nuevaDescripcion, nuevoPrecio, nuevaCantidad);

        // Actualizar la lista y la tabla
        Producto nuevoProducto = new Producto(0, nuevoNombre, nuevaDescripcion, nuevoPrecio, nuevaCantidad); // El 0 es temporal, ya que se generará automáticamente en la base de datos
        productosData.add(nuevoProducto);

        // Limpiar los campos después de agregar
        limpiarCamposInventarios();
    }
    private void agregarProductoEnBD(String nombre, String descripcion, double precio, int stock) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/erp", "root", "")) {
            String sql = "INSERT INTO productos (nombre, descripcion, precio, stock) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, nombre);
                statement.setString(2, descripcion);
                statement.setDouble(3, precio);
                statement.setInt(4, stock);

                // Ejecutar la inserción
                statement.executeUpdate();

                // Obtener el ID generado automáticamente
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int idGenerado = generatedKeys.getInt(1);
                    // Actualizar el producto con el ID generado
                    for (Producto producto : productosData) {
                        if (producto.getNombre().equals(nombre) && producto.getDescripcion().equals(descripcion) &&
                                producto.getPrecio() == precio && producto.getStock() == stock) {
                            producto.setIdProd(idGenerado);
                            break;
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void eliminarProducto(ActionEvent event) {
        // Obtener el producto seleccionado
        Producto productoSeleccionado = tablaInventarios.getSelectionModel().getSelectedItem();

        // Verificar si hay un producto seleccionado
        if (productoSeleccionado != null) {
            // Eliminar el producto de la base de datos
            eliminarProductoEnBD(productoSeleccionado.getIdProd());

            // Eliminar el producto de la lista
            productosData.remove(productoSeleccionado);

            // Limpiar los campos después de eliminar
            limpiarCamposInventarios();
        }
    }
    private void eliminarProductoEnBD(int idProd) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/erp", "root", "")) {
            String sql = "DELETE FROM productos WHERE idProd=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, idProd);

                // Ejecutar la eliminación
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para limpiar los campos de la pestaña "Inventarios"
    private void limpiarCamposInventarios() {
        TextFieldNombreProducto.clear();
        AreaDescripcionProducto.clear();
        TextFieldPreciProducto.clear();
        TextFieldCantidadProducto.clear();
    }
    // Evento del botón "BotonLimpiarProducto"
    public void limpiarCamposProducto(ActionEvent event) {
        limpiarCamposInventarios();
    }
    // Método para mostrar los detalles del producto seleccionado en los campos correspondientes
    private void mostrarDetallesProducto(Producto producto) {
        if (producto != null) {
            TextFieldNombreProducto.setText(producto.getNombre());
            AreaDescripcionProducto.setText(producto.getDescripcion());
            TextFieldPreciProducto.setText(String.valueOf(producto.getPrecio()));
            TextFieldCantidadProducto.setText(String.valueOf(producto.getStock()));
        }
    }

    public void acti() {
        colIDProdInvent.setCellValueFactory(cellData -> cellData.getValue().idProdProperty().asObject());
        colNombreInvent.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        colDescripcionInvent.setCellValueFactory(cellData -> cellData.getValue().descripcionProperty());
        colPrecioInvent.setCellValueFactory(cellData -> cellData.getValue().precioProperty().asObject());
        colCantidadInvent.setCellValueFactory(cellData -> cellData.getValue().stockProperty().asObject());

        // Conexión a la base de datos
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/erp", "root", "")) {
            // Consulta SQL para obtener los productos
            String sql = "SELECT idProd, nombre, descripcion, precio, stock FROM productos";
            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                // Limpiamos la lista antes de agregar nuevos datos
                productosData.clear();

                // Agregamos los productos desde la base de datos
                while (resultSet.next()) {
                    int idProd = resultSet.getInt("idProd");
                    String nombre = resultSet.getString("nombre");
                    String descripcion = resultSet.getString("descripcion");
                    double precio = resultSet.getDouble("precio");
                    int stock = resultSet.getInt("stock");
                    productosData.add(new Producto(idProd, nombre, descripcion, precio, stock));
                }

                // Establecemos los datos en la tabla
                tablaInventarios.setItems(productosData);

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Agregar el manejador para el botón "BotonActualizarProducto"
    @FXML
    public void actualizarProducto(ActionEvent event) {
        // Obtener el producto seleccionado
        Producto productoSeleccionado = tablaInventarios.getSelectionModel().getSelectedItem();

        // Verificar si hay un producto seleccionado
        if (productoSeleccionado != null) {
            // Obtener los nuevos valores de los campos
            String nuevoNombre = TextFieldNombreProducto.getText();
            String nuevaDescripcion = AreaDescripcionProducto.getText();
            double nuevoPrecio = Double.parseDouble(TextFieldPreciProducto.getText());
            int nuevaCantidad = Integer.parseInt(TextFieldCantidadProducto.getText());

            // Actualizar el producto en la base de datos
            actualizarProductoEnBD(productoSeleccionado.getIdProd(), nuevoNombre, nuevaDescripcion, nuevoPrecio, nuevaCantidad);

            // Actualizar la lista y la tabla
            Producto productoActualizado = new Producto(productoSeleccionado.getIdProd(), nuevoNombre, nuevaDescripcion, nuevoPrecio, nuevaCantidad);

            // Reemplazar el producto antiguo con el actualizado en la lista
            int indiceProducto = productosData.indexOf(productoSeleccionado);
            productosData.set(indiceProducto, productoActualizado);

            // Refrescar la tabla
            tablaInventarios.refresh();
        }
    }

    // Método para actualizar el producto en la base de datos
    private void actualizarProductoEnBD(int idProd, String nombre, String descripcion, double precio, int stock) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/erp", "root", "")) {
            String sql = "UPDATE productos SET nombre=?, descripcion=?, precio=?, stock=? WHERE idProd=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, nombre);
                statement.setString(2, descripcion);
                statement.setDouble(3, precio);
                statement.setInt(4, stock);
                statement.setInt(5, idProd);

                // Ejecutar la actualización
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void registVenta() throws SQLException {
        String prod = SeleccionarProductoVentas.getValue().toString();
        String cli = SeleccionarClienteVentas.getValue().toString();
        String prov = SeleccionarProveedorVentas.getValue().toString();
        Date fecha = Date.valueOf(DatePickerFechaVentas.getValue());
        int cant = Integer.parseInt(TextFieldCantidadVentas.getText());

        int idcliente = 0;
        int idproveedor = 0;
        int idproducto = 0;

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/erp", "root", "");
        String considprod = String.format("SELECT idProd FROM productos where nombre = '%s'", prod);
        String considcli = String.format("SELECT clienteId FROM cliente where nombre = '%s'", cli);
        String considprov = String.format("SELECT proveedorId FROM proveedor where nombre = '%s'", prov);
        Statement statement = connection.createStatement();

        //Id producto seleccionado
        ResultSet rsidprod = statement.executeQuery(considprod);
        while (rsidprod.next()) {
            idproducto = rsidprod.getInt(1);
        }
        //Id proveedor seleccionado
        ResultSet rsidcli = statement.executeQuery(considcli);
        while (rsidcli.next()) {
            idcliente = rsidcli.getInt(1);
        }
        //Id cliente seleccionado
        ResultSet rsidprov = statement.executeQuery(considprov);
        while (rsidprov.next()) {
            idproveedor = rsidprov.getInt(1);
        }

        String insert = String.format("INSERT INTO venta (cantidad, fecha, clienteId, idProd, proveedorId) VALUES (%s, '%s', %s, %s, %s)",cant, fecha, idcliente, idproducto, idproveedor);
        statement.execute(insert);
    }
}