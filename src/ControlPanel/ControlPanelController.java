package ControlPanel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class ControlPanelController implements Initializable {
    private Stage window;
    Parent root;
    Scene scene;
    @FXML
    private MediaView mediaVideo;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Media media=new Media("http://94.130.11.106/dl/s10/v6ImQhrLbTRoiFpzw3l8_A,1533924438/yt:ugTaLEBu6g8-8/A%20short%20Meditation%20of%20Nature%20Sounds-Relaxing%20Birdsong-Calming%20Sound%20of%20Water%20Relaxation.mp4");
        MediaPlayer player=new MediaPlayer(media);
        mediaVideo.setMediaPlayer(player);
        player.setVolume(0);
        player.play();
        
        window = new Stage();
        window.setResizable(false);
    }    

    @FXML
    private void viewEmployee(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Actions/viewEmployees.fxml"));
        scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void update_delete_emp(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Actions/update_removeEmployee.fxml"));
        scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void insertEmployee(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Actions/insertEmployees.fxml"));
        scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }
    
}
