package testeos.Controllers.Client;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import testeos.Models.Model;
import testeos.Models.QueueClient;

import java.net.URL;
import java.util.ResourceBundle;

public class QueueCellController implements Initializable {

    public Label costumer_lbl;
    public Label DNI_lbl;
    public Label waiter_lbl;
    public Label hora_asignada_lbl;
    public Label tiempo_esperando_lbl;
    public Label mesa_asignada_lbl;
    public Button eliminar_btn;

    private final QueueClient qclient;

    public QueueCellController(QueueClient qclient) {
        this.qclient = qclient;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        costumer_lbl.textProperty().bind(qclient.costumerProperty());
        DNI_lbl.textProperty().bind(qclient.dNIProperty());
        waiter_lbl.textProperty().bind(qclient.waiterProperty());
        hora_asignada_lbl.textProperty().bind(qclient.hora_asignadaProperty());
        tiempo_esperando_lbl.textProperty().bind(qclient.tiempo_esperandoProperty());
        mesa_asignada_lbl.textProperty().bind(qclient.mesa_asignadaProperty());
        eliminar_btn.setOnAction(event -> {
            String dniCliente = qclient.dNIProperty().get();
            String numMesa = qclient.mesa_asignadaProperty().get();
            Model.getInstance().getDashboardController().eliminarCliente(dniCliente, numMesa);

        });
    }



}
