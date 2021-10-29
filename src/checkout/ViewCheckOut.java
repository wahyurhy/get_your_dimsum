/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkout;

import checkout.*;
import akunSaya.ViewMenuAkunSayaEdit;
import akunSaya.ViewSettings;
import database.koneksiDatabase;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import login.ViewLogin;
import menuUtama.ViewMenuUtama;
import pesananSaya.ViewMenuPesananSaya;
import pesananSaya.ViewMenuPesananSayaEmpty;

/**
 *
 * @author hp
 */
public class ViewCheckOut extends javax.swing.JFrame {
//    String path = "";
//    Image showImage;

    ImageIcon icon = new ImageIcon("src/img/logo.png");

    public String name, pass;
    public int id;

    private static ViewCheckOut instance = null;

    private void ViewCheckOut() {

    }

    public static ViewCheckOut getViewCheckOut() throws IOException, FileNotFoundException, SQLException {
        if (instance == null) {
            instance = new ViewCheckOut();
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
    private ViewMenuAkunSayaEdit akun;
//    private InputDataProduk idp;
//    private String idProduk;

//    public String getIdProduk() {
//        return idProduk;
//    }
//    public JTextField getBeratTF() {
//        return beratTF;
//    }
//
//    public JTextArea getDeskripsiTA() {
//        return deskripsiTA;
//    }
    public JTextField getAlamatTF() {
        return alamatTF;
    }

    public JTextArea getDeskripsiTA() {
        return catatanTA;
    }

    public JTextField getKecamatanTF() {
        return kecamatanTF;
    }

    public JTextField getKelurahanTF() {
        return kelurahanTF;
    }

    public JTextField getKodePostTF() {
        return kodePostTF;
    }

    public JTextField getKotaKabupatenTF() {
        return kotaKabupatenTF;
    }

    public JTextField getProvinsiTF() {
        return provinsiTF;
    }

    public JTextField getRtTF() {
        return rtTF;
    }

    public JTextField getRwTF() {
        return rwTF;
    }

    public JTextField getTelpTF() {
        return telpTF;
    }

    public JTextField getNamaTF() {
        return namaTF;
    }

//    public void setGambarBT(JButton gambarBT) {
//        this.gambarBT = gambarBT;
//    }
//    
//    public void setPathImage(String path) {
//        this.path = path;
//    }
//    
//    public String getPathImage() {
//        return this.path;
//    }
//
//    public void setShowImage(Image showImage) {
//        this.showImage = showImage;
//    }
//
//    public Image getShowImage() {
//        return showImage;
//    }
    public void getInfoProduk() {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();

        int jumlah1 = ((Integer) ViewMenuUtama.spinnerProduk1.getValue()) * (Integer.valueOf(ViewMenuUtama.hargaProduk1.getText()));
        String value1 = String.valueOf(jumlah1);
        int jumlah2 = ((Integer) ViewMenuUtama.spinnerProduk2.getValue()) * (Integer.valueOf(ViewMenuUtama.hargaProduk2.getText()));
        String value2 = String.valueOf(jumlah2);
        int jumlah3 = ((Integer) ViewMenuUtama.spinnerProduk3.getValue()) * (Integer.valueOf(ViewMenuUtama.hargaProduk3.getText()));
        String value3 = String.valueOf(jumlah3);
        int jumlah4 = ((Integer) ViewMenuUtama.spinnerProduk4.getValue()) * (Integer.valueOf(ViewMenuUtama.hargaProduk4.getText()));
        String value4 = String.valueOf(jumlah4);
        int jumlah5 = ((Integer) ViewMenuUtama.spinnerProduk5.getValue()) * (Integer.valueOf(ViewMenuUtama.hargaProduk5.getText()));
        String value5 = String.valueOf(jumlah5);
        int jumlah6 = ((Integer) ViewMenuUtama.spinnerProduk6.getValue()) * (Integer.valueOf(ViewMenuUtama.hargaProduk6.getText()));
        String value6 = String.valueOf(jumlah6);

        Object daftarBeli[][] = {{ViewMenuUtama.namaProduk1.getText(), ViewMenuUtama.hargaProduk1.getText(), ViewMenuUtama.spinnerProduk1.getValue(), ViewMenuUtama.gambar1.getToolTipText(), value1},
        {ViewMenuUtama.namaProduk2.getText(), ViewMenuUtama.hargaProduk2.getText(), ViewMenuUtama.spinnerProduk2.getValue(), ViewMenuUtama.gambar2.getToolTipText(), value2},
        {ViewMenuUtama.namaProduk3.getText(), ViewMenuUtama.hargaProduk3.getText(), ViewMenuUtama.spinnerProduk3.getValue(), ViewMenuUtama.gambar3.getToolTipText(), value3},
        {ViewMenuUtama.namaProduk4.getText(), ViewMenuUtama.hargaProduk4.getText(), ViewMenuUtama.spinnerProduk4.getValue(), ViewMenuUtama.gambar4.getToolTipText(), value4},
        {ViewMenuUtama.namaProduk5.getText(), ViewMenuUtama.hargaProduk5.getText(), ViewMenuUtama.spinnerProduk5.getValue(), ViewMenuUtama.gambar5.getToolTipText(), value5},
        {ViewMenuUtama.namaProduk6.getText(), ViewMenuUtama.hargaProduk6.getText(), ViewMenuUtama.spinnerProduk6.getValue(), ViewMenuUtama.gambar6.getToolTipText(), value6}};

        for (Object[] obj : daftarBeli) {
            if (((int) obj[2]) == 0) {
                continue;
            }
            model.addRow(new Object[]{obj[3], obj[0], obj[2], obj[1], obj[4]});
        }
    }
//    public void getInfoProduk() throws IOException, FileNotFoundException, SQLException {
//        model.getDataVector().removeAllElements();
//        model.fireTableDataChanged();
//        
//        Statement stat;
//        
//        stat = (Statement) koneksiDatabase.getKoneksi().createStatement();
//        String sql = "SELECT * FROM informasi_produk";
//        ResultSet res = stat.executeQuery(sql);
//        
//        while (res.next()) {
//            Object[] obj = new Object[7];
//            obj[0] = res.getString("id_produk");
//            obj[1] = res.getString("gambar");
//            obj[2] = res.getString("nama_produk");
//            obj[3] = res.getString("harga_produk");
//            obj[4] = res.getString("stok");
//            obj[5] = res.getString("berat_barang");
//            obj[6] = res.getString("deskripsi_produk");
//            model.addRow(obj);
//        }
//    }

    public void ambilData() {
//        int index = produkTB.getSelectedRow();

        String nama = String.valueOf(akun.getNamaTF());
        String telp = String.valueOf(akun.getTelpTF());
        String alamat = String.valueOf(akun.getAlamatTF());
        String rt = String.valueOf(akun.getRtTF());
        String rw = String.valueOf(akun.getRwTF());
        String kelurahan = String.valueOf(akun.getKelurahanTF());
        String kecamatan = String.valueOf(akun.getKecamatanTF());
        String kotaKabupaten = String.valueOf(akun.getKotaKabupatenTF());
        String provinsi = String.valueOf(akun.getNamaTF());
        String kodePos = String.valueOf(akun.getKodePostTF());

        namaTF.setText(nama);
        telpTF.setText(telp);
        alamatTF.setText(alamat);
        rtTF.setText(rt);
        rwTF.setText(rw);
        kelurahanTF.setText(kelurahan);
        kecamatanTF.setText(kecamatan);
        kotaKabupatenTF.setText(kotaKabupaten);
        provinsiTF.setText(provinsi);
        kodePostTF.setText(kodePos);

//        ImageIcon ii = new ImageIcon(gambar);
//        Image image = ii.getImage().getScaledInstance(gambarLB.getWidth(), gambarLB.getHeight(), Image.SCALE_SMOOTH);
//        gambarLB.setIcon(new ImageIcon(image));
//        gambarBT.setText(gambar);
    }

//    public int ongkir() {
//        int ongkir = 0;
//            if (Integer.valueOf(ViewMenuUtama.jumlahKeranjangBelanjaTF.getText()) < 100000) {
//                ongkir = 10000;
//            } else if (Integer.valueOf(ViewMenuUtama.jumlahKeranjangBelanjaTF.getText()) < 500000) {
//                ongkir = 20000;
//            } else {
//                ongkir = 35000;
//            }
//            return ongkir;
//    }
    /**
     * Creates new form ViewRegister
     */
    private final JPopupMenu popupMenu = new JPopupMenu();
    private JMenuItem menuItem = null;

    public ViewCheckOut() throws IOException, FileNotFoundException, SQLException {
        initComponents();

        setIconImage(icon.getImage());

        // Call Our Method
        createPopupMenu(this);

        getUsername();

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
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(photoName).getImage().getScaledInstance(120, 70, Image.SCALE_SMOOTH));
            return new JLabel(imageIcon);
        }

    }

//    public void clearTextField() {
//        namaTF.setText("");
//        hargaTF.setText("");
//        stokTF.setText("");
//        beratTF.setText("");
//        deskripsiTA.setText("");
//        namaTF.requestFocus();
//    }
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
        bayarBT = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        produkTB = new javax.swing.JTable();
        namaTF = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        telpTF = new javax.swing.JTextField();
        alamatTF = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        rtTF = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        rwTF = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        kelurahanTF = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        kecamatanTF = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        kotaKabupatenTF = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        provinsiTF = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        kodePostTF = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        catatanTA = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        ongkir = new javax.swing.JLabel();
        total = new javax.swing.JLabel();
        subtotal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Get Your Dimsum");
        setMinimumSize(new java.awt.Dimension(524, 268));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(112, 112, 112));
        jLabel7.setText("Checkout");

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
                .addGap(1, 1, 1)
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

        bayarBT.setFont(new java.awt.Font("Raleway", 0, 18)); // NOI18N
        bayarBT.setText("Bayar");
        bayarBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bayarBTActionPerformed(evt);
            }
        });

        produkTB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Gambar", "Nama", "Quantity", "Harga", "Jumlah"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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

        namaTF.setFont(new java.awt.Font("Raleway", 0, 16)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Raleway", 0, 16)); // NOI18N
        jLabel11.setText("Nama");

        jLabel20.setFont(new java.awt.Font("Raleway", 0, 16)); // NOI18N
        jLabel20.setText("No. Telp / WA");

        telpTF.setFont(new java.awt.Font("Raleway", 0, 16)); // NOI18N

        alamatTF.setFont(new java.awt.Font("Raleway", 0, 16)); // NOI18N

        jLabel21.setFont(new java.awt.Font("Raleway", 0, 16)); // NOI18N
        jLabel21.setText("Alamat Penerima");

        jLabel22.setFont(new java.awt.Font("Raleway", 0, 16)); // NOI18N
        jLabel22.setText("RT/RW");

        rtTF.setFont(new java.awt.Font("Raleway", 0, 16)); // NOI18N
        rtTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rtTFActionPerformed(evt);
            }
        });

        jLabel9.setText("/");

        rwTF.setFont(new java.awt.Font("Raleway", 0, 16)); // NOI18N

        jLabel23.setFont(new java.awt.Font("Raleway", 0, 16)); // NOI18N
        jLabel23.setText("Kelurahan");

        kelurahanTF.setFont(new java.awt.Font("Raleway", 0, 16)); // NOI18N

        jLabel24.setFont(new java.awt.Font("Raleway", 0, 16)); // NOI18N
        jLabel24.setText("Kecamatan");

        kecamatanTF.setFont(new java.awt.Font("Raleway", 0, 16)); // NOI18N

        jLabel25.setFont(new java.awt.Font("Raleway", 0, 16)); // NOI18N
        jLabel25.setText("Kota / Kabupaten");

        kotaKabupatenTF.setFont(new java.awt.Font("Raleway", 0, 16)); // NOI18N

        jLabel26.setFont(new java.awt.Font("Raleway", 0, 16)); // NOI18N
        jLabel26.setText("Provinsi");

        provinsiTF.setFont(new java.awt.Font("Raleway", 0, 16)); // NOI18N

        jLabel27.setFont(new java.awt.Font("Raleway", 0, 16)); // NOI18N
        jLabel27.setText("Kode Pos");

        kodePostTF.setFont(new java.awt.Font("Raleway", 0, 16)); // NOI18N

        jLabel19.setFont(new java.awt.Font("Raleway", 0, 16)); // NOI18N
        jLabel19.setText("Catatan");

        catatanTA.setColumns(20);
        catatanTA.setFont(new java.awt.Font("Raleway", 0, 16)); // NOI18N
        catatanTA.setRows(5);
        jScrollPane1.setViewportView(catatanTA);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setText("Sub-Total");

        jLabel8.setText("Ongkir");

        jLabel10.setText("Total");

        jLabel17.setText(":");

        jLabel28.setText(":");

        jLabel29.setText(":");

        ongkir.setText("...");

        total.setText("...");

        subtotal.setText("...");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(jLabel10))
                        .addComponent(jLabel8)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ongkir))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(total))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(subtotal)))
                .addGap(43, 43, 43))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel29)
                    .addComponent(subtotal))
                .addGap(1, 1, 1)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel28)
                    .addComponent(ongkir))
                .addGap(1, 1, 1)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel17)
                        .addComponent(total)))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(63, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(286, 286, 286))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(telpTF)
                                    .addComponent(alamatTF)
                                    .addComponent(kelurahanTF)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(rtTF, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel9)
                                        .addGap(1, 1, 1)
                                        .addComponent(rwTF, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(kecamatanTF)
                                    .addComponent(kotaKabupatenTF)
                                    .addComponent(provinsiTF)
                                    .addComponent(kodePostTF)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
                                    .addComponent(namaTF)))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(bayarBT)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE)
                                .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap(78, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(namaTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(telpTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(alamatTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(rtTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(rwTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(kelurahanTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(kecamatanTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(kotaKabupatenTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(provinsiTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(kodePostTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addGap(18, 18, 18)
                .addComponent(bayarBT)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
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

    private void produkTBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_produkTBMouseClicked
//        ambilData();
    }//GEN-LAST:event_produkTBMouseClicked

    private void bayarBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bayarBTActionPerformed
        if (namaTF.getText().isEmpty() || telpTF.getText().isEmpty() || alamatTF.getText().isEmpty() || rtTF.getText().isEmpty()
                || rwTF.getText().isEmpty() || kelurahanTF.getText().isEmpty() || kecamatanTF.getText().isEmpty() || kotaKabupatenTF.getText().isEmpty()
                || provinsiTF.getText().isEmpty() || kodePostTF.getText().isEmpty() || catatanTA.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Silakan input semua kolom!");
        } else if (!telpTF.getText().matches("^\\d+$") || !rwTF.getText().matches("^\\d+$") || !rtTF.getText().matches("^\\d+$") || !kodePostTF.getText().matches("^\\d+$")) {
            JOptionPane.showMessageDialog(null, "Masukkan angka pada kolom no telp, RT, RW, dan kode pos!");
        } else {
            try {
                ViewCheckOutKonfirmasi halamanKonfirmasi = new ViewCheckOutKonfirmasi();
                ViewLogin halamanLogin = new ViewLogin();
                int ongkirHarga = Integer.valueOf(ongkir.getText());
                int totalHarga = Integer.valueOf(total.getText());
//            int ongkir = 0;
//            if (Integer.valueOf(ViewMenuUtama.jumlahKeranjangBelanjaTF.getText()) < 100000) {
//                ongkir = 10000;
//            } else if (Integer.valueOf(ViewMenuUtama.jumlahKeranjangBelanjaTF.getText()) < 500000) {
//                ongkir = 20000;
//            } else {
//                ongkir = 35000;
//            }
                // int totalHarga = Integer.valueOf(ViewMenuUtama.jumlahKeranjangBelanjaTF.getText()) + ongkir;
//            int totalHarga = (Integer.valueOf(ViewMenuUtama.hargaProduk1.getText())*(Integer) ViewMenuUtama.spinnerProduk1.getValue())+
//                             (Integer.valueOf(ViewMenuUtama.hargaProduk2.getText())*(Integer) ViewMenuUtama.spinnerProduk2.getValue())+
//                             (Integer.valueOf(ViewMenuUtama.hargaProduk3.getText())*(Integer) ViewMenuUtama.spinnerProduk3.getValue())+
//                             (Integer.valueOf(ViewMenuUtama.hargaProduk4.getText())*(Integer) ViewMenuUtama.spinnerProduk4.getValue())+
//                             (Integer.valueOf(ViewMenuUtama.hargaProduk5.getText())*(Integer) ViewMenuUtama.spinnerProduk5.getValue())+
//                             (Integer.valueOf(ViewMenuUtama.hargaProduk6.getText())*(Integer) ViewMenuUtama.spinnerProduk6.getValue());

                String hargaMasingMasing = ViewMenuUtama.hargaProduk1.getText() + "," + ViewMenuUtama.hargaProduk2.getText() + "," + ViewMenuUtama.hargaProduk3.getText()
                        + "," + ViewMenuUtama.hargaProduk4.getText() + "," + ViewMenuUtama.hargaProduk5.getText() + "," + ViewMenuUtama.hargaProduk6.getText();
                String kuantitas = ViewMenuUtama.spinnerProduk1.getValue() + "," + ViewMenuUtama.spinnerProduk2.getValue() + "," + ViewMenuUtama.spinnerProduk3.getValue() + ","
                        + ViewMenuUtama.spinnerProduk4.getValue() + "," + ViewMenuUtama.spinnerProduk5.getValue() + "," + ViewMenuUtama.spinnerProduk6.getValue();
                String namaProduk = ViewMenuUtama.namaProduk1.getText() + "," + ViewMenuUtama.namaProduk2.getText() + "," + ViewMenuUtama.namaProduk3.getText() + ","
                        + ViewMenuUtama.namaProduk4.getText() + "," + ViewMenuUtama.namaProduk5.getText() + "," + ViewMenuUtama.namaProduk6.getText();

                String note = catatanTA.getText();
                String alamat = namaTF.getText() + " " + telpTF.getText() + "\n"
                        + alamatTF.getText() + ", " + rtTF.getText() + "/" + rwTF.getText()
                        + ", " + kelurahanTF.getText() + ", " + kecamatanTF.getText() + ", "
                        + kotaKabupatenTF.getText() + ", " + provinsiTF.getText() + ", "
                        + kodePostTF.getText();
                String no_resi = "-";
//         Object beliGabungan[] = {namaProduk,kuantitas,hargaMasingMasing,totalHarga,ongkirHarga,note, alamat,no_resi};

                koneksiDatabase.insert("informasi_pesanan", new Object[][]{
                    {"username", usernameLB.getText()},
                    {"nama_produk", namaProduk},
                    {"kuantitas", kuantitas},
                    {"harga", hargaMasingMasing},
                    {"total_harga", totalHarga},
                    {"ongkir", ongkirHarga},
                    {"note", note},
                    {"alamat", alamat},
                    {"no_resi", no_resi},
                    {"status_pengiriman", 0},
                    {"status_pembayaran", 0}
                });
//        String sql = "INSERT INTO informasi_pesanan (username, nama_produk, kuantitas, harga, total_harga, ongkir, note, alamat,no_resi, status_pengiriman, status_pembayaran) VALUE('%s','%s','%s','%s','%s','%s','%s','%s','%s',%d','%d')";
//                                  sql = String.format(sql,ViewLogin.usernameTF.getText(), beliGabungan[0],beliGabungan[1],beliGabungan[2],beliGabungan[3],beliGabungan[4],beliGabungan[5],beliGabungan[6],beliGabungan[7], 0,0);
//                                  Statement stmt;
//                                  
//                stmt = koneksiDatabase.getKoneksi().createStatement();
//                stmt.execute(sql);
                // tampilin id ke tombol
                String query = ("SELECT `id_pesanan` FROM  informasi_pesanan ORDER BY `id_pesanan` DESC LIMIT 1;");
                Statement stmt2;

                stmt2 = koneksiDatabase.getKoneksi().createStatement();
                ResultSet rs = null;
                rs = stmt2.executeQuery(query);
                String id = null;
                if (rs.next()) {
                    System.out.println(ViewLogin.usernameTF.getText() + "-" + String.valueOf(rs.getInt("id_pesanan")));
                    id = usernameLB.getText() + "-" + String.valueOf(rs.getInt("id_pesanan"));
                }
                ViewCheckOutKonfirmasi bayar = new ViewCheckOutKonfirmasi();
                bayar.setVisible(true);
                halamanKonfirmasi.idPesanan.setText(String.valueOf(id));
                ViewCheckOutKonfirmasi.totalLB.setText(String.valueOf(totalHarga));
                ViewLogin.getViewLogin().name = name;
            } catch (IOException | SQLException ex) {
                Logger.getLogger(ViewCheckOut.class.getName()).log(Level.SEVERE, null, ex);
            }
            dispose();
        }
    }//GEN-LAST:event_bayarBTActionPerformed

    private void rtTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rtTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rtTFActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // tabel info produk
//        idp = new InputDataProduk(this);
        model = new DefaultTableModel();

        produkTB.setModel(model);
        model.addColumn("");
        model.addColumn("Nama Produk");
        model.addColumn("Quantity");
        model.addColumn("Harga");
        model.addColumn("Jumlah");
        getInfoProduk();
        produkTB.setRowHeight(70);
        produkTB.getColumnModel().getColumn(0).setPreferredWidth(50);
        produkTB.getColumnModel().getColumn(0).setCellRenderer(new ImageRender());

        ResultSet user;
        try {
            user = koneksiDatabase.get_where("informasi_pengiriman", new Object[][]{
                {"username", usernameLB.getText()}
            });

            if (user.next()) {
            DecimalFormat decimalFormat = new DecimalFormat("00");
            namaTF.setText(user.getString("nama_penerima"));
            telpTF.setText(user.getLong("no_telp") + "");
            alamatTF.setText(user.getString("alamat_penerima"));
            rtTF.setText(decimalFormat.format(user.getInt("RT")) + "");
            rwTF.setText(decimalFormat.format(user.getInt("RW")) + "");
            kelurahanTF.setText(user.getString("kelurahan"));
            kecamatanTF.setText(user.getString("kecamatan"));
            kotaKabupatenTF.setText(user.getString("kabupaten"));
            provinsiTF.setText(user.getString("provinsi"));
            kodePostTF.setText(user.getString("kode_pos"));
            } 
        } catch (SQLException | IOException ex) {
            Logger.getLogger(ViewCheckOut.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_formWindowActivated

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
            java.util.logging.Logger.getLogger(ViewCheckOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewCheckOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewCheckOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewCheckOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                    new ViewCheckOut().setVisible(true);
                } catch (IOException | SQLException ex) {
                    Logger.getLogger(ViewCheckOut.class.getName()).log(Level.SEVERE, null, ex);
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
                    Logger.getLogger(ViewCheckOut.class.getName()).log(Level.SEVERE, null, ex);
                }
                dispose();
            }
        });
        // Add Menu Item Into Popup Menu
            popupMenu.add (menuItem);
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
                    ViewMenuPesananSaya pesanan = new ViewMenuPesananSaya();
                    if (pesanan.produkTB.getRowCount() == 0) { // pengecek row table 0
                        new ViewMenuPesananSayaEmpty().setVisible(true);
                        dispose();
                    } else {
                        ViewLogin.getViewLogin().name = name;
                        new ViewMenuPesananSaya().setVisible(true);
                        dispose();
                    }}catch (IOException | SQLException ex) {
                    Logger.getLogger(ViewMenuUtama.class.getName()).log(Level.SEVERE, null, ex);
                }

                }
            }

            );
        // Add Menu Item Into Popup Menu
            popupMenu.add (menuItem);
        
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
                boolean yakin = JOptionPane.showOptionDialog(null, tanya, "Konfirmasi", JOptionPane.YES_NO_OPTION, 
                    JOptionPane.QUESTION_MESSAGE, null, null, null) == JOptionPane.YES_OPTION;
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
    private javax.swing.JTextField alamatTF;
    private javax.swing.JButton bayarBT;
    public static javax.swing.JTextArea catatanTA;
    private javax.swing.JLabel homeIcon;
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
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField kecamatanTF;
    private javax.swing.JTextField kelurahanTF;
    private javax.swing.JTextField kodePostTF;
    private javax.swing.JTextField kotaKabupatenTF;
    private javax.swing.JTextField namaTF;
    public static javax.swing.JLabel ongkir;
    private javax.swing.JTable produkTB;
    private javax.swing.JTextField provinsiTF;
    private javax.swing.JTextField rtTF;
    private javax.swing.JTextField rwTF;
    public static javax.swing.JLabel subtotal;
    private javax.swing.JTextField telpTF;
    public static javax.swing.JLabel total;
    private javax.swing.JLabel usernameLB;
    // End of variables declaration//GEN-END:variables
}
