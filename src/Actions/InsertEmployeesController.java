package Actions;

import Users.*;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class InsertEmployeesController extends Methods implements Initializable {

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
    private DatePicker dateOfBirth;
    @FXML
    private DatePicker constracctStart;
    @FXML
    private DatePicker constracctEnd;
    private LocalDate dateBirth;
    private LocalDate datecon_start;
    private LocalDate datecon_end;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    public void addEmployee(ActionEvent event) throws SQLException, ClassNotFoundException {
        dateBirth = dateOfBirth.getValue();
        datecon_start = constracctStart.getValue();
        datecon_end = constracctEnd.getValue();

        Employees emp = new Employees();
        emp.setFname(first_name.getText());
        emp.setLname(last_name.getText());
        emp.setSalary(Float.parseFloat(salary.getText()));
        emp.setAge(Integer.parseInt(age.getText()));
        emp.setDepartment(dept.getText());
        emp.setAddress(address.getText());
        emp.setDate_of_birth(dateBirth.toString());
        emp.setGender(gender.getText());
        emp.setEducation( eduction.getText());
        emp.setExperience(experince.getText());
        emp.setConstract_start(datecon_start.toString());
        emp.setContract_end(datecon_end.toString());
        emp.setNationality(nationality.getText());
        emp.setMother_language(motherLang.getText());
        emp.setOther_language(otherLang.getText());
        
        int status = EmployeesDB.insert(emp);
        
        if(status > 0){
            alertDialog("EMPLOYEE ADDED", "ALERT", "INFORMATION");
        } else {
            alertDialog("EMPLOYEE NOT ADDED. Please provide valid data", "ALERT", "WARNING");
        }
    }

}
