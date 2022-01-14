package connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author sqlitetutorial.net
 */
public class UserCrud {

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//
//        UserCrud app = new UserCrud();
//        // insert three new rows
//        app.insert("Raw Materials", 3000);
//        app.insert("Semifinished Goods", 4000);
//        app.insert("Finished Goods", 5000);
//    }


    /**
     * Insert a new row into the warehouses table
     *
     * @param name
     * @param capacity
     */
    public void insert(String name, double capacity) {
        String sql = "INSERT INTO warehouses(name,capacity) VALUES(?,?)";
        System.out.println("User insert");

        try (Connection conn = DB.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setDouble(2, capacity);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //check username
    public boolean checkUsername(String name) {
        String sql = "SELECT name FROM Users WHERE name = ?";
        boolean checkUser = false;
        ResultSet rs = null;

        try (Connection conn = DB.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();

            if (rs.next())
                checkUser = true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return checkUser;
    }

}