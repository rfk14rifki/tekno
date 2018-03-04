/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File;

import Pengaturan.ListBiayaAdmin;
import Master.*;
import Transaksi.*;
import Bantuan.*;
import Module.SessionLogin;
import Module.koneksi;
import Pengaturan.Profile;
import Report.*;
import java.awt.Cursor;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author LENOVO
 */
public class MainMenu extends javax.swing.JFrame {
SessionLogin sesi = new SessionLogin();
    /**
     * Creates new form MainMenu
     */
    public MainMenu() {
        initComponents();
        this.setTitle("Aplikasi Pembayaran Listrik Pasca Bayar V.0.0.1");
        ImageIcon ico = new ImageIcon("src/Image/listrik.png");
        setIconImage(ico.getImage());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        Timer open = new Timer(0, new ActionListener() {    
            @Override
            public void actionPerformed(ActionEvent e) {
                statusbar();
            }
        });
        open.start();
    }
    
    private void statusbar(){
        SimpleDateFormat df = new SimpleDateFormat("EEEE, YYYY-MM-DD HH:mm:ss");
        Date dt = new Date();
        tgl.setText(df.format(dt));
        if(sesi.getNama()==null){
            nm.setText("-");
        }else{
            nm.setText(sesi.getNama());
        }
    }
    private void cetakplg(){
        {setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Connection cn = koneksi.connect();
        try {
        Statement st = cn.createStatement();
        Map<String, Object> prs = new HashMap<String, Object>();
        JasperReport JRpt =
        JasperCompileManager.compileReport("src/Report/DftPelanggan.jrxml");
        JasperPrint JPrint = JasperFillManager.fillReport(JRpt, prs, cn);
        JasperViewer view = new JasperViewer(JPrint, false);
        javax.swing.JDialog dialog = new javax.swing.JDialog(new javax.swing.JFrame(),true);//the owner
            dialog.setContentPane(view.getContentPane());
            dialog.setSize(1280,720);
            dialog.setTitle("Laporan Pelanggan");
            dialog.setLocationRelativeTo(dialog);
            dialog.setVisible(true);
        } catch (Exception rptexcpt) {
        System.out.println("Report Can't view because : " + rptexcpt);
        }
        setCursor(Cursor.getDefaultCursor());
        }
    }
    
    private void cetaktarif(){
        {setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Connection cn = koneksi.connect();
        try {
        Statement st = cn.createStatement();
        Map<String, Object> prs = new HashMap<String, Object>();
        JasperReport JRpt =
        JasperCompileManager.compileReport("src/Report/RptTarif.jrxml");
        JasperPrint JPrint = JasperFillManager.fillReport(JRpt, prs, cn);
        JasperViewer view = new JasperViewer(JPrint, false);
        javax.swing.JDialog dialog = new javax.swing.JDialog(new javax.swing.JFrame(),true);//the owner
            dialog.setContentPane(view.getContentPane());
            dialog.setSize(1280,720);
            dialog.setTitle("Laporan Tarif");
            dialog.setLocationRelativeTo(dialog);
            dialog.setVisible(true);
        } catch (Exception rptexcpt) {
        System.out.println("Report Can't view because : " + rptexcpt);
        }
        setCursor(Cursor.getDefaultCursor());
        }
    }
    
