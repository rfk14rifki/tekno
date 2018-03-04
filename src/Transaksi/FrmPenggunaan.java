/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Transaksi;

import Module.SessionLogin;
import Module.koneksi;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;
/**
 *
 * @author LENOVO
 */
public class FrmPenggunaan extends javax.swing.JDialog {
    SessionLogin sesi = new SessionLogin();
    private String idPelanggan;
    private boolean update=false;
    String uid = SessionLogin.getNama();
    /**
     * Creates new form FrmPenggunaan
     */
    public FrmPenggunaan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        cbo();
        this.setTitle("Penggunaan");
        ImageIcon ico = new ImageIcon("src/Image/listrik.png");
        setIconImage(ico.getImage());
        SimpleDateFormat df = new SimpleDateFormat("Y");
        Date dt = new Date();
        tahun.setText(df.format(dt));
    }
    
    private int cekdata(){
        int i = 0;
        if(tahun.getText().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "Tahun tidak boleh kosong");
        }else if(awal.getText().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "Meter Awal tidak boleh kosong");
        }else if(akhir.getText().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "Meter Akhir tidak boleh kosong");
        }else{
        i=1;
    }
        return i;
    }
    
    private void simpan(){
        String idPlg = idplg.getSelectedItem().toString();
        String aDi = idPlg.substring(idPlg.length()-8, idPlg.length());
        String pt = uid;
        String bl = bulan.getText();
        String th = tahun.getText();
        String awl = awal.getText();
        String akh = akhir.getText();
        String stat = "1";
        String sql ="insert into tbl_png values('"+aDi+"','"+bl+"','"+th+"','"+awl+"','"+akh+"','"+stat+"','"+pt+"')";
        int i = cekdata();
        if(i>0){
            int a = koneksi.Execute(sql);
            if(a>0){
                sesi.setRefresh("true");
                JOptionPane.showMessageDialog(rootPane, "Data berhasil disimpan.",
                        "Informasi",JOptionPane.INFORMATION_MESSAGE);
                sesi.setRefresh("false");
            }
        }else{
               JOptionPane.showMessageDialog(rootPane, "Data gagal disimpan.",
                        "Informasi",JOptionPane.WARNING_MESSAGE); 
        }   
    }
    
    private void cbo(){
        try{
            String sql="select idpel,nama from tbl_pel order by idpel asc";
            ResultSet rs=koneksi.ExecuteQuery(sql);
            while(rs.next()){
                idplg.addItem(rs.getString(1));
            }
        }catch(Exception e){
            System.out.println();
        }
    }
    
    private void updateData(){
        String pt = uid;
        String idPlg = idplg.getSelectedItem().toString();
        String aDi = idPlg.substring(idPlg.length()-8, idPlg.length());
        String bl = bulan.getText();
        String th = tahun.getText();
        String awl = awal.getText();
        String akh = akhir.getText();
        String sql="update tbl_png set bl='"+bl+"',petugas='"+pt+"',th='"+th+"',meterawal='"+awl+"',meterakhir='"+akh+"' where idpel='"+this.getIdPelanggan()+"'";
        try{
            int a=koneksi.Execute(sql);
            if(a>0){
                sesi.setRefresh("true");
                JOptionPane.showMessageDialog(rootPane, "Data berhasil diubah.","Infomasi"
                ,JOptionPane.INFORMATION_MESSAGE);
                sesi.setRefresh("false");
            }else{
                JOptionPane.showMessageDialog(rootPane, "Data gagal diubah.","Infomasi"
                ,JOptionPane.WARNING_MESSAGE);
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    private void getNama(){
        String idPLG=idplg.getSelectedItem().toString();
        String sql2="Select idpel,nama from tbl_pel where idpel='"+idPLG+"' ";
        try{
            ResultSet rs= koneksi.ExecuteQuery(sql2);
            while(rs.next()){
                nm.setText(rs.getString(2));
            }
        }catch(Exception e){
            System.out.println(e);
        }
        
    }
    
    private void blnthn(){
        try{
    String id = idplg.getSelectedItem().toString();
    String s = "select bl,th from tbl_png where idpel='"+id+"' order by idpel desc";
    ResultSet rs = koneksi.ExecuteQuery(s);
            if(rs.next()){
                String bln = (rs.getString(1));
                String thn = (rs.getString(2));
                int thnn = Integer.valueOf(thn);
                if(rs.getString(1).equals("Januari")){
                    bln = "Februari";
                    bulan.setText(bln);
                    tahun.setText(thn);
                }else if(rs.getString(1).equals("Februari")){
                    bln = "Maret"; 
                    bulan.setText(bln);
                    tahun.setText(thn);  
                }else if (rs.getString(1).equals("Maret")){
                    bln = "April";
                    bulan.setText(bln);
                    tahun.setText(thn);  
                }else if (rs.getString(1).equals("April")) {
                    bln = "Mei";
                    bulan.setText(bln);
                    tahun.setText(thn);  ;
                }else if (rs.getString(1).equals("Mei")){
                    bln = "Juni";
                    bulan.setText(bln);
                    tahun.setText(thn);  
                }else if(rs.getString(1).equals("Juni")){
                    bln = "Juli";
                    bulan.setText(bln);
                    tahun.setText(thn);     
                }else if (rs.getString(1).equals("Juli")){
                    bln = "Agustus";
                    bulan.setText(bln);
                    tahun.setText(thn);  
                }else if (rs.getString(1).equals("Agustus")) {
                    bln = "September";
                    bulan.setText(bln);
                    tahun.setText(thn);  
                }else if (rs.getString(1).equals("September")){
                    bln = "Oktober";
                    bulan.setText(bln);
                    tahun.setText(thn);  
                }else if(rs.getString(1).equals("Oktober")){
                    bln = "November";
                    bulan.setText(bln);
                    tahun.setText(thn);     
                }else if (rs.getString(1).equals("November")){
                    bln = "Desember";
                    bulan.setText(bln);
                    tahun.setText(thn);  
                }else if (rs.getString(1).equals("Desember")) {
                    bln = "Januari";
                    thnn = thnn+1;
                    String thhn = String.valueOf(thnn);
                    bulan.setText(bln);
                    tahun.setText(thn);  
                }
                else{
                    SimpleDateFormat df = new SimpleDateFormat("YYYY");
                    SimpleDateFormat dt = new SimpleDateFormat("M");
                    Date date = new Date();
                    String hasil = df.format(date);
                    String a = dt.format(date);
                    if(a.equals("1")){
                        String blnz = "Januari";
                        bulan.setText(blnz);
                        tahun.setText(hasil);
                    }else if(a.equals("2")){
                        String blnz = "Februari";
                       bulan.setText(blnz);
                        tahun.setText(hasil);
                    }else if (a.equals("3")){
                        String blnz = "Maret";
                        bulan.setText(blnz);
                        tahun.setText(hasil);
                    }else if (a.equals("4")){
                        String blnz = "April";
                        bulan.setText(blnz);
                        tahun.setText(hasil);
                    }else if (a.equals("5")){
                        String blnz = "Mei";
                        bulan.setText(blnz);
                        tahun.setText(hasil);
                    }else if(a.equals("6")){
                        String blnz = "Juni";
                        bulan.setText(blnz);
                        tahun.setText(hasil);
                    }else if (a.equals("7")){
                        String blnz = "Juli";
                        bulan.setText(blnz);
                        tahun.setText(hasil);
                    }else if (a.equals("8")){
                        String blnz = "Agustus";
                        bulan.setText(blnz);
                        tahun.setText(hasil);
                    }else if (a.equals("9")){
                        String blnz = "September";
                        bulan.setText(blnz);
                        tahun.setText(hasil);
                    }else if(a.equals("10")){
                        String blnz = "Oktober";
                        bulan.setText(blnz);
                        tahun.setText(hasil);
                    }else if (a.equals("11")){
                        String blnz = "November";
                        bulan.setText(blnz);
                        tahun.setText(hasil);
                    }else if (a.equals("12")){
                        String blnz = "Desember";
                        bulan.setText(blnz);
                        tahun.setText(hasil);
                    }else{
                        bulan.setText(a);
                        tahun.setText(hasil);
                    }
                }
            }else{
                    SimpleDateFormat df = new SimpleDateFormat("YYYY");
                    SimpleDateFormat dt = new SimpleDateFormat("M");
                    Date date = new Date();
                    String hasil = df.format(date);
                    String a = dt.format(date);
                    if(a.equals("1")){
                        String bln = "Januari";
                        bulan.setText(bln);
                        tahun.setText(hasil);
                    }else if(a.equals("2")){
                        String bln = "Februari";
                        bulan.setText(bln);
                        tahun.setText(hasil);
                    }else if (a.equals("3")){
                        String bln = "Maret";
                        bulan.setText(bln);
                        tahun.setText(hasil);
                    }else if (a.equals("4")){
                        String bln = "April";
                        bulan.setText(bln);
                        tahun.setText(hasil);
                    }else if (a.equals("5")){
                        String bln = "Mei";
                        bulan.setText(bln);
                        tahun.setText(hasil);
                    }else if(a.equals("6")){
                        String bln = "Juni";
                        bulan.setText(bln);
                        tahun.setText(hasil);
                    }else if (a.equals("7")){
                        String bln = "Juli";
                        bulan.setText(bln);
                        tahun.setText(hasil);
                    }else if (a.equals("8")){
                        String bln = "Agustus";
                        bulan.setText(bln);
                        tahun.setText(hasil);
                    }else if (a.equals("9")){
                        String bln = "September";
                        bulan.setText(bln);
                        tahun.setText(hasil);
                    }else if(a.equals("10")){
                        String bln = "Oktober";
                        bulan.setText(bln);
                        tahun.setText(hasil);
                    }else if (a.equals("11")){
                        String bln = "November";
                        bulan.setText(bln);
                        tahun.setText(hasil);
                    }else if (a.equals("12")){
                        String bln = "Desember";
                        bulan.setText(bln);
                        tahun.setText(hasil);
                    }else{
                        bulan.setText(a);
                        tahun.setText(hasil);
                    }
            }
        } catch (Exception e) {
        }
    }
    
     private void thnbln(){
        try{
    String id = idplg.getSelectedItem().toString();
    String s = "select bl,th from tbl_png where idpel='"+id+"' order by idpel desc";
    ResultSet rs = koneksi.ExecuteQuery(s);
            if(rs.next()){
                String bln = (rs.getString(1));
                String thn = (rs.getString(2));
                bulan.setText(bln);
                tahun.setText(thn);
                
            }
        } catch (Exception e) {
        }
    }
    
    private void pushData(){
        String sql = "select * from tbl_png where idpel='"+this.getIdPelanggan()+"'";
        try{
            ResultSet rs=koneksi.ExecuteQuery(sql);
            if(rs.next()){
                bulan.setText(rs.getString(2));
                tahun.setText(rs.getString(3));
                awal.setText(rs.getString(4));
                akhir.setText(rs.getString(5));
                idplg.setSelectedItem(rs.getString(1));
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        idplg = new javax.swing.JComboBox<String>();
        tahun = new javax.swing.JTextField();
        awal = new javax.swing.JTextField();
        akhir = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        nm = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        bulan = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        btnCancel = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(0, 153, 153));

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("*Form ini berfungsi untuk menambah penggunaan listrik");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Penggunaan Listrik ");

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/listrik.png"))); // NOI18N

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Close_Window_32px.png"))); // NOI18N
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4))
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel4))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Input Penggunaan", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        idplg.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        idplg.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                idplgItemStateChanged(evt);
            }
        });

        tahun.setEditable(false);
        tahun.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tahunKeyTyped(evt);
            }
        });

        awal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                awalKeyTyped(evt);
            }
        });

        akhir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                akhirKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ID Pelanggan");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Bulan");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Meter Awal");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Meter Akhir");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Tahun");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Nama");

        bulan.setEditable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jLabel3)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(awal, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(akhir, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tahun, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(nm, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(idplg, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(bulan, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(74, 74, 74))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idplg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(bulan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tahun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(awal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(akhir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(0, 102, 102));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Action", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/16/back.png"))); // NOI18N
        btnCancel.setText("Kembali");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/16/save.png"))); // NOI18N
        btnSave.setText("Simpan");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/16/close.png"))); // NOI18N
        jButton1.setText("Batal");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSave)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancel)
                .addGap(118, 118, 118))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(jButton1)
                    .addComponent(btnCancel))
                .addGap(0, 7, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        if (this.isUpdate()==true){
            pushData();
            btnSave.setText("UBAH");
        }else{
           
        }
    }//GEN-LAST:event_formWindowOpened

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        if(this.isUpdate()==true){
            updateData();
        }else{
            simpan();
        }
        bulan.setVisible(false);
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        if(JOptionPane.showConfirmDialog(rootPane, "Apakah anda kembali ke List Penggunaan?","Informasi",JOptionPane.CANCEL_OPTION,
                JOptionPane.OK_OPTION)==JOptionPane.YES_OPTION){
            dispose();
        }
        ListPenggunaan a = new ListPenggunaan(new javax.swing.JFrame(),true);
        a.setLocationRelativeTo(this);
        a.setVisible(true);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void awalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_awalKeyTyped
        // TODO add your handling code here:
        char karakter = evt.getKeyChar();
        if(!(((karakter >= '0') && (karakter <= '9') || (karakter == KeyEvent.VK_BACK_SPACE) || (karakter == KeyEvent.VK_DELETE)))){
            getToolkit().beep();
            evt.consume();
            }
    }//GEN-LAST:event_awalKeyTyped

    private void akhirKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_akhirKeyTyped
        // TODO add your handling code here:
        char karakter = evt.getKeyChar();
        if(!(((karakter >= '0') && (karakter <= '9') || (karakter == KeyEvent.VK_BACK_SPACE) || (karakter == KeyEvent.VK_DELETE)))){
            getToolkit().beep();
            evt.consume();
            }
    }//GEN-LAST:event_akhirKeyTyped

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        // TODO add your handling code here:
        if(JOptionPane.showConfirmDialog(rootPane, "Anda akan keluar dari List Tagihan?","Informasi",JOptionPane.CANCEL_OPTION,
            JOptionPane.OK_OPTION)==JOptionPane.YES_OPTION){
        this.dispose();
        }
    }//GEN-LAST:event_jLabel9MouseClicked

    private void idplgItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_idplgItemStateChanged
        // TODO add your handling code here:
        if(isUpdate()==true){
            thnbln();
        }else{
            blnthn();
        }
        getNama();
    }//GEN-LAST:event_idplgItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        awal.setText("");
        akhir.setText("");
        idplg.setSelectedItem("-");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tahunKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tahunKeyTyped
        // TODO add your handling code here:
        char karakter = evt.getKeyChar();
        if(!(((karakter >= '0') && (karakter <= '9') || (karakter == com.sun.glass.events.KeyEvent.VK_BACKSPACE) || (karakter == com.sun.glass.events.KeyEvent.VK_DELETE)))){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_tahunKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmPenggunaan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPenggunaan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPenggunaan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPenggunaan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmPenggunaan dialog = new FrmPenggunaan(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    
    public String getIdPelanggan() {
        return idPelanggan;
    }
    /**
     * @param idPelanggan the idPelanggan to set
     */
    public void setIdPelanggan(String idPelanggan) {
        this.idPelanggan = idPelanggan;
    }

    /**
     * @return the update
     */
    public boolean isUpdate() {
        return update;
    }

    /**
     * @param update the update to set
     */
    public void setUpdate(boolean update) {
        this.update = update;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField akhir;
    private javax.swing.JTextField awal;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSave;
    private javax.swing.JTextField bulan;
    private javax.swing.JComboBox<String> idplg;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTextField nm;
    private javax.swing.JTextField tahun;
    // End of variables declaration//GEN-END:variables
}
