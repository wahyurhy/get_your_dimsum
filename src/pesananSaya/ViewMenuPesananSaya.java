/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pesananSaya;

import akunSaya.ViewMenuAkunSayaEdit;
import akunSaya.ViewSettings;
import database.koneksiDatabase;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import login.ViewLogin;
import menuUtama.ViewMenuUtama;

/**
 *
 * @author hp
 */
public class ViewMenuPesananSaya extends javax.swing.JFrame {

    String path = "";
    Image showImage;
    ImageIcon icon = new ImageIcon("src/img/logo.png");

    public String name, pass;

    private static ViewMenuUtama instance = null;

    private void ViewMenuUtama() {

    }

    public static ViewMenuUtama getViewMenuUtama() throws IOException, FileNotFoundException, SQLException {
        if (instance == null) {
            instance = new ViewMenuUtama();
        }
        return instance;
    }

    public void getUsername() throws IOException, FileNotFoundException, SQLException {
        name = ViewLogin.getViewLogin().name;

        Statement stat;

        stat = (Statement) koneksiDatabase.getKoneksi().createStatement();
        String sql = "SELECT * FROM informasi_akun WHERE username = '" + name + "'";
//        String sql = "SELECT * FROM informasi_akun";
        ResultSet res = stat.executeQuery(sql);

        while (res.next()) {
            String username = res.getString("username");

            usernameLB.setText(username);
        }
    }

    private DefaultTableModel model;
//    private InputDataProduk idp;
//    private String idProduk;

