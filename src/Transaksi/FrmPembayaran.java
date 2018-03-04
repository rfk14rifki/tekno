/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Transaksi;

import Module.SessionLogin;
import Module.koneksi;
import Report.*;
import Master.ListTarif;
import com.sun.glass.events.KeyEvent;
import java.awt.Cursor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author LENOVO
 */
public class FrmPembayaran extends javax.swing.JDialog {
    String id = SessionLogin.getNama();
    SessionLogin sesi = new SessionLogin();
    /**
     * Creates new form FrmPembayaran
     */
    public FrmPembayaran(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setTitle(".:Transaksi Pembayaran:.");
        ImageIcon ico = new ImageIcon("src/Image/listrik.png");
        setIconImage(ico.getImage());
        cbo();
        tglTransaksi();
        cetak.setVisible(false);
    }
    
    private void getBiayaadmin(){
     try{
         String query="Select * FROM ref_biaya";
         ResultSet rs = koneksi.ExecuteQuery(query);
         while(rs.next()){
             biayaadmin.setText(rs.getString(2));
         }
     }catch(Exception ex){
     }
    }
    
     private void tglTransaksi(){
        SimpleDateFormat df = new SimpleDateFormat("EEEE, dd MMMM YYYY");
        Date date = new Date();
        String hasil = df.format(date);
        txtTanggal.setText(hasil);
    }
    private int cekData(){
        int i = 0;
        if(bulan.getSelectedItem().equals("-")){
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih bulan.");
        }else if(total.getText().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "Total tidak boleh kosong.");
        }else if(biayaadmin.getText().equals("0")){
            JOptionPane.showMessageDialog(rootPane, "Biaya admin tidak boleh 0.");
        }else if(pelanggan.getSelectedItem().equals("-")){
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih pelanggan.");
        }
        else{
            i=1;
        }
        return i;
    }
    
    private void genIdTransaksi(){
        SimpleDateFormat df1 = new SimpleDateFormat("YYYY-MM-dd");
        SimpleDateFormat df2 = new SimpleDateFormat("ddMMyy");
        Date date = new Date();
//        String idPetugas = sesi.getIdPetugas();
        String query = "select substring(idbayar,1,4) as no from tbl_pem where tgl='"+df1.format(date)+"' order by idbayar desc limit 0,1";
        try {
            ResultSet rs1 = koneksi.ExecuteQuery(query);
            if(rs1.next()){
                String pattern = "0000";
                String no = rs1.getString(1);
                int Ino = Integer.valueOf(no)+1;
                String hasil = String.valueOf(Ino);
                txtid.setText(pattern.substring(0,4-hasil.length())+hasil+"-"+df2.format(date));
            }else{
                txtid.setText("0001-"+df2.format(date));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        
    }
    
    private int cekdata(){
        int i = 0;
        if(biayaadmin.getText().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "Biaya admin tidak boleh kosong!");
        }else if(total.getText().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "Total Pembayaran tidak boleh kosong!");
        }else{
            i=1;
        }
        return i;
    }
    
    private void cbo(){
        try{
            ResultSet rs=koneksi.ExecuteQuery("select a.idpel,b.nama from tbl_tag a inner join tbl_pel b on a.idpel= b.idpel WHERE status='Belum Lunas'");
            while(rs.next()){
                pelanggan.addItem(rs.getString(2)+" - "+rs.getString(1));
            }
        }catch(Exception e){
        }
    }
    
    private void simpan(){
        int i  = cekdata();
        if(i>0){
            SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd");
            Date date = new Date();
            String idpm = txtid.getText();
            String idPlg = pelanggan.getSelectedItem().toString();
            String aDi = idPlg.substring(idPlg.length()-8, idPlg.length());
            String ptg = id;
            String bln = bulan.getSelectedItem().toString();
            String admin = biayaadmin.getText();
            String ttl = total.getText();
            String tanggal = df.format(date);
            String lunas = "Sudah Lunas";
            String SQL = "insert into tbl_pem(idbayar,idpel,petugas,"
                    + "tgl,blbayar,biayaadmin,total) values ('"+idpm+"','"+aDi+"','"+ptg+"','"+tanggal+"','"+bln+"','"+admin+"','"+ttl+"')";
            String SQL1 = "update tbl_tag set status='"+lunas+"' WHERE idpel='"+aDi+"'";
            int a = koneksi.Execute(SQL);
            int b = koneksi.Execute(SQL1);
            if(a>0){
                sesi.setRefresh("true");
                JOptionPane.showMessageDialog(rootPane, "Data Berhasil Disimpan","Informasi",JOptionPane.INFORMATION_MESSAGE);
                sesi.setRefresh("false");
                cetak.setVisible(true);
            }else{
                 JOptionPane.showMessageDialog(rootPane, "Ada kesalahan\nDalam menyimpan data.","Warning",JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    private void cetak(){
        {setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Connection cn = koneksi.connect();
        try {
        Statement st = cn.createStatement();
        Map<String, Object> prs = new HashMap<String, Object>();
        prs.put("idbayar", txtid.getText());
        JasperReport JRpt =
        JasperCompileManager.compileReport("src/Report/FKPembayaran.jrxml");
        JasperPrint JPrint = JasperFillManager.fillReport(JRpt, prs, cn);
        JasperViewer view = new JasperViewer(JPrint, false);
        javax.swing.JDialog dialog = new javax.swing.JDialog(new javax.swing.JFrame(),true);//the owner
            dialog.setContentPane(view.getContentPane());
            dialog.setSize(650,500);
            dialog.setTitle("Laporan Transaksi");
            dialog.setLocationRelativeTo(dialog);
            dialog.setVisible(true);
        } catch (Exception rptexcpt) {
        System.out.println("Report Can't view because : " + rptexcpt);
        }
        setCursor(Cursor.getDefaultCursor());
        }
    }
    
    private void getJumlah(){
        String id=pelanggan.getSelectedItem().toString();
        String aId = id.substring(id.length()-8,id.length());
        System.out.print(aId);
        String sql="Select jmlmeter from tbl_tag WHERE idpel='"+aId+"'";
        try{
            ResultSet rs = koneksi.ExecuteQuery(sql);
            while(rs.next()){
                meter.setText(rs.getString(1));
            }
        }catch(Exception e){
        }
    }
    
    private void getTarif(){
        String id=pelanggan.getSelectedItem().toString();
        String aId = id.substring(id.length()-8,id.length());
        System.out.print(aId);
        String sql2="Select idpel,tarifperkwh from "
                + "tbl_pel inner join tbl_trf on tbl_pel.kdtarif = tbl_trf.kdtarif where idpel='"+aId+"' ";
        try{
            ResultSet rs= koneksi.ExecuteQuery(sql2);
            while(rs.next()){
                tarif.setText(rs.getString(2));
            }
        }catch(Exception e){
        }
    }
    
    private void tambahBiayaadmin(){
        int ttl = (Integer.valueOf(meter.getText())*Integer.valueOf(tarif.getText())+Integer.valueOf(biayaadmin.getText()));
        total.setText(String.valueOf(ttl));
    }
    
    private void total(){
        if(total.getText().isEmpty()){
            total.setText("0");
        }else{
            tambahBiayaadmin();
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

        jTextField5 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        txtid = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        biayaadmin = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        bulan = new javax.swing.JComboBox<String>();
        total = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTanggal = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        pelanggan = new javax.swing.JComboBox<String>();
        jLabel11 = new javax.swing.JLabel();
        meter = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tarif = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        batal = new javax.swing.JButton();
        cetak = new javax.swing.JButton();
        simpan = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        jTextField5.setText("jTextField5");

        jScrollPane1.setViewportView(jEditorPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(0, 153, 153));

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("*Form ini berfungsi untuk melakukan transaksi pembayaran listrik");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Transaksi");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Pembayaran Listrik");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/point-of-service.png"))); // NOI18N

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Close_Window_32px.png"))); // NOI18N
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2))
                            .addComponent(jLabel9))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(0, 102, 102));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pembayaran", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        txtid.setEditable(false);

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("No Pembayaran");

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Tanggal Bayar");

        biayaadmin.setEditable(false);
        biayaadmin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                biayaadminKeyReleased(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Bulan Bayar");

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Biaya Admin");

        bulan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember" }));

        total.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                totalKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Total Pembayaran");

        txtTanggal.setForeground(new java.awt.Color(255, 255, 255));
        txtTanggal.setText("Senin, 21 September 2016");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel30)
                    .addComponent(jLabel29)
                    .addComponent(jLabel31)
                    .addComponent(jLabel32)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(biayaadmin, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bulan, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtTanggal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(txtTanggal))
                .addGap(12, 12, 12)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(bulan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(biayaadmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(0, 102, 102));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Pelanggan", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        pelanggan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Pilih Pelanggan -" }));
        pelanggan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                pelangganItemStateChanged(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Pelanggan :");

        meter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                meterKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Jumlah Meter");

        tarif.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tarifKeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Tarif");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(meter, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tarif, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(meter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tarif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(68, 68, 68))
        );

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Action", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        batal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/16/back.png"))); // NOI18N
        batal.setText("Kembali");
        batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batalActionPerformed(evt);
            }
        });

        cetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/16/printer.png"))); // NOI18N
        cetak.setText("Cetak");
        cetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cetakActionPerformed(evt);
            }
        });

        simpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/16/save.png"))); // NOI18N
        simpan.setText("Simpan");
        simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/16/close.png"))); // NOI18N
        jButton1.setText("Batal");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(simpan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(batal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cetak)
                .addGap(44, 44, 44))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(simpan)
                    .addComponent(cetak)
                    .addComponent(batal)
                    .addComponent(jButton1))
                .addGap(110, 110, 110))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanActionPerformed
        // TODO add your handling code here:
        simpan();
    }//GEN-LAST:event_simpanActionPerformed

    private void pelangganItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_pelangganItemStateChanged
        // TODO add your handling code here:
        if(pelanggan.getSelectedItem().equals("- Pilih Pelanggan -")){
            meter.setText("");
            tarif.setText("");
            total.setText("");
            biayaadmin.setText("");
        }else{
        getJumlah();
        getTarif();
        total();
        getBiayaadmin();
        }
