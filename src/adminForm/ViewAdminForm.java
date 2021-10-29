/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminForm;

import akunSaya.ViewMenuAkunSayaEdit;
import database.koneksiDatabase;
import database.management.InputDataProduk;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import login.ViewLogin;

/**
 *
 * @author hp
 */
public class ViewAdminForm extends javax.swing.JFrame {
    String path = "";
    Image showImage;
    ImageIcon icon = new ImageIcon("src/img/logo.png");
    
    private DefaultTableModel model;
    private InputDataProduk idp;
    private String idProduk;

    public String getIdProduk() {
        return idProduk;
    }

    public JTextField getBeratTF() {
        return beratTF;
    }

    public JTextArea getDeskripsiTA() {
        return deskripsiTA;
    }

    public JButton getGambarBT() {
        return gambarBT;
    }

    public JTextField getHargaTF() {
        return hargaTF;
    }

    public JTextField getNamaTF() {
        return namaTF;
    }

    public JTextField getStokTF() {
        return stokTF;
    }

    public void setGambarBT(JButton gambarBT) {
        this.gambarBT = gambarBT;
    }
    
    public void setPathImage(String path) {
        this.path = path;
    }
    
    public String getPathImage() {
        return this.path;
    }

    public void setShowImage(Image showImage) {
        this.showImage = showImage;
    }

    public Image getShowImage() {
        return showImage;
    }
    
    
    
    public void getInfoProduk() throws IOException, FileNotFoundException, SQLException {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        
        Statement stat;
        
        stat = (Statement) koneksiDatabase.getKoneksi().createStatement();
        String sql = "SELECT * FROM informasi_produk";
        ResultSet res = stat.executeQuery(sql);
        
        while (res.next()) {
            Object[] obj = new Object[7];
            obj[0] = res.getString("id_produk");
            obj[1] = res.getString("gambar");
            obj[2] = res.getString("nama_produk");
            obj[3] = res.getString("harga_produk");
            obj[4] = res.getString("stok");
            obj[5] = res.getString("berat_barang");
            obj[6] = res.getString("deskripsi_produk");
            model.addRow(obj);
        }
    }
    
    public void ambilData() {
        int index = produkTB.getSelectedRow();
        
        String id_produk = String.valueOf(produkTB.getValueAt(index, 0));
        String gambar = String.valueOf(produkTB.getValueAt(index, 1));
        String nama_produk = String.valueOf(produkTB.getValueAt(index, 2));
        String harga_produk = String.valueOf(produkTB.getValueAt(index, 3));
        String stok = String.valueOf(produkTB.getValueAt(index, 4));
        String berat_barang = String.valueOf(produkTB.getValueAt(index, 5));
        String deskripsi_produk = String.valueOf(produkTB.getValueAt(index, 6));
        
        
        idProduk = id_produk;
        namaTF.setText(nama_produk);
        hargaTF.setText(harga_produk);
        stokTF.setText(stok);
        beratTF.setText(berat_barang);
        deskripsiTA.setText(deskripsi_produk);
        ImageIcon ii = new ImageIcon(gambar);
        Image image = ii.getImage().getScaledInstance(gambarLB.getWidth(), gambarLB.getHeight(), Image.SCALE_SMOOTH);
        gambarLB.setIcon(new ImageIcon(image));
//        gambarBT.setText(gambar);
    }
    
