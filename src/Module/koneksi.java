/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Module;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author LENOVO
 */
public class koneksi {
    private Connection koneksi;

    public static Connection connect() {
        Connection cn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/m_listrik", "root", "");
        } catch (Exception e) {
            System.out.println(e);
        }
        return cn;
    }

    public static int Execute(String SQL) {
        int i = 0;
        try {
            Connection cn = connect();
            Statement st = cn.createStatement();
            i = st.executeUpdate(SQL);
        } catch (Exception e) {
            System.out.println(e);
        }
        return i;
    }
    
    public static ResultSet ExecuteQuery(String SQL) {
        ResultSet rs = null;
        try {
            Connection cn = connect();
            Statement st = cn.createStatement();
            rs = st.executeQuery(SQL);
        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }
}
