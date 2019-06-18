package Actions;

import Main.DBConnection;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import Users.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

public class ViewEmployeesController extends Methods implements Initializable{

    @FXML
    private TableView<Employees> table1;
    @FXML
    private TableColumn<Employees, Integer> id;
    @FXML
    private TableColumn<Employees, String> first_name;
    @FXML
    private TableColumn<Employees, String> last_name;
    @FXML
    private TableColumn<Employees, Float> salary;
    @FXML
    private TableColumn<Employees, Integer> age;
    @FXML
    private TableColumn<Employees, String> dept;
    @FXML
    private TableColumn<Employees, String> address;
    @FXML
    private TableColumn<Employees, String> dateOfBirth;

    private ObservableList<Employees> data = FXCollections.observableArrayList(); //used with table view. will make list that suits the table
    private Connection conn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String sql = "SELECT * FROM `employees` WHERE 1";
        try {
            conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                data.add(new Employees(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4), rs.getInt(5), rs.getString(6),
                         rs.getString(7), rs.getString(8)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ViewEmployeesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        id.setCellValueFactory(new PropertyValueFactory<Employees, Integer>("id"));
        first_name.setCellValueFactory(new PropertyValueFactory<Employees, String>("fname"));
        last_name.setCellValueFactory(new PropertyValueFactory<Employees, String>("name"));
        salary.setCellValueFactory(new PropertyValueFactory<Employees, Float>("salary"));
        age.setCellValueFactory(new PropertyValueFactory<Employees, Integer>("age"));
        dept.setCellValueFactory(new PropertyValueFactory<Employees, String>("department"));
        address.setCellValueFactory(new PropertyValueFactory<Employees, String>("address"));
        dateOfBirth.setCellValueFactory(new PropertyValueFactory<Employees, String>("date_of_birth"));

        table1.setItems(data);
    }

}