    public void getInfoProduk() throws IOException, FileNotFoundException, SQLException {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();

        try {
            String sql = "SELECT  `nama_produk`, `total_harga`,`no_resi`,`alamat`, `kuantitas`, `id_pesanan`,`status_pembayaran`,`status_pengiriman` FROM `informasi_pesanan` WHERE `username`='%s' ORDER BY `id_pesanan` DESC";
            sql = String.format(sql, usernameLB.getText());
            Statement stmt;

            stmt = koneksiDatabase.getKoneksi().createStatement();

            stmt.execute(sql);
            ResultSet rs = stmt.executeQuery(sql);

            // tampilkan hasil query
            String statusPengiriman = "";
            String statusPembayaran = "";
            while (rs.next()) {
                // status pesanan
                if (rs.getInt("status_pengiriman") == 0) {
                    statusPengiriman = "Belum Dikirim";
                } else {
                    statusPengiriman = "Sudah Dikirim";
                };
                if (rs.getInt("status_pembayaran") == 0) {
                    statusPembayaran = "Belum Dibayar";
                } else {
                    statusPembayaran = "Sudah Dibayar";
                }

                // string to arrays
                String arrayProduk[] = rs.getString("nama_produk").split(",");
                String arrayKuantitas[] = rs.getString("kuantitas").split(",");
                ArrayList<String> lis = new ArrayList<String>();
                Object[] lisTerkumpul = {};
                String gabungan[][] = {arrayProduk, arrayKuantitas};
                for (int i = 0; i < arrayKuantitas.length; i++) {
                    if (arrayKuantitas[i].equals("0")) {
                        continue;
                    }
                    String produkKuantitas = arrayProduk[i] + " (" + arrayKuantitas[i] + ")";
                    lis.add(produkKuantitas);
                    lisTerkumpul = lis.toArray();

                }
                // Arrays.toString(lisTerkumpul);
//            while (rs.next()) {
                String[] lisTerkumpulFinal = Arrays.copyOf(lisTerkumpul, lisTerkumpul.length, String[].class);
                Object[] obj = new Object[7];
                obj[0] = usernameLB.getText() + "-" + rs.getInt("id_pesanan");
                obj[1] = String.join(", ", lisTerkumpulFinal);
                obj[2] = "Rp" + rs.getInt("total_harga");
                obj[3] = rs.getString("alamat");
                obj[4] = statusPembayaran;
                obj[5] = statusPengiriman;
                obj[6] = rs.getString("no_resi");
                // model.addRow(new Object[] {rs.getInt("id_pesanan"),Arrays.toString(lisTerkumpul),statusPembayaran,statusPengiriman});
                model.addRow(obj);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ViewMenuPesananSaya.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

//        Statement stat;
//        
//        stat = (Statement) koneksiDatabase.getKoneksi().createStatement();
//        String sql = "SELECT * FROM informasi_produk";
//        ResultSet res = stat.executeQuery(sql);
    /**
     * Creates new form ViewRegister
     */
    private final JPopupMenu popupMenu = new JPopupMenu();
    private JMenuItem menuItem = null;

    public ViewMenuPesananSaya() throws IOException, FileNotFoundException, SQLException {
        initComponents();

        setIconImage(icon.getImage());

        // Call Our Method
        createPopupMenu(this);

        getUsername();

        // tabel info produk
//        idp = new InputDataProduk(this);
        model = new DefaultTableModel();
        produkTB.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        produkTB.setModel(model);
        model.addColumn("ID Pesanan");
        model.addColumn("Produk");
        model.addColumn("Total Harga");
        model.addColumn("Alamat");
        model.addColumn("Status Pembayaran");
        model.addColumn("Status Pengiriman");
        model.addColumn("Nomor Resi");
        try {
            getInfoProduk();
        } catch (IOException | SQLException ex) {
            Logger.getLogger(ViewMenuPesananSaya.class.getName()).log(Level.SEVERE, null, ex);
        }
        produkTB.setRowHeight(70);
        produkTB.getColumnModel().getColumn(0).setPreferredWidth(10);
        produkTB.getColumnModel().getColumn(1).setPreferredWidth(80);
        produkTB.getColumnModel().getColumn(2).setPreferredWidth(70);
        produkTB.getColumnModel().getColumn(3).setPreferredWidth(90);
        produkTB.getColumnModel().getColumn(4).setPreferredWidth(10);
        produkTB.getColumnModel().getColumn(5).setPreferredWidth(30);
        produkTB.getColumnModel().getColumn(6).setPreferredWidth(30);

//        gambarBT.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent ae) {
//                if (ae.getSource()==gambarBT) {
//                    JFileChooser fileChooser = new JFileChooser();
//                    
//                    int respon = fileChooser.showOpenDialog(null);
//                    
//                    if (respon == JFileChooser.APPROVE_OPTION) {
//                        File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
//                        setGambarBT(file);
//                    }
//                }
//            }
//        });
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        akunIcon = new javax.swing.JLabel();
        usernameLB = new javax.swing.JLabel();
        homeIcon = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        produkTB = new javax.swing.JTable();
        konfirmasiPembayaran = new javax.swing.JButton();
        reloadLB = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Get Your Dimsum");
        setMinimumSize(new java.awt.Dimension(524, 268));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(223, 164, 164));
        jPanel1.setPreferredSize(new java.awt.Dimension(356, 839));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Raleway", 3, 14)); // NOI18N
        jLabel2.setText("Made with love to bless your tummy");

        jLabel4.setFont(new java.awt.Font("Raleway", 3, 16)); // NOI18N
        jLabel4.setText("Est. 2019");

        jLabel5.setFont(new java.awt.Font("Raleway", 3, 16)); // NOI18N
        jLabel5.setText("We are present in");

        jLabel6.setFont(new java.awt.Font("Raleway", 3, 16)); // NOI18N
        jLabel6.setText("Jakarta");

        jLabel12.setFont(new java.awt.Font("Raleway", 3, 16)); // NOI18N
        jLabel12.setText("Bogor");

        jLabel13.setFont(new java.awt.Font("Raleway", 3, 16)); // NOI18N
        jLabel13.setText("Depok");

        jLabel14.setFont(new java.awt.Font("Raleway", 3, 16)); // NOI18N
        jLabel14.setText("Tangerang");

        jLabel15.setFont(new java.awt.Font("Raleway", 3, 16)); // NOI18N
        jLabel15.setText("Bekasi");

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/unsika.png"))); // NOI18N

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Fasilkom.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(34, 34, 34))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel13)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel14))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(jLabel15))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel18))
                            .addComponent(jLabel2))))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel1)
                .addGap(47, 47, 47)
                .addComponent(jLabel2)
                .addGap(0, 0, 0)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addGap(2, 2, 2)
                .addComponent(jLabel6)
                .addGap(0, 0, 0)
                .addComponent(jLabel12)
                .addGap(1, 1, 1)
                .addComponent(jLabel13)
                .addGap(0, 0, 0)
                .addComponent(jLabel14)
                .addGap(0, 0, 0)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(112, 112, 112));
        jLabel7.setText("Pesanan Saya");

        jPanel3.setBackground(new java.awt.Color(187, 151, 151));

        akunIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/account.png"))); // NOI18N
        akunIcon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        akunIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                akunIconMouseClicked(evt);
            }
        });

        usernameLB.setFont(new java.awt.Font("Raleway", 1, 18)); // NOI18N
        usernameLB.setText("Nama Pengguna");

        homeIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/home.png"))); // NOI18N
        homeIcon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        homeIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homeIconMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(homeIcon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(akunIcon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usernameLB)
                .addGap(29, 29, 29))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(homeIcon)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(36, 36, 36)
                            .addComponent(usernameLB))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(8, 8, 8)
                            .addComponent(akunIcon))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        produkTB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Pesanan", "Produk", "Total Harga", "Alamat", "Status Pembayaran", "Status Pengiriman", "Nomor Resi"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(produkTB);

        konfirmasiPembayaran.setFont(new java.awt.Font("Raleway", 0, 18)); // NOI18N
        konfirmasiPembayaran.setText("KONFIRMASI PEMBAYARAN");
        konfirmasiPembayaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                konfirmasiPembayaranActionPerformed(evt);
            }
        });

        reloadLB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/reload.png"))); // NOI18N
        reloadLB.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        reloadLB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reloadLBMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(konfirmasiPembayaran)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(83, 83, 83))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(443, 443, 443)
                        .addComponent(reloadLB)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(reloadLB)
                    .addComponent(jLabel7))
                .addGap(41, 41, 41)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(konfirmasiPembayaran)
                .addContainerGap(389, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 918, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void akunIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_akunIconMouseClicked
        // Get Mouse Position
//        int mPosX = MouseInfo.getPointerInfo().getLocation().x;
//        int mPosY = MouseInfo.getPointerInfo().getLocation().y;
        // Show Popup Menu
        popupMenu.show(this, 740, 110);
    }//GEN-LAST:event_akunIconMouseClicked

    private void konfirmasiPembayaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_konfirmasiPembayaranActionPerformed
        try {

            Desktop.getDesktop().browse(new URI("https://docs.google.com/forms/d/e/1FAIpQLSdVY3trRjWBWVIATFQ2o1oFK_7S46UXhjtfQ90To90nJ-PC-A/viewform"));

        } catch (IOException | URISyntaxException e1) {
            e1.printStackTrace();
        }
    }//GEN-LAST:event_konfirmasiPembayaranActionPerformed

    private void reloadLBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reloadLBMouseClicked
        try {
            new ViewMenuPesananSaya().setVisible(true);
        } catch (IOException | SQLException ex) {
            Logger.getLogger(ViewMenuPesananSaya.class.getName()).log(Level.SEVERE, null, ex);
        }
        dispose();
    }//GEN-LAST:event_reloadLBMouseClicked

    private void homeIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeIconMouseClicked
        try {
            new ViewMenuUtama().setVisible(true);
        } catch (IOException | SQLException ex) {
            Logger.getLogger(ViewMenuAkunSayaEdit.class.getName()).log(Level.SEVERE, null, ex);
        }
        dispose();
    }//GEN-LAST:event_homeIconMouseClicked

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
            java.util.logging.Logger.getLogger(ViewMenuPesananSaya.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewMenuPesananSaya.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewMenuPesananSaya.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewMenuPesananSaya.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ViewMenuPesananSaya().setVisible(true);
                } catch (IOException | SQLException ex) {
                    Logger.getLogger(ViewMenuPesananSaya.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Create Menu Method
    private void createPopupMenu(JFrame frame) {
        // Create File Menu
        menuItem = new JMenuItem(
                "Akun Saya",
                new ImageIcon("C:\\Users\\hp\\Documents\\NetBeansProjects\\Get Your Dimsum\\src\\icon\\account.png")
        );
        // Apply Desc
        menuItem.getAccessibleContext().setAccessibleDescription("Akun Saya");
        menuItem.setFont(new Font("MV Boli", Font.PLAIN, 20));
        // Create an Action Listener
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    new ViewMenuAkunSayaEdit().setVisible(true);
                } catch (IOException | SQLException ex) {
                    Logger.getLogger(ViewMenuPesananSaya.class.getName()).log(Level.SEVERE, null, ex);
                }
                dispose();
            }
        });
        // Add Menu Item Into Popup Menu
        popupMenu.add(menuItem);

        // Add another menu item to popup menu
        menuItem = new JMenuItem(
                "Pesanan Saya",
                new ImageIcon("C:\\Users\\hp\\Documents\\NetBeansProjects\\Get Your Dimsum\\src\\icon\\book.png")
        );
        // Apply Desc
        menuItem.getAccessibleContext().setAccessibleDescription("Pesanan Saya");
        menuItem.setFont(new Font("MV Boli", Font.PLAIN, 20));
        // Create an Action Listener
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JOptionPane.showMessageDialog(null, "Anda telah berada di Menu Pesanan Saya");
            }
        });
        // Add Menu Item Into Popup Menu
        popupMenu.add(menuItem);
        
        // Add another menu item to popup menu
            menuItem  = new JMenuItem(
                    "Settings",
                    new ImageIcon("C:\\Users\\hp\\Documents\\NetBeansProjects\\Get Your Dimsum\\src\\icon\\settings.png")
            );
            // Apply Desc

            menuItem.getAccessibleContext ()

            .setAccessibleDescription("Settings");
            menuItem.setFont (
                    

            new Font("MV Boli", Font.PLAIN, 20));
        // Create an Action Listener
            menuItem.addActionListener (
                     
                new ActionListener() {
            @Override
                public void actionPerformed
                (ActionEvent ae
                
                
                    ) {
                
                try {
                        ViewLogin.getViewLogin().name = name;
                        ViewLogin.getViewLogin().pass = pass;
                        new ViewSettings().setVisible(true);
                        dispose();
                    } catch (IOException | SQLException ex) {
                        Logger.getLogger(ViewMenuUtama.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }

            );
        // Add Menu Item Into Popup Menu
            popupMenu.add (menuItem);

        // Add another menu item to popup menu
        menuItem = new JMenuItem(
                "Log-out",
                new ImageIcon("C:\\Users\\hp\\Documents\\NetBeansProjects\\Get Your Dimsum\\src\\icon\\logout.png")
        );
        // Apply Desc
        menuItem.getAccessibleContext().setAccessibleDescription("Log-out");
        menuItem.setFont(new Font("MV Boli", Font.PLAIN, 20));
        // Create an Action Listener
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String tanya = "Apakah yakin mau Log-out?";
                boolean yakin = JOptionPane.showConfirmDialog(rootPane, tanya) == JOptionPane.YES_OPTION;
                if (yakin) {
                    JOptionPane.showMessageDialog(frame, "Anda Telah Logout");
                    new ViewLogin().setVisible(true);
                    dispose();
                }
            }
        });
        // Add Menu Item Into Popup Menu
        popupMenu.add(menuItem);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel akunIcon;
    private javax.swing.JLabel homeIcon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton konfirmasiPembayaran;
    public static javax.swing.JTable produkTB;
    private javax.swing.JLabel reloadLB;
    private javax.swing.JLabel usernameLB;
    // End of variables declaration//GEN-END:variables
}
