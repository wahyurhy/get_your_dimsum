/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belumKonek;

import database.koneksiDatabase;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class BelumKonekMySQL {
    
   public static boolean belumTerkoneksi = false;
        
    public static void pesanError() throws ClassNotFoundException {
            JOptionPane.showMessageDialog(null, "Gagal koneksi ke server MySQL", "Error SQL", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
    }
    public static boolean belumTerkoneksi() throws ClassNotFoundException, IOException, FileNotFoundException, SQLException {
        if(koneksiDatabase.getKoneksi() == null) {
            belumTerkoneksi = true; }
        
        return belumTerkoneksi;
        
    }
    }