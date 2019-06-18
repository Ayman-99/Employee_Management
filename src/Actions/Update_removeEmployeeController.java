package Actions;

import Main.DBConnection;
import Users.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class Update_removeEmployeeController extends Methods implements Initializable {

    @FXML
    private TextField first_name;
    @FXML
    private TextField last_name;
    @FXML
    private TextField salary;
    @FXML
    private TextField age;
    @FXML
    private TextField dept;
    @FXML
    private TextField address;
    @FXML
    private TextField otherLang;
    @FXML
    private TextField motherLang;
    @FXML
    private TextField nationality;
    @FXML
    private TextField experince;
    @FXML
    private TextField eduction;
    @FXML
    private TextField gender;
    @FXML
    private TextField dateOfBirth;
    @FXML
    private TextField constracctStart;
    @FXML
    private TextField constracctEnd;
    @FXML
    private TextField searchText;

    private Employees emp;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void updateEmployee(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (searchText.getText().equals("") || searchText.getText().equals(null)) {
            alertDialog("Enter employee's ID!", "ALERT", "WARNING");
        } else {
            EmployeesDB.update(storeNew());
            alertDialog("Employee info updated!", "ALERT", "INFORMATION");
        }
    }

    @FXML
    private void removeEmployee(ActionEvent event) throws ClassNotFoundException, SQLException {
        if (searchText.getText().equals("") || searchText.getText().equals(null)) {
            alertDialog("Enter employee's ID!", "ALERT", "WARNING");
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("CONFIRMATION");
            alert.setContentText("Are you sure you want to delete this employee?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                EmployeesDB.delete(emp.getId());
                alertDialog("Employee removed!", "ALERT", "INFORMATION");
            }
        }
    }

    private Employees storeNew() {
        Employees emp2 = new Employees();
        emp2.setId(Integer.parseInt(searchText.getText()));
        emp2.setFname(first_name.getText());
        emp2.setLname(last_name.getText());
        emp2.setSalary(Float.parseFloat(salary.getText()));
        emp2.setAge(Integer.parseInt(age.getText()));
        emp2.setDepartment(dept.getText());
        emp2.setAddress(address.getText());
        emp2.setDate_of_birth(dateOfBirth.getText());
        emp2.setGender(gender.getText());
        emp2.setEducation(eduction.getText());
        emp2.setExperience(experince.getText());
        emp2.setConstract_start(constracctStart.getText());
        emp2.setContract_end(constracctEnd.getText());
        emp2.setNationality(nationality.getText());
        emp2.setMother_language(motherLang.getText());
        emp2.setOther_language(otherLang.getText());

        return emp2;
    }

    @FXML
    private void loadData(ActionEvent event) throws ClassNotFoundException, SQLException {
        emp = EmployeesDB.getEmp(Integer.parseInt(searchText.getText()));
        if (searchText.getText().equals("") || searchText.getText().equals(null)) {
            alertDialog("Enter employee's ID!", "ALERT", "WARNING");
        } else if (searchText.getText().equals(String.valueOf(emp.getId()))) {
            first_name.setText(emp.getFname());
            last_name.setText(emp.getLname());
            salary.setText(String.valueOf(emp.getSalary()));
            age.setText(String.valueOf(emp.getAge()));
            dept.setText(emp.getDepartment());
            address.setText(emp.getAddress());
            otherLang.setText(emp.getOther_language());
            motherLang.setText(emp.getMother_language());
            nationality.setText(emp.getNationality());
            experince.setText(emp.getExperience());
            eduction.setText(emp.getEducation());
            gender.setText(emp.getGender());
            dateOfBirth.setText(emp.getDate_of_birth());
            constracctStart.setText(emp.getConstract_start());
            constracctEnd.setText(emp.getContract_end());
        } else {
            alertDialog("Employee doesn't exist!", "ALERT", "ERROR");
        }
    }

}
