package testeos.Controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import testeos.Models.Model;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public Label payee_address_lbl;
    public TextField payee_address_fld;
    public TextField password_fld;
    public Button login_btn;
    public Label error_lbl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        login_btn.setOnAction(event -> onLogin());
    }

    private void onLogin() {
        Stage stage = (Stage) error_lbl.getScene().getWindow();
        Model.getInstance().evaluateClientCred(payee_address_fld.getText(), password_fld.getText());
        if (Model.getInstance().getClientLoginSuccessFlag()){
            Model.getInstance().getViewFactory().showClientWindow();
            Model.getInstance().getViewFactory().closeStage(stage);
        } else {
            payee_address_fld.setText("");
            password_fld.setText("");
            error_lbl.setText("No existen dichas credenciales   ");
        }
    }

}