    private void cetakpenggunaan(){
        {setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Connection cn = koneksi.connect();
        try {
        Statement st = cn.createStatement();
        Map<String, Object> prs = new HashMap<String, Object>();
        JasperReport JRpt =
        JasperCompileManager.compileReport("src/Report/RptPenggunaan.jrxml");
        JasperPrint JPrint = JasperFillManager.fillReport(JRpt, prs, cn);
        JasperViewer view = new JasperViewer(JPrint, false);
        javax.swing.JDialog dialog = new javax.swing.JDialog(new javax.swing.JFrame(),true);//the owner
            dialog.setContentPane(view.getContentPane());
            dialog.setSize(1280,720);
            dialog.setTitle("Laporan Penggunaan Listrik");
            dialog.setLocationRelativeTo(dialog);
            dialog.setVisible(true);
        } catch (Exception rptexcpt) {
        System.out.println("Report Can't view because : " + rptexcpt);
        }
        setCursor(Cursor.getDefaultCursor());
        }
    }
    
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
         if (JOptionPane.showConfirmDialog(rootPane, "Anda akan keluar dari System?","Informasi",
                JOptionPane.CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE)==JOptionPane.OK_OPTION) {
//            JOptionPane.showMessageDialog(rootPane, "Berhasil Logout.");
            System.exit(0);
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

        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        btnPelanggan = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btnPg = new javax.swing.JButton();
        btnTgh = new javax.swing.JButton();
        btnPembayaran = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        btnPrint = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        tgl = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        nm = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        lock = new javax.swing.JMenuItem();
        keluar = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        plg = new javax.swing.JMenuItem();
        png = new javax.swing.JMenuItem();
        trf = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        contentMenuItem2 = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem4 = new javax.swing.JMenuItem();
        helpMenu1 = new javax.swing.JMenu();
        contentMenuItem1 = new javax.swing.JMenuItem();
        aboutMenuItem1 = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        aboutMenuItem5 = new javax.swing.JMenuItem();
        aboutMenuItem6 = new javax.swing.JMenuItem();
        aboutMenuItem7 = new javax.swing.JMenuItem();
        helpMenu2 = new javax.swing.JMenu();
        aboutMenuItem2 = new javax.swing.JMenuItem();
        helpMenu3 = new javax.swing.JMenu();
        contentMenuItem3 = new javax.swing.JMenuItem();
        aboutMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/24/lock.png"))); // NOI18N
        jButton1.setToolTipText("Kunci Aplikasi");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        btnPelanggan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/24/team.png"))); // NOI18N
        btnPelanggan.setToolTipText("Pelanggan");
        btnPelanggan.setFocusable(false);
        btnPelanggan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPelanggan.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPelangganActionPerformed(evt);
            }
        });
        jToolBar1.add(btnPelanggan);
        jToolBar1.add(jSeparator1);

        btnPg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/credit-card.png"))); // NOI18N
        btnPg.setToolTipText("Penggunaan");
        btnPg.setFocusable(false);
        btnPg.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPg.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPgActionPerformed(evt);
            }
        });
        jToolBar1.add(btnPg);

        btnTgh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/24/if_Cashier-2_379354.png"))); // NOI18N
        btnTgh.setToolTipText("Tagihan");
        btnTgh.setFocusable(false);
        btnTgh.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTgh.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnTgh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTghActionPerformed(evt);
            }
        });
        jToolBar1.add(btnTgh);

        btnPembayaran.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/24/point-of-service.png"))); // NOI18N
        btnPembayaran.setToolTipText("Pembayaran");
        btnPembayaran.setFocusable(false);
        btnPembayaran.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPembayaran.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPembayaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPembayaranActionPerformed(evt);
            }
        });
        jToolBar1.add(btnPembayaran);
        jToolBar1.add(jSeparator2);

        btnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/24/printer.png"))); // NOI18N
        btnPrint.setToolTipText("Print Periode Pembayaran");
        btnPrint.setFocusable(false);
        btnPrint.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPrint.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });
        jToolBar1.add(btnPrint);

        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/24/exit.png"))); // NOI18N
        btnExit.setToolTipText("Keluar");
        btnExit.setFocusable(false);
        btnExit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnExit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        jToolBar1.add(btnExit);

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Untitled2.png"))); // NOI18N

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        jPanel3.setBackground(new java.awt.Color(0, 102, 102));

        tgl.setForeground(new java.awt.Color(255, 255, 255));
        tgl.setText("Tanggal");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tgl)
                .addContainerGap(118, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tgl)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(0, 102, 102));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Nama :");

        nm.setForeground(new java.awt.Color(255, 255, 255));
        nm.setText("Muhamad Rifki");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(36, 36, 36)
                .addComponent(nm)
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nm))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        fileMenu.setMnemonic('f');
        fileMenu.setText("File");

        lock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/16/locked.png"))); // NOI18N
        lock.setMnemonic('o');
        lock.setText("Lock");
        lock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lockActionPerformed(evt);
            }
        });
        fileMenu.add(lock);

        keluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/16/logout.png"))); // NOI18N
        keluar.setMnemonic('s');
        keluar.setText("Keluar");
        keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keluarActionPerformed(evt);
            }
        });
        fileMenu.add(keluar);
        fileMenu.add(jSeparator3);

        exitMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/16/close.png"))); // NOI18N
        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        editMenu.setMnemonic('e');
        editMenu.setText("Master");

        plg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/16/team.png"))); // NOI18N
        plg.setMnemonic('t');
        plg.setText("Pelanggan");
        plg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plgActionPerformed(evt);
            }
        });
        editMenu.add(plg);

        png.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/16/petugas.png"))); // NOI18N
        png.setMnemonic('y');
        png.setText("Pengguna");
        png.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pngActionPerformed(evt);
            }
        });
        editMenu.add(png);

        trf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/16/interface.png"))); // NOI18N
        trf.setMnemonic('p');
        trf.setText("Tarif");
        trf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trfActionPerformed(evt);
            }
        });
        editMenu.add(trf);
        editMenu.add(jSeparator5);

        contentMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/16/cashier-1.png"))); // NOI18N
        contentMenuItem2.setMnemonic('c');
        contentMenuItem2.setText("Biaya Admin");
        contentMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contentMenuItem2ActionPerformed(evt);
            }
        });
        editMenu.add(contentMenuItem2);

        menuBar.add(editMenu);

        helpMenu.setMnemonic('h');
        helpMenu.setText("Transaksi");

        contentMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/16/penggunaan.png"))); // NOI18N
        contentMenuItem.setMnemonic('c');
        contentMenuItem.setText("Penggunaan");
        contentMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contentMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(contentMenuItem);

        aboutMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/16/penagihan.png"))); // NOI18N
        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("Tagihan");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(aboutMenuItem);

        aboutMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/16/pembayaran.png"))); // NOI18N
        aboutMenuItem4.setMnemonic('a');
        aboutMenuItem4.setText("Pembayaran");
        aboutMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItem4ActionPerformed(evt);
            }
        });
        helpMenu.add(aboutMenuItem4);

        menuBar.add(helpMenu);

        helpMenu1.setMnemonic('h');
        helpMenu1.setText("Laporan");

        contentMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/16/file.png"))); // NOI18N
        contentMenuItem1.setMnemonic('c');
        contentMenuItem1.setText("Laporan Daftar Pelanggan");
        contentMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contentMenuItem1ActionPerformed(evt);
            }
        });
        helpMenu1.add(contentMenuItem1);

        aboutMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/16/file2.png"))); // NOI18N
        aboutMenuItem1.setMnemonic('a');
        aboutMenuItem1.setText("Laporan Daftar Tarif");
        aboutMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItem1ActionPerformed(evt);
            }
        });
        helpMenu1.add(aboutMenuItem1);
        helpMenu1.add(jSeparator4);

        aboutMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/16/file3.png"))); // NOI18N
        aboutMenuItem5.setMnemonic('a');
        aboutMenuItem5.setText("LaporanPenggunaan");
        aboutMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItem5ActionPerformed(evt);
            }
        });
        helpMenu1.add(aboutMenuItem5);

        aboutMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/16/file.png"))); // NOI18N
        aboutMenuItem6.setMnemonic('a');
        aboutMenuItem6.setText("Laporan Tagihan");
        aboutMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItem6ActionPerformed(evt);
            }
        });
        helpMenu1.add(aboutMenuItem6);

        aboutMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/16/file3.png"))); // NOI18N
        aboutMenuItem7.setMnemonic('a');
        aboutMenuItem7.setText("Laporan Pembayaran");
        aboutMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItem7ActionPerformed(evt);
            }
        });
        helpMenu1.add(aboutMenuItem7);

        menuBar.add(helpMenu1);

        helpMenu2.setMnemonic('h');
        helpMenu2.setText("Pengaturan");
        helpMenu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpMenu2ActionPerformed(evt);
            }
        });

        aboutMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/16/team.png"))); // NOI18N
        aboutMenuItem2.setMnemonic('a');
        aboutMenuItem2.setText("Profile");
        aboutMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItem2ActionPerformed(evt);
            }
        });
        helpMenu2.add(aboutMenuItem2);

        menuBar.add(helpMenu2);

        helpMenu3.setMnemonic('h');
        helpMenu3.setText("Bantuan");

        contentMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/16/what.png"))); // NOI18N
        contentMenuItem3.setMnemonic('c');
        contentMenuItem3.setText("Cara Penggunaan");
        contentMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contentMenuItem3ActionPerformed(evt);
            }
        });
        helpMenu3.add(contentMenuItem3);

        aboutMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/16/boy.png"))); // NOI18N
        aboutMenuItem3.setMnemonic('a');
        aboutMenuItem3.setText("Tentang");
        aboutMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItem3ActionPerformed(evt);
            }
        });
        helpMenu3.add(aboutMenuItem3);

        menuBar.add(helpMenu3);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        if(JOptionPane.showConfirmDialog(rootPane, "Apakah anda akan keluar dari aplikasi?","Informasi",JOptionPane.CANCEL_OPTION,
                JOptionPane.OK_OPTION)==JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        Login a = new Login(new javax.swing.JFrame(),true);
        a.setLocationRelativeTo(this);
        a.setVisible(true);
    }//GEN-LAST:event_formWindowOpened

    private void lockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lockActionPerformed
        // TODO add your handling code here:
        Lock a = new Lock(new javax.swing.JFrame(),true);
        a.setLocationRelativeTo(this);
        a.setVisible(true);
    }//GEN-LAST:event_lockActionPerformed

    private void keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keluarActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(rootPane, "Anda Berhasil Logout...");
        Login a = new Login(new javax.swing.JFrame(),true);
        a.setLocationRelativeTo(this);
        a.setVisible(true);
    }//GEN-LAST:event_keluarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Lock a = new Lock(new javax.swing.JFrame(),true);
        a.setLocationRelativeTo(this);
        a.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void trfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trfActionPerformed
        // TODO add your handling code here:
        ListTarif a = new ListTarif(new javax.swing.JFrame(),true);
        a.setLocationRelativeTo(this);
        a.setVisible(true);
    }//GEN-LAST:event_trfActionPerformed

    private void plgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plgActionPerformed
        // TODO add your handling code here:
        ListPelanggan a = new ListPelanggan(new javax.swing.JFrame(),true);
        a.setLocationRelativeTo(this);
        a.setVisible(true);
    }//GEN-LAST:event_plgActionPerformed

    private void pngActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pngActionPerformed
        // TODO add your handling code here:
        ListUser a = new ListUser(new javax.swing.JFrame(),true);
        a.setLocationRelativeTo(this);
        a.setVisible(true);
    }//GEN-LAST:event_pngActionPerformed

    private void contentMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contentMenuItemActionPerformed
        // TODO add your handling code here:
        ListPenggunaan a = new ListPenggunaan(new javax.swing.JFrame(),true);
        a.setLocationRelativeTo(this);
        a.setVisible(true);
    }//GEN-LAST:event_contentMenuItemActionPerformed

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        // TODO add your handling code here:
        ListTagihan a = new ListTagihan(new javax.swing.JFrame(),true);
        a.setLocationRelativeTo(this);
        a.setVisible(true);
    }//GEN-LAST:event_aboutMenuItemActionPerformed

    private void aboutMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItem4ActionPerformed
        // TODO add your handling code here:
        ListPembayaran a = new ListPembayaran(new javax.swing.JFrame(),true);
        a.setLocationRelativeTo(this);
        a.setVisible(true);
    }//GEN-LAST:event_aboutMenuItem4ActionPerformed

    private void contentMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contentMenuItem3ActionPerformed
        // TODO add your handling code here:
        Cara a = new Cara(new javax.swing.JFrame(),true);
        a.setLocationRelativeTo(this);
        a.setVisible(true);
    }//GEN-LAST:event_contentMenuItem3ActionPerformed

    private void aboutMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItem3ActionPerformed
        // TODO add your handling code here:
        About a = new About(new javax.swing.JFrame(),true);
        a.setLocationRelativeTo(this);
        a.setVisible(true);
    }//GEN-LAST:event_aboutMenuItem3ActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:
        if(JOptionPane.showConfirmDialog(rootPane, "Apakah anda akan keluar dari aplikasi?","Informasi",JOptionPane.CANCEL_OPTION,
                JOptionPane.OK_OPTION)==JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_btnExitActionPerformed

    private void contentMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contentMenuItem2ActionPerformed
        // TODO add your handling code here:
        ListBiayaAdmin a = new ListBiayaAdmin(new javax.swing.JFrame(),true);
        a.setLocationRelativeTo(this);
        a.setVisible(true);
    }//GEN-LAST:event_contentMenuItem2ActionPerformed

    private void helpMenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpMenu2ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_helpMenu2ActionPerformed

    private void aboutMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItem2ActionPerformed
        // TODO add your handling code here:
        Profile a = new Profile(new javax.swing.JFrame(),true);
        a.setLocationRelativeTo(this);
        a.setVisible(true);
    }//GEN-LAST:event_aboutMenuItem2ActionPerformed

    private void contentMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contentMenuItem1ActionPerformed
        // TODO add your handling code here:
        cetakplg();
    }//GEN-LAST:event_contentMenuItem1ActionPerformed

    private void aboutMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItem1ActionPerformed
        // TODO add your handling code here:
        cetaktarif();
    }//GEN-LAST:event_aboutMenuItem1ActionPerformed

    private void aboutMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItem5ActionPerformed
        // TODO add your handling code here:
        cetakpenggunaan();
    }//GEN-LAST:event_aboutMenuItem5ActionPerformed

    private void aboutMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItem7ActionPerformed
        // TODO add your handling code here:
        Pemb a = new Pemb(new javax.swing.JFrame(),true);
        a.setLocationRelativeTo(this);
        a.setVisible(true);
    }//GEN-LAST:event_aboutMenuItem7ActionPerformed

    private void aboutMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItem6ActionPerformed
        // TODO add your handling code here:
        Tagihan a = new Tagihan(new javax.swing.JFrame(),true);
        a.setLocationRelativeTo(this);
        a.setVisible(true);
    }//GEN-LAST:event_aboutMenuItem6ActionPerformed

    private void btnPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPelangganActionPerformed
        // TODO add your handling code here:
        ListPelanggan a = new ListPelanggan(new javax.swing.JFrame(),true);
        a.setLocationRelativeTo(this);
        a.setVisible(true);
    }//GEN-LAST:event_btnPelangganActionPerformed

    private void btnPgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPgActionPerformed
        // TODO add your handling code here:
        ListPenggunaan a = new ListPenggunaan(new javax.swing.JFrame(),true);
        a.setLocationRelativeTo(this);
        a.setVisible(true);
    }//GEN-LAST:event_btnPgActionPerformed

    private void btnTghActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTghActionPerformed
        // TODO add your handling code here:
        ListTagihan a = new ListTagihan(new javax.swing.JFrame(),true);
        a.setLocationRelativeTo(this);
        a.setVisible(true);
    }//GEN-LAST:event_btnTghActionPerformed

    private void btnPembayaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPembayaranActionPerformed
        // TODO add your handling code here:
        ListPembayaran a = new ListPembayaran(new javax.swing.JFrame(),true);
        a.setLocationRelativeTo(this);
        a.setVisible(true);
    }//GEN-LAST:event_btnPembayaranActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        // TODO add your handling code here:
        Pemb a = new Pemb(new javax.swing.JFrame(),true);
        a.setLocationRelativeTo(this);
        a.setVisible(true);
    }//GEN-LAST:event_btnPrintActionPerformed

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
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem aboutMenuItem1;
    private javax.swing.JMenuItem aboutMenuItem2;
    private javax.swing.JMenuItem aboutMenuItem3;
    private javax.swing.JMenuItem aboutMenuItem4;
    private javax.swing.JMenuItem aboutMenuItem5;
    private javax.swing.JMenuItem aboutMenuItem6;
    private javax.swing.JMenuItem aboutMenuItem7;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnPelanggan;
    private javax.swing.JButton btnPembayaran;
    private javax.swing.JButton btnPg;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnTgh;
    private javax.swing.JMenuItem contentMenuItem;
    private javax.swing.JMenuItem contentMenuItem1;
    private javax.swing.JMenuItem contentMenuItem2;
    private javax.swing.JMenuItem contentMenuItem3;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenu helpMenu1;
    private javax.swing.JMenu helpMenu2;
    private javax.swing.JMenu helpMenu3;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JMenuItem keluar;
    private javax.swing.JMenuItem lock;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JLabel nm;
    private javax.swing.JMenuItem plg;
    private javax.swing.JMenuItem png;
    private javax.swing.JLabel tgl;
    private javax.swing.JMenuItem trf;
    // End of variables declaration//GEN-END:variables

}
