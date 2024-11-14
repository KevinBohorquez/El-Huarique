package testeos.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import testeos.Controllers.Client.DashboardController;
import testeos.Controllers.Structures.ColaClientes;
import testeos.Views.ViewFactory;
import java.sql.ResultSet;

public class Model {

    private static Model model;
    private final ViewFactory viewFactory;
    private final DatabaseDriver databaseDriver;
    public final ObservableList<QueueClient> allClients;
    private DashboardController dashboardController;
    private final Client client;
    private boolean clientLoginSuccessFlag;

    private Model() {
        this.viewFactory = new ViewFactory();
        this.databaseDriver = new DatabaseDriver();
        this.allClients = FXCollections.observableArrayList();
        this.clientLoginSuccessFlag = false;
        this.client = new Client("");
    }

    public static synchronized Model getInstance() {
        if (model == null){
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }

    public DashboardController getDashboardController() {
        return dashboardController;
    }

    private void prepareClients(ObservableList<QueueClient> qclient, DashboardController dashboard, int limit) {
        ColaClientes colaClientes = dashboard.getCola();
        ColaClientes.Nodo actual = colaClientes.getFrente();
        int count = 0;
        while (actual != null && count < limit) {
                String nombre = actual.nombre;
                String dni = actual.DNI;
                String horaAsignacion = actual.hora_asignacion;
                String mesero = actual.mesero;
                String tiempoEsperando = actual.hora_asignacion;
                String numMesa = String.valueOf(actual.numMesa);
                qclient.add(new QueueClient(nombre, dni, mesero, horaAsignacion, tiempoEsperando, numMesa));
                actual = actual.siguiente;
                count++;
        }
    }

    public ObservableList<QueueClient> getAllTransactions() {
        return allClients;
    }

    public void setAllTransactions() {
        if (dashboardController != null) {
            allClients.clear();
            prepareClients(this.allClients, dashboardController, 30);
        } else {
            System.out.println("Error: DashboardController no está inicializado.");
        }
    }

    /*
     * Client Method Section
     */
    public boolean getClientLoginSuccessFlag() {
        return this.clientLoginSuccessFlag;
    }

    public void setClientLoginSuccessFlag(boolean flag) {
        this.clientLoginSuccessFlag = flag;
    }

    public Client getClient() {
        return client;
    }

    public void evaluateClientCred(String pAddress, String password) {
        ResultSet resultSet = databaseDriver.getClientData(pAddress, password);
        try{
            if (resultSet.isBeforeFirst()){
                this.client.pAddressProperty().set(resultSet.getString("PayeeAddress"));
                this.clientLoginSuccessFlag = true;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
