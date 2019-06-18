package Users;

import Main.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeesDB {

    private static Connection conn = DBConnection.getConnection();

    public static int insert(Employees emp) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO `employees`(`fname`, `name`, `salary`, `age`, `department`, `address`, `date_of_birth`, `gender`, `education`, `experience`, `contract_start`, `contract_end`, `nationality`, `mother_language`, `other_language`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, emp.getFname());
        ps.setString(2, emp.getLname());
        ps.setFloat(3, emp.getSalary());
        ps.setInt(4, emp.getAge());
        ps.setString(5, emp.getDepartment());
        ps.setString(6, emp.getAddress());
        ps.setString(7, emp.getDate_of_birth());
        ps.setString(8, emp.getGender());
        ps.setString(9, emp.getEducation());
        ps.setString(10, emp.getExperience());
        ps.setString(11, emp.getConstract_start());
        ps.setString(12, emp.getContract_end());
        ps.setString(13, emp.getNationality());
        ps.setString(14, emp.getMother_language());
        ps.setString(15, emp.getOther_language());
        int st = ps.executeUpdate();

        return st;
    }

    public static int update(Employees emp) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE `employees` SET `fname`=?,`name`=?,`salary`=?,`age`=?,`department`=?,`address`=?,`date_of_birth`=?,`gender`=?,`education`=?,`experience`=?,`contract_start`=?,`contract_end`=?,`nationality`=?,`mother_language`=?,`other_language`=? WHERE `id`=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, emp.getFname());
        ps.setString(2, emp.getLname());
        ps.setFloat(3, emp.getSalary());
        ps.setInt(4, emp.getAge());
        ps.setString(5, emp.getDepartment());
        ps.setString(6, emp.getAddress());
        ps.setString(7, emp.getDate_of_birth());
        ps.setString(8, emp.getGender());
        ps.setString(9, emp.getEducation());
        ps.setString(10, emp.getExperience());
        ps.setString(11, emp.getConstract_start());
        ps.setString(12, emp.getContract_end());
        ps.setString(13, emp.getNationality());
        ps.setString(14, emp.getMother_language());
        ps.setString(15, emp.getOther_language());
        ps.setInt(16, emp.getId());
        int st = ps.executeUpdate();

        return st;
    }

    public static int delete(int id) throws ClassNotFoundException, SQLException {
        String sql = "DELETE FROM `employees` WHERE `id`=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        int st = ps.executeUpdate();
        return st;
    }
    public static Employees getEmp(int id) throws ClassNotFoundException, SQLException {
        Employees emp = new Employees();
        String sql = "SELECT * FROM `employees` WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            emp.setId(rs.getInt(1));
            emp.setFname(rs.getString(2));
            emp.setLname(rs.getString(3));
            emp.setSalary(rs.getFloat(4));
            emp.setAge(rs.getInt(5));
            emp.setDepartment(rs.getString(6));
            emp.setAddress(rs.getString(7));
            emp.setDate_of_birth(rs.getString(8));
            emp.setGender(rs.getString(9));
            emp.setEducation(rs.getString(10));
            emp.setExperience(rs.getString(11));
            emp.setConstract_start(rs.getString(12));
            emp.setContract_end(rs.getString(13));
            emp.setNationality(rs.getString(14));
            emp.setMother_language(rs.getString(15));
            emp.setOther_language(rs.getString(16));
        }
        return emp;
    }
    public static List<Employees> getEmployees() throws ClassNotFoundException, SQLException {
        List<Employees> list = new ArrayList<Employees>();
        String sql = "SELECT * FROM `employees` WHERE 1";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Employees emp = new Employees();
            emp.setId(rs.getInt(1));
            emp.setFname(rs.getString(2));
            emp.setLname(rs.getString(3));
            emp.setSalary(rs.getFloat(4));
            emp.setAge(rs.getInt(5));
            emp.setDepartment(rs.getString(6));
            emp.setAddress(rs.getString(7));
            emp.setDate_of_birth(rs.getString(8));
            emp.setGender(rs.getString(9));
            emp.setEducation(rs.getString(10));
            emp.setExperience(rs.getString(11));
            emp.setConstract_start(rs.getString(12));
            emp.setContract_end(rs.getString(13));
            emp.setNationality(rs.getString(14));
            emp.setMother_language(rs.getString(15));
            emp.setOther_language(rs.getString(16));
            list.add(emp);
        }
        return list;
    }
}
