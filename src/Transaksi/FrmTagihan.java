/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Transaksi;

import Module.SessionLogin;
import Module.koneksi;
import com.sun.glass.events.KeyEvent;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author LENOVO
 */
public class FrmTagihan extends javax.swing.JDialog {
    SessionLogin sesi = new SessionLogin();
    private String idPelanggan;
    String id = SessionLogin.getNama();
    private boolean update=false;
    /**
     * Creates new form FrmTagihan
     */
    public FrmTagihan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setTitle(".:Pelanggan:.");
        ImageIcon ico = new ImageIcon("src/Image/listrik.png");
        setIconImage(ico.getImage());
    }   
    
    private int cekdata(){
        int i = 0;
        if(tahun.getText().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "Tahun tidak boleh kosong");
        }else if(bulan.getText().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "Meter Awal tidak boleh kosong");
        }else if(meter.getText().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "Meter Akhir tidak boleh kosong");
        }else{
        i=1;
    }
        return i;
    }
    private void simpan(){
        String idPlg = pelanggan.getSelectedItem().toString();
        String aDi = idPlg.substring(idPlg.length()-8, idPlg.length());
        String pt= id;
        String bl = bulan.getText();
        String th = tahun.getText();
        String awl = meter.getText();
        String stat = sts.getText();
        String tg = idtag.getText();
        String total = tagihan.getText();
        String st = "0";
        String sql ="insert into tbl_tag values('"+aDi+"','"+tg+"','"+bl+"','"+th+"','"+awl+"','"+total+"','"+stat+"','1')";
        String sql1="update tbl_png set status='"+st+"' where idpel='"+aDi+"'";
        int i = cekdata();
        if(i>0){
            int a = koneksi.Execute(sql);
            int a1 = koneksi.Execute(sql1);
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
    
    private void update(){
        String idPlg = pelanggan.getSelectedItem().toString();
        String aDi = idPlg.substring(idPlg.length()-8, idPlg.length());
        String pt= id;
        String bl = bulan.getText();
        String th = tahun.getText();
        String awl = meter.getText();
        String stat = sts.getText();
        String total = tagihan.getText();
        String tg = idtag.getText();
        String st = "0";
        String sql="update tbl_tag set petugas='"+pt+"',bulan='"+bl+"',tahun='"+th+"',jmlhmeter='"+awl+"',tottag="
                + "'"+total+"',status='"+stat+"' where idtag='"+tg+"'";
        String sql1="update tbl_png set status='"+st+"' where idpel='"+aDi+"'";
        int i = cekdata();
        if(i>0){
            int a = koneksi.Execute(sql);
            int a1 = koneksi.Execute(sql1);
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
            ResultSet rs=koneksi.ExecuteQuery("select a.idpel,b.nama from tbl_png a inner join tbl_pel b on a.idpel= b.idpel where status=1");
            while(rs.next()){
                pelanggan.addItem(rs.getString(1));
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    private void cbo2(){
        try{
            ResultSet rs=koneksi.ExecuteQuery("select a.idpel,b.nama from tbl_tag a inner join tbl_pel b on a.idpel= b.idpel");
            while(rs.next()){
                pelanggan.addItem(rs.getString(1));
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    private void getTagihan(){
        int ttl = Integer.valueOf(meter.getText())*Integer.valueOf(tarif.getText());
        tagihan.setText(String.valueOf(ttl));
    }
    
    private void getJumlah(){
        String idPlg=pelanggan.getSelectedItem().toString();
        String aDi = idPlg.substring(idPlg.length()-8, idPlg.length());
        String sql="Select meterawal,meterakhir,meterakhir-meterawal as jumlah from tbl_png WHERE idpel='"+aDi+"'";
        try{
            ResultSet rs = koneksi.ExecuteQuery(sql);
            while(rs.next()){
                meter.setText(rs.getString(3));
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
     private void getNama(){
        String idPlg=pelanggan.getSelectedItem().toString();
        String aDi = idPlg.substring(idPlg.length()-8, idPlg.length());
        String sql="Select nama from tbl_pel WHERE idpel='"+aDi+"'";
        try{
            ResultSet rs = koneksi.ExecuteQuery(sql);
            while(rs.next()){
                txtNama.setText(rs.getString(1));
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
     private void getID(){
        String sql="select substring(idtag,4,9)as id from tbl_tag order by idtag desc limit 0,1";
        ResultSet rs=koneksi.ExecuteQuery(sql);
        try{
            if(rs.first()){
                String str ="00000";
                String id=rs.getString("id");
                int no=Integer.valueOf(id)+1;
                String str_no=String.valueOf(no);
                String hasil ="TAG"+str.substring(0,5-str_no.length())+Integer.toString(no);
                idtag.setText(hasil);
            }else{
                String idPelanggan="TAG"+"00001";
                idtag.setText(idPelanggan);
                
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e);
            System.out.println(e);
        }
    }
    
    private void getTarif(){
        String idPlg=pelanggan.getSelectedItem().toString();
        String aDi = idPlg.substring(idPlg.length()-8, idPlg.length());
        String sql2="Select idpel,tarifperkwh from tbl_pel inner join tbl_trf on tbl_pel.kdtarif = tbl_trf.kdtarif where idpel='"+aDi+"' ";
        try{
            ResultSet rs= koneksi.ExecuteQuery(sql2);
            while(rs.next()){
                tarif.setText(rs.getString(2));
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    private void getBulan(){
        String idPlg=pelanggan.getSelectedItem().toString();
        String aDi = idPlg.substring(idPlg.length()-8, idPlg.length());
        try{
            ResultSet rs=koneksi.ExecuteQuery("SELECT bl,th from tbl_png WHERE idpel='"+aDi+"'");
            while(rs.next()){
                bulan.setText(rs.getString(1));
                tahun.setText(rs.getString(2));
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        pelanggan = new javax.swing.JComboBox<String>();
        tahun = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        bulan = new javax.swing.JTextField();
        tagihan = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        sts = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        idtag = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        meter = new javax.swing.JTextField();
        tarif = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        batal = new javax.swing.JButton();
        simpan = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Close_Window_32px.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/point-of-service.png"))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Tambah Tagihan");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("*Form ini berfungsi untuk menambah tagihan pelanggan");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Pelanggan");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel9))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(43, 43, 43))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9)))
                        .addContainerGap())))
        );

        jPanel3.setBackground(new java.awt.Color(0, 102, 102));

        jPanel4.setBackground(new java.awt.Color(0, 102, 102));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        pelanggan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        pelanggan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                pelangganItemStateChanged(evt);
            }
        });

        tahun.setEditable(false);
        tahun.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tahunKeyTyped(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("ID Pelanggan");

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Bulan");

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Status");

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Tahun");

        bulan.setEditable(false);

        tagihan.setEditable(false);

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Total Tagihan");

        sts.setForeground(new java.awt.Color(255, 255, 255));
        sts.setText("Belum Lunas");

        txtNama.setEditable(false);

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Nama");

        idtag.setEditable(false);

        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("ID Tagihan");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel5)
                    .addComponent(jLabel11)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(idtag, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tahun, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bulan, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tagihan, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sts))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idtag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(pelanggan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel5))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bulan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tahun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tagihan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sts)
                    .addComponent(jLabel7))
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(0, 102, 102));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        meter.setEditable(false);
        meter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                meterKeyTyped(evt);
            }
        });

        tarif.setEditable(false);

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Jumlah Meter");

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Tarif");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(meter, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                    .addComponent(tarif))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(meter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tarif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(0, 102, 102));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Action", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        batal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/16/back.png"))); // NOI18N
        batal.setText("Kembali");
        batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batalActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(simpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(batal)
                .addContainerGap(57, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(simpan)
                    .addComponent(batal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        if(JOptionPane.showConfirmDialog(rootPane, "Anda akan keluar dari List Tagihan?","Informasi",JOptionPane.CANCEL_OPTION,
            JOptionPane.OK_OPTION)==JOptionPane.YES_OPTION){
        System.exit(0);
        }
    }//GEN-LAST:event_jLabel3MouseClicked

    private void pelangganItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_pelangganItemStateChanged
        // TODO add your handling code here:
        if(pelanggan.getSelectedItem().equals("-")){
            txtNama.setText("");
            bulan.setText("");
            tahun.setText("");
            tarif.setText("");
            meter.setText("");
            tagihan.setText("");
        }else{
            getJumlah();
        getBulan();
        getTarif();
        getTagihan();
        getNama();
        }
    }//GEN-LAST:event_pelangganItemStateChanged

    private void batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalActionPerformed
        // TODO add your handling code here:
       if(JOptionPane.showConfirmDialog(rootPane, "Apakah anda kembali ke List Tagihan?","Informasi",JOptionPane.CANCEL_OPTION,
                JOptionPane.OK_OPTION)==JOptionPane.YES_OPTION){
            dispose();
        }
                ListTagihan a = new ListTagihan(new javax.swing.JFrame(),true);
                a.setLocationRelativeTo(this);
                a.setVisible(true);
    }//GEN-LAST:event_batalActionPerformed

    private void simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanActionPerformed
        // TODO add your handling code here:
        if(this.isUpdate()==true){
            update();
        }else{
            simpan();
        }
    }//GEN-LAST:event_simpanActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        if(this.isUpdate()==true){
            cbo2();
            simpan.setText("Ubah");
        }else{
            cbo();
             getID();
        }
    }//GEN-LAST:event_formWindowOpened

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
            txtNama.setText("");
            bulan.setText("");
            tahun.setText("");
            tarif.setText("");
            meter.setText("");
            tagihan.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tahunKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tahunKeyTyped
        // TODO add your handling code here:
        char karakter = evt.getKeyChar();
        if(!(((karakter >= '0') && (karakter <= '9') || (karakter == KeyEvent.VK_BACKSPACE) || (karakter == KeyEvent.VK_DELETE)))){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_tahunKeyTyped

    private void meterKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_meterKeyTyped
        // TODO add your handling code here:
        char karakter = evt.getKeyChar();
        if(!(((karakter >= '0') && (karakter <= '9') || (karakter == KeyEvent.VK_BACKSPACE) || (karakter == KeyEvent.VK_DELETE)))){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_meterKeyTyped

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
            java.util.logging.Logger.getLogger(FrmTagihan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmTagihan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmTagihan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmTagihan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmTagihan dialog = new FrmTagihan(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextField bulan;
    private javax.swing.JTextField idtag;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JTextField meter;
    private javax.swing.JComboBox<String> pelanggan;
    private javax.swing.JButton simpan;
    private javax.swing.JLabel sts;
    private javax.swing.JTextField tagihan;
    private javax.swing.JTextField tahun;
    private javax.swing.JTextField tarif;
    private javax.swing.JTextField txtNama;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the idPelanggan
     */
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
}
