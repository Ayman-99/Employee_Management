package LoginStuff;

import Actions.Methods;
import Main.DBConnection;
import Users.Admins;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import Users.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import Main.main;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.geometry.Pos;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Ayman
 */
public class LoginController extends Methods implements Initializable {

    @FXML
    private Label dbCheckCon;
    @FXML
    private TextField userInput;
    @FXML
    private PasswordField passwordInput;

    private Connection conn;
    private List<Admins> list;
    private Stage window;
    @FXML
    private PasswordField regPass;
    @FXML
    private TextField regUser;
    @FXML
    private TextField regEmail;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        window = main.getStage();
        try {
            conn = DBConnection.getConnection();
            list = AdminsDB.getAdmins();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void checkDB(ActionEvent event) throws SQLException {
        try {
            if (conn.isClosed()) {
                dbCheckCon.setTextFill(Color.RED);
                dbCheckCon.setText("NOT ACTIVE. You are not connected");
            } else {
                dbCheckCon.setTextFill(Color.GREEN);
                dbCheckCon.setText("ACTIVE. You are connected");
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void login(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        //map has key and value. Key is username, value is password. Use map!
        Map<String, String> map = new HashMap<String, String>(); //Username is String and password is String

        for (Admins a : list) { //adding to the map. the key and value
            map.put(a.getUsername(), a.getPassword()); //key,value
        }
        if (map.containsKey(userInput.getText())) { //check if the input that user entered is found in the map
            String val = map.get(userInput.getText()); // if username found. We get username password from the map. Like map get(we send the key) and method return its value
            if (val.equals(passwordInput.getText())) {

                if (AdminsDB.checkActive(userInput.getText())) {
                    AnchorPane root = FXMLLoader.load(getClass().getResource("/ControlPanel/controlPanel1.fxml"));
                    main.rootPane.getChildren().clear();
                    main.rootPane.getChildren().add(root);
                } else {
                    String s = JOptionPane.showInputDialog(null, "Enter serial (You can find it in your email)");
                    if (s == null) {
                        System.exit(0);
                    } else if (s.equals("")) {
                        System.exit(0);
                    } else {
                        if (s.equals(AdminsDB.getSerial(userInput.getText()))) {
                            AdminsDB.setActive(userInput.getText());
                            Notifications note = Notifications.create()
                                    .title("ACTIVE note")
                                    .text("Your account is active!")
                                    .graphic(new Rectangle(20, 20))
                                    .hideAfter(Duration.seconds(5))
                                    .position(Pos.BOTTOM_LEFT);
                            note.showConfirm();
                        }
                    }
                }
            } else {
                alertDialog("Wrong password. Try again!", "ALERT", "WARNING");
            }
        } else {
            alertDialog("Wrong info. Try again!", "ALERT", "WARNING");
        }
    }

    @FXML
    private void register(ActionEvent event) throws ClassNotFoundException, SQLException {
        if (validatePassword()) {
            if (((regPass.getText().isEmpty() ^ regPass.getText().isEmpty())) || (regPass.getText().isEmpty() && regPass.getText().isEmpty())) {
                regEmail.setDisable(true);
            } else {
                regEmail.setDisable(false);
                if (!(regUser.getText().isEmpty() || regPass.getText().isEmpty() || regEmail.getText().isEmpty())) {
                    AdminsDB.insertAdmin(regUser.getText(), regPass.getText(), regEmail.getText());
                    Notifications note = Notifications.create()
                            .title("Registeration note")
                            .text("Successfully Registered. Check your email for serial!")
                            .graphic(new Rectangle(20, 20))
                            .hideAfter(Duration.seconds(5))
                            .position(Pos.BOTTOM_LEFT);
                    note.showConfirm();
                }
            }
        }

    }

    public boolean validatePassword() {
        /* 
        ?=.9\\d : Must contains one digit from 0-9 rule
        ?=.[a-z] : must conatins one lowercase char
        ?=.*[A-Z] : must conatins one Upper char
        ?=.*[@#$%] : must contains one of @#$%
        .{4,10} : minium and maximum chara
        Pattern p = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{4,10})"); 
         */
        Pattern p = Pattern.compile("((?=.*\\d)(?=.*[A-Z]).{4,10})");
        Matcher m = p.matcher(regPass.getText());
        if (m.matches()) {
            return true;
        } else {
            alertDialog("Password must contain at least one(Digit, uppercase)"
                    + " and length must be between 4- 10, e.g: 1234A", "ALERT", "WARNING");
            return false;
        }
    }
}

