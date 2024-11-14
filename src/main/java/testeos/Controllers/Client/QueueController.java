package testeos.Controllers.Client;

import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import testeos.Models.Model;
import testeos.Models.QueueClient;
import testeos.Views.QueueCellFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class QueueController implements Initializable {
    public ListView<QueueClient> transactions_listview;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().setAllTransactions();
        transactions_listview.setItems(Model.getInstance().getAllTransactions());
        transactions_listview.setCellFactory(event -> new QueueCellFactory());
    }

    private void initAllTransactionsList() {
        if(Model.getInstance().getAllTransactions().isEmpty()) {
            Model.getInstance().setAllTransactions();
        }
    }
}