    /**
     * Creates new form ViewRegister
     */
    private final JPopupMenu popupMenu = new JPopupMenu();
    private JMenuItem menuItem = null;
    public ViewAdminForm() throws IOException, FileNotFoundException, SQLException {
        initComponents();
        
        setIconImage(icon.getImage());
        
        // Call Our Method
        createPopupMenu(this);
        
        // tabel info produk
        idp = new InputDataProduk(this);
        model = new DefaultTableModel();
        
        produkTB.setModel(model);
        model.addColumn("ID");
        model.addColumn("Gambar");
        model.addColumn("Nama Produk");
        model.addColumn("Harga/pcs");
        model.addColumn("Stok");
        model.addColumn("Berat(kg)");
        model.addColumn("Deskripsi Produk");
        getInfoProduk();
        produkTB.setRowHeight(70);
        produkTB.getColumnModel().getColumn(0).setPreferredWidth(10);
        produkTB.getColumnModel().getColumn(1).setPreferredWidth(80);
        produkTB.getColumnModel().getColumn(2).setPreferredWidth(70);
        produkTB.getColumnModel().getColumn(3).setPreferredWidth(40);
        produkTB.getColumnModel().getColumn(4).setPreferredWidth(10);
        produkTB.getColumnModel().getColumn(5).setPreferredWidth(30);
        produkTB.getColumnModel().getColumn(6).setPreferredWidth(120);
        produkTB.getColumnModel().getColumn(1).setCellRenderer(new ImageRender());
        
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
    
    private class ImageRender extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
            String photoName = o.toString();
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(photoName).getImage().getScaledInstance(120,70, Image.SCALE_SMOOTH));
            return new JLabel(imageIcon);
        }
        
    }
    
    public void clearTextField() {
        namaTF.setText("");
        hargaTF.setText("");
        stokTF.setText("");
        beratTF.setText("");
        deskripsiTA.setText("");
        namaTF.requestFocus();
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
        jLabel17 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        namaTF = new javax.swing.JTextField();
        hargaTF = new javax.swing.JTextField();
        stokTF = new javax.swing.JTextField();
        beratTF = new javax.swing.JTextField();
        gambarBT = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        deskripsiTA = new javax.swing.JTextArea();
        tambahBT = new javax.swing.JButton();
        hapusBT = new javax.swing.JButton();
        updateBT = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        produkTB = new javax.swing.JTable();
        gambarLB = new javax.swing.JLabel();

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
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel14))
                            .addComponent(jLabel5)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel13)))))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 386, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(112, 112, 112));
        jLabel7.setText("Administrator");

        jPanel3.setBackground(new java.awt.Color(187, 151, 151));

        akunIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/account.png"))); // NOI18N
        akunIcon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        akunIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                akunIconMouseClicked(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Raleway", 1, 18)); // NOI18N
        jLabel17.setText("Nama Pengguna");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(519, Short.MAX_VALUE)
                .addComponent(akunIcon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addGap(29, 29, 29))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel17))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(akunIcon)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setText("Nama Produk");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel8.setText("Harga/pcs");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel10.setText("Stok");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel11.setText("Berat (kg)");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel19.setText("Deskripsi Produk");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel20.setText("Gambar");

        namaTF.setFont(new java.awt.Font("Raleway", 0, 18)); // NOI18N

        hargaTF.setFont(new java.awt.Font("Raleway", 0, 18)); // NOI18N

        stokTF.setFont(new java.awt.Font("Raleway", 0, 18)); // NOI18N

        beratTF.setFont(new java.awt.Font("Raleway", 0, 18)); // NOI18N

        gambarBT.setFont(new java.awt.Font("Raleway", 0, 18)); // NOI18N
        gambarBT.setText("Pilih Gambar");
        gambarBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gambarBTActionPerformed(evt);
            }
        });

        deskripsiTA.setColumns(20);
        deskripsiTA.setFont(new java.awt.Font("Raleway", 0, 18)); // NOI18N
        deskripsiTA.setRows(5);
        jScrollPane1.setViewportView(deskripsiTA);

        tambahBT.setFont(new java.awt.Font("Raleway", 0, 18)); // NOI18N
        tambahBT.setText("Tambah");
        tambahBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahBTActionPerformed(evt);
            }
        });

        hapusBT.setFont(new java.awt.Font("Raleway", 0, 18)); // NOI18N
        hapusBT.setText("Hapus");
        hapusBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusBTActionPerformed(evt);
            }
        });

        updateBT.setFont(new java.awt.Font("Raleway", 0, 18)); // NOI18N
        updateBT.setText("Update");
        updateBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBTActionPerformed(evt);
            }
        });

        produkTB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Gambar", "Nama", "Harga", "Stok", "Berat", "Deskripsi"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        produkTB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                produkTBMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(produkTB);

        gambarLB.setPreferredSize(new java.awt.Dimension(100, 130));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(254, 254, 254))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(73, 73, 73))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(gambarBT)
                                        .addGap(18, 18, 18)
                                        .addComponent(gambarLB, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(namaTF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
                                    .addComponent(hargaTF, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(stokTF, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(beratTF, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(145, 145, 145))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(tambahBT)
                                .addGap(35, 35, 35)
                                .addComponent(hapusBT)
                                .addGap(35, 35, 35)
                                .addComponent(updateBT))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1)))
                        .addGap(145, 145, 145))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(namaTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(hargaTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(stokTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(beratTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(gambarBT))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(gambarLB, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tambahBT)
                    .addComponent(hapusBT)
                    .addComponent(updateBT))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void tambahBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahBTActionPerformed
        idp.tambahData();
        try {
            getInfoProduk();
        } catch (IOException | SQLException ex) {
            Logger.getLogger(ViewAdminForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//        produkTB.setModel(model);
        clearTextField();
    }//GEN-LAST:event_tambahBTActionPerformed

    private void hapusBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusBTActionPerformed
        idp.hapusData();
        
        try {
            getInfoProduk();
        } catch (IOException | SQLException ex) {
            Logger.getLogger(ViewAdminForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//        produkTB.setModel(model);
        clearTextField();
    }//GEN-LAST:event_hapusBTActionPerformed

    private void produkTBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_produkTBMouseClicked
        ambilData();
    }//GEN-LAST:event_produkTBMouseClicked

    private void updateBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBTActionPerformed
        idp.updateData();
        
        try {
            getInfoProduk();
        } catch (IOException | SQLException ex) {
            Logger.getLogger(ViewAdminForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        produkTB.setModel(model);
        clearTextField();
    }//GEN-LAST:event_updateBTActionPerformed

    
    private void gambarBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gambarBTActionPerformed
        JFileChooser fc = new JFileChooser("src/produkImage/");
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("IMAGES", "png", "jpg", "jpeg");
        fc.addChoosableFileFilter(fnef);
        int respon = fc.showOpenDialog(null);
        
        if (respon == JFileChooser.APPROVE_OPTION) {
            File selectedImageFile = fc.getSelectedFile();
            String selectedImagePath = selectedImageFile.getAbsolutePath();
            JOptionPane.showMessageDialog(null, selectedImagePath);
            ImageIcon ii = new ImageIcon(selectedImagePath);
            Image image = ii.getImage().getScaledInstance(gambarLB.getWidth(), gambarLB.getHeight(), Image.SCALE_SMOOTH);
            
            gambarLB.setIcon(new ImageIcon(image));
            setPathImage(selectedImagePath);
            setShowImage(image);
            
        }
    }//GEN-LAST:event_gambarBTActionPerformed

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
            java.util.logging.Logger.getLogger(ViewAdminForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewAdminForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewAdminForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewAdminForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                    new ViewAdminForm().setVisible(true);
                } catch (IOException | SQLException ex) {
                    Logger.getLogger(ViewAdminForm.class.getName()).log(Level.SEVERE, null, ex);
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
                    Logger.getLogger(ViewAdminForm.class.getName()).log(Level.SEVERE, null, ex);
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
                try {
                    new ViewMenuAkunSayaEdit().setVisible(true);
                } catch (IOException | SQLException ex) {
                    Logger.getLogger(ViewAdminForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                dispose();
            }
        });
        // Add Menu Item Into Popup Menu
        popupMenu.add(menuItem);
        
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
    private javax.swing.JTextField beratTF;
    private javax.swing.JTextArea deskripsiTA;
    private javax.swing.JButton gambarBT;
    private javax.swing.JLabel gambarLB;
    private javax.swing.JButton hapusBT;
    private javax.swing.JTextField hargaTF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField namaTF;
    private javax.swing.JTable produkTB;
    private javax.swing.JTextField stokTF;
    private javax.swing.JButton tambahBT;
    private javax.swing.JButton updateBT;
    // End of variables declaration//GEN-END:variables
}
