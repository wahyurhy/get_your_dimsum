/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.management;

import adminForm.ViewAdminForm;
import database.infoProduk.Produk;
import database.koneksiDatabase;
import java.awt.HeadlessException;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author hp
 */
public class InputDataProduk {
    private ViewAdminForm admin;
    private Produk produk;
    
    public InputDataProduk(ViewAdminForm admin) {
        this.admin = admin;
    }
    
    public void tambahData() {
        produk = new Produk();
        
        produk.setNama(admin.getNamaTF().getText());
        produk.setHarga(admin.getHargaTF().getText());
        produk.setStok(admin.getStokTF().getText());
        produk.setBerat(admin.getBeratTF().getText());
        produk.setGambar(admin.getPathImage());
        produk.setDeskripsi(admin.getDeskripsiTA().getText());
        
        String nama_produk = produk.getNama();
        String harga_produk = produk.getHarga();
        String stok = produk.getStok();
        String berat_barang = produk.getBerat();
        String deskripsi_produk = produk.getDeskripsi();
        String gambar = produk.getGambar();
        gambar= gambar.replace("\\", "\\\\");
        
        try {
            Statement statement = koneksiDatabase.getKoneksi().createStatement();
            statement.execute("INSERT INTO informasi_produk(nama_produk, harga_produk, stok, berat_barang, deskripsi_produk, gambar) VALUES ('"+ nama_produk +"', '"+ harga_produk +"', '"+ stok +"', '"+ berat_barang +"', '"+ deskripsi_produk +"', '"+ gambar +"');");
            statement.close();

            JOptionPane.showMessageDialog(null, "Berhasil Input Data");
        } catch (HeadlessException | IOException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal Input Data :" + e + "");
        }
    }
    
    public void hapusData() {
        produk = new Produk();
        produk.setId(admin.getIdProduk());
        
        String id = admin.getIdProduk();
        
        try {
            Statement statement = koneksiDatabase.getKoneksi().createStatement();
            statement.execute("DELETE FROM informasi_produk WHERE id_produk= '"+id+"';");
            statement.close();
            
            JOptionPane.showMessageDialog(null, "Berhasil Hapus Data");
        } catch (HeadlessException | IOException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal Hapus Data");
        }
    }
    public void updateData() {
        produk = new Produk();
        produk.setId(admin.getIdProduk());
        produk.setNama(admin.getNamaTF().getText());
        produk.setHarga(admin.getHargaTF().getText());
        produk.setStok(admin.getStokTF().getText());
        produk.setBerat(admin.getBeratTF().getText());
        produk.setGambar(admin.getPathImage());
        produk.setDeskripsi(admin.getDeskripsiTA().getText());
        
        String id = admin.getIdProduk();
        String nama_produk = produk.getNama();
        String harga_produk = produk.getHarga();
        String stok = produk.getStok();
        String berat_barang = produk.getBerat();
        String deskripsi_produk = produk.getDeskripsi();
        String gambar = produk.getGambar();
        gambar = gambar.replace("\\", "\\\\");
        
        try {
            Statement statement = koneksiDatabase.getKoneksi().createStatement();
            statement.execute("UPDATE informasi_produk SET nama_produk= '"+nama_produk+"', harga_produk= '"+harga_produk+"', stok= '"+stok+"', berat_barang= '"+berat_barang+"', deskripsi_produk= '"+deskripsi_produk+"', gambar= '"+gambar+"' WHERE id_produk= '"+id+"';");
            statement.close();
            
            JOptionPane.showMessageDialog(null, "Berhasil Update Data");
        } catch (HeadlessException | IOException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal Update Data");
        }
    }
}
