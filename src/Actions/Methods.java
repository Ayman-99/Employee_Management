package Actions;

import javafx.scene.control.Alert;

public abstract class Methods {

    public void alertDialog(String message, String title, String type) {
            Alert alert = new Alert(Alert.AlertType.valueOf(type));
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
    }

}
