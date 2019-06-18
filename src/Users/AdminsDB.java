package Users;

import Main.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import emailmessages.sendMail;
import java.util.Calendar;
import java.util.Date;

public class AdminsDB {

    static Connection conn = DBConnection.getConnection();

    public static List<Admins> getAdmins() throws ClassNotFoundException, SQLException {
        List<Admins> list = new ArrayList<Admins>();
        String sql = "SELECT * FROM `users` WHERE 1";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Admins adm = new Admins();
            adm.setId(rs.getInt("id"));
            adm.setUsername(rs.getString("username"));
            adm.setPassword(rs.getString("password"));
            list.add(adm);
        }
        return list;
    }

    public static String setString() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            char temp = (char) ('a' + Math.random() * ('z' - 'a'));
            sb.append(temp);
        }
        return sb.toString();
    }

    public static Date addExpireDate(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

    public static boolean insertAdmin(String username, String password, String email) throws ClassNotFoundException, SQLException {
        Date today = new Date();
        Date expire = addExpireDate(today, 10);
        java.sql.Date sqlDate = new java.sql.Date(expire.getTime());
        String sql = "INSERT INTO `users` (`username`,`password`,`email`,`date`, `serial`,`expireDate`) VALUES (?,?,?,NOW(),?,?)";
        String serial = setString();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, password);
        ps.setString(3, email);
        ps.setString(4, serial);
        ps.setDate(5, sqlDate);
        int result = ps.executeUpdate();
        sendMail.getMail(serial, email);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkActive(String username) throws ClassNotFoundException, SQLException {
        String sql = "SELECT `active` FROM `users` WHERE username=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            return rs.getInt("active") == 1;
        }
        return false;
    }

    public static String getSerial(String username) throws ClassNotFoundException, SQLException {
        String sql = "SELECT `serial` FROM `users` WHERE `username`=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        String serial = "";
        while (rs.next()) {
            serial = rs.getString("serial");
        }
        return serial;
    }

    public static void setActive(String username) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE `users` SET `active`=1 WHERE `username`=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, username);
        ps.executeUpdate();
    }
}
