package testeos.Controllers.Client;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import testeos.Controllers.Structures.ColaClientes;
import testeos.Controllers.Structures.Mesa;
import testeos.Models.Model;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    public Label login_date;
    public Button table_btn;

    public Label table_cap_1;
    public FontAwesomeIconView table_status_1;
    public Label table_cap_2;
    public FontAwesomeIconView table_status_2;
    public Label table_cap_3;
    public FontAwesomeIconView table_status_3;
    public Label table_cap_4;
    public FontAwesomeIconView table_status_4;
    public Label table_cap_5;
    public FontAwesomeIconView table_status_5;
    public Label table_cap_6;
    public FontAwesomeIconView table_status_6;
    public Label table_cap_7;
    public FontAwesomeIconView table_status_7;
    public Label table_cap_8;
    public FontAwesomeIconView table_status_8;
    public Label table_cap_9;
    public FontAwesomeIconView table_status_9;
    public Label table_cap_10;
    public FontAwesomeIconView table_status_10;

    public TextField table_choosen_fld;
    public TextField dni_costumer_fld;
    public TextField name_costumer_fld;
    public TextField name_waiter_fld;
    public Label error_lbl;

    private Mesa[] mesas;
    private ColaClientes colaClientes;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().setDashboardController(this);
        bindData();
        actualizarColoresDeEstado();
        table_btn.setOnAction(event -> agregarCliente());
    }

    public ColaClientes getCola(){
        return this.colaClientes;
    }

    public DashboardController() {
        mesas = new Mesa[10];
        colaClientes = new ColaClientes();
        mesas[0] = new Mesa(1, 4);
        mesas[1] = new Mesa(2, 4);
        mesas[2] = new Mesa(3, 2);
        mesas[3] = new Mesa(4, 6);
        mesas[4] = new Mesa(5, 8);
        mesas[5] = new Mesa(6, 10);
        mesas[6] = new Mesa(7, 6);
        mesas[7] = new Mesa(8, 6);
        mesas[8] = new Mesa(9, 4);
        mesas[9] = new Mesa(10, 4);
    }

    private void bindData() {
        login_date.setText("Hoy, " + LocalDate.now());
    }

    private FontAwesomeIconView getTableStatus(int mesaId) {
        switch (mesaId) {
            case 1: return table_status_1;
            case 2: return table_status_2;
            case 3: return table_status_3;
            case 4: return table_status_4;
            case 5: return table_status_5;
            case 6: return table_status_6;
            case 7: return table_status_7;
            case 8: return table_status_8;
            case 9: return table_status_9;
            case 10: return table_status_10;
            default: return null;
        }
    }

    private Label getTableCap(int mesaId) {
        switch (mesaId) {
            case 1: return table_cap_1;
            case 2: return table_cap_2;
            case 3: return table_cap_3;
            case 4: return table_cap_4;
            case 5: return table_cap_5;
            case 6: return table_cap_6;
            case 7: return table_cap_7;
            case 8: return table_cap_8;
            case 9: return table_cap_9;
            case 10: return table_cap_10;
            default: return null;
        }
    }

    /*
    * Utility Methods
    * */

    public void agregarCliente() {
        try {
            int mesaId = Integer.parseInt(table_choosen_fld.getText());
            if (mesaId < 1 || mesaId > mesas.length) {
                error_lbl.setTextFill(Color.RED);
                error_lbl.setText("Numero de mesa no encontrado");
                return;
            }
            if(mesaLlena(mesaId)){
                Mesa numeroDeMesa = mesas[mesaId - 1];
                colaClientes.Encolar(
                        name_costumer_fld.getText(),
                        dni_costumer_fld.getText(),
                        numeroDeMesa.clientes.getHoraAsignacion(),
                        name_waiter_fld.getText(),
                        mesaId
                );

                numeroDeMesa.clientes.agregar(name_costumer_fld.getText(), dni_costumer_fld.getText(), name_waiter_fld.getText());
                int cantidad = numeroDeMesa.getCantidad();
                numeroDeMesa.setCantidad(cantidad + 1);
                setStatusColor();

                getTableCap(mesaId).setText(String.valueOf(numeroDeMesa.getCantidad()) + "/" + String.valueOf(numeroDeMesa.getCapacidad()));
                Model.getInstance().setAllTransactions();
                emptyfields();
            }

        } catch (NumberFormatException e) {
            error_lbl.setTextFill(Color.RED);
            error_lbl.setText("Valor ingresado no valido, ingrese un numero");
        }
    }

    public void eliminarCliente(String dni, String Mesa) {
        Mesa numeroDeMesa = mesas[Integer.parseInt(Mesa) - 1];
        colaClientes.desencolarPorId(dni);
        numeroDeMesa.clientes.eliminar(dni);

        int cantidad = numeroDeMesa.getCantidad();
        numeroDeMesa.setCantidad(cantidad - 1);
        getTableCap(Integer.parseInt(Mesa)).setText(String.valueOf(numeroDeMesa.getCantidad()) + "/" + String.valueOf(numeroDeMesa.getCapacidad()));

        Model.getInstance().setAllTransactions();
    }

    public void emptyfields(){
        table_choosen_fld.setText("");
        dni_costumer_fld.setText("");
        name_costumer_fld.setText("");
        name_waiter_fld.setText("");
    }

    public boolean mesaLlena(int id){
        if(mesas[Integer.parseInt(table_choosen_fld.getText())-1].getCantidad() < mesas[Integer.parseInt(table_choosen_fld.getText())-1].getCapacidad()){
            return true;
        } else{
            error_lbl.setTextFill(Color.RED);
            error_lbl.setText("Mesa llena");
            return false;
        }
    }

    public void setStatusColor(){
        if(mesas[Integer.parseInt(table_choosen_fld.getText())-1].getCantidad() == mesas[Integer.parseInt(table_choosen_fld.getText())-1].getCapacidad()){
            getTableStatus(Integer.parseInt(table_choosen_fld.getText())).setFill(Color.RED);
        } else {
            getTableStatus(Integer.parseInt(table_choosen_fld.getText())).setFill(Color.GREEN);
        }
    }

    public void actualizarColoresDeEstado() {
        for (int i = 1; i <= mesas.length; i++) {
            Mesa mesa = mesas[i - 1];
            if (mesa.getCantidad() == mesa.getCapacidad()) {
                getTableStatus(i).setFill(Color.RED);
            } else {
                getTableStatus(i).setFill(Color.GREEN);
            }
        }
    }
}
