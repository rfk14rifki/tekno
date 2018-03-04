/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Module;

/**
 *
 * @author LENOVO
 */
public class SessionLogin {
    private static String idUser;
    private static String nama;
    private static String level;
    private static String username;
    private static String password;
    private static String telepon;
    private static String alamat;
    private static String refresh = "false";

    /**
     * @return the idUser
     */
    public static String getIdUser() {
        return idUser;
    }

    /**
     * @param aIdUser the idUser to set
     */
    public static void setIdUser(String aIdUser) {
        idUser = aIdUser;
    }

    /**
     * @return the nama
     */
    public static String getNama() {
        return nama;
    }

    /**
     * @param aNama the nama to set
     */
    public static void setNama(String aNama) {
        nama = aNama;
    }

    /**
     * @return the level
     */
    public static String getLevel() {
        return level;
    }

    /**
     * @param aLevel the level to set
     */
    public static void setLevel(String aLevel) {
        level = aLevel;
    }

    /**
     * @return the username
     */
    public static String getUsername() {
        return username;
    }

    /**
     * @param aUsername the username to set
     */
    public static void setUsername(String aUsername) {
        username = aUsername;
    }

    /**
     * @return the refresh
     */
    public static String getRefresh() {
        return refresh;
    }

    /**
     * @param aRefresh the refresh to set
     */
    public static void setRefresh(String aRefresh) {
        refresh = aRefresh;
    }

    /**
     * @return the alamat
     */
    public static String getAlamat() {
        return alamat;
    }

    /**
     * @param aAlamat the alamat to set
     */
    public static void setAlamat(String aAlamat) {
        alamat = aAlamat;
    }

    /**
     * @return the password
     */
    public static String getPassword() {
        return password;
    }

    /**
     * @param aPassword the password to set
     */
    public static void setPassword(String aPassword) {
        password = aPassword;
    }

    /**
     * @return the telepon
     */
    public static String getTelepon() {
        return telepon;
    }

    /**
     * @param aTelepon the telepon to set
     */
    public static void setTelepon(String aTelepon) {
        telepon = aTelepon;
    }
}