//        tambahBiayaadmin();
    }//GEN-LAST:event_pelangganItemStateChanged

    private void totalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_totalKeyTyped
        // TODO add your handling code here:
        char karakter = evt.getKeyChar();
        if(!(((karakter >= '0') && (karakter <= '9') || (karakter == KeyEvent.VK_BACKSPACE) || (karakter == KeyEvent.VK_DELETE)))){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_totalKeyTyped

    private void biayaadminKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_biayaadminKeyReleased
        // TODO add your handling code here:
        
    }//GEN-LAST:event_biayaadminKeyReleased

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        genIdTransaksi();
    }//GEN-LAST:event_formWindowOpened

    private void cetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cetakActionPerformed
        // TODO add your handling code here:
        cetak();
    }//GEN-LAST:event_cetakActionPerformed

    private void meterKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_meterKeyTyped
        // TODO add your handling code here:
        char karakter = evt.getKeyChar();
        if(!(((karakter >= '0') && (karakter <= '9') || (karakter == KeyEvent.VK_BACKSPACE) || (karakter == KeyEvent.VK_DELETE)))){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_meterKeyTyped

    private void tarifKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tarifKeyTyped
        // TODO add your handling code here:
        char karakter = evt.getKeyChar();
        if(!(((karakter >= '0') && (karakter <= '9') || (karakter == KeyEvent.VK_BACKSPACE) || (karakter == KeyEvent.VK_DELETE)))){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_tarifKeyTyped

    private void batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalActionPerformed
        // TODO add your handling code here:
       if(JOptionPane.showConfirmDialog(rootPane, "Apakah anda kembali ke List Pembayaran?","Informasi",JOptionPane.CANCEL_OPTION,
                JOptionPane.OK_OPTION)==JOptionPane.YES_OPTION){
            dispose();
        }
        ListPembayaran a = new ListPembayaran(new javax.swing.JFrame(),true);
        a.setLocationRelativeTo(this);
        a.setVisible(true);
    }//GEN-LAST:event_batalActionPerformed

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
            meter.setText("");
            tarif.setText("");
            total.setText("");
            biayaadmin.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(FrmPembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmPembayaran dialog = new FrmPembayaran(new javax.swing.JFrame(), true);
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton batal;
    private javax.swing.JTextField biayaadmin;
    private javax.swing.JComboBox<String> bulan;
    private javax.swing.JButton cetak;
    private javax.swing.JButton jButton1;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField meter;
    private javax.swing.JComboBox<String> pelanggan;
    private javax.swing.JButton simpan;
    private javax.swing.JTextField tarif;
    private javax.swing.JTextField total;
    private javax.swing.JLabel txtTanggal;
    private javax.swing.JTextField txtid;
    // End of variables declaration//GEN-END:variables
}
