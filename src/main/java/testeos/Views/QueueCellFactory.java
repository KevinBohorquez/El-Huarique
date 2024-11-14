package testeos.Views;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import testeos.Controllers.Client.QueueCellController;
import testeos.Models.QueueClient;

public class QueueCellFactory extends ListCell<QueueClient> {
    @Override
    protected void updateItem(QueueClient qclient, boolean empty) {
        super.updateItem(qclient, empty);
        if(empty){
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Client/TransactionCell.fxml"));
            QueueCellController controller = new QueueCellController(qclient);
            loader.setController(controller);
            setText(null);
            try {
                setGraphic(loader.load());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
