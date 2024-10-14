/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pertemuan4;

/**
 *
 * @author Herdy
 */

import javax.swing.*;
import java.awt.event.*;

public class WindowListenerExample {
    public static void main(String[] args) {
        //Membuat frame
        JFrame frame = new JFrame ("WindowListener Example");
        //Membuat label untuk menampilkan pesan
        JLabel label = new JLabel("Lakukan operasi pada jendela.");
        label.setBounds(50,50,300,30);
    
        //Menambahkan WindowListener ke frame
        frame.addWindowListener(new WindowListener(){
        //DIjalankan ketika jendela dibuka
            public void windowOpened(WindowEvent e){
                label.setText("Window Opened.");
            }
            //DIjalankan ketika jendela sedang dalam proses penutupan
            public void windowClosing(WindowEvent e){
                label.setText("Window Closing");
                //Bisa menambahkan System.exit(0); jika ingin menghentikan program
            }
            //DIjalankan ketika jendela benar-benar ditutup
            public void windowClosed(WindowEvent e){
                System.out.println("Window Closed.");
            }
            //DIjalankan ketika jendela diminimalkan
            public void windowIconified(WindowEvent e){
                label.setText("Window Minimized");
            }
            //DIjalankan ketika jendela dipulihkan dari minimalkan
            public void windowDeiconified(WindowEvent e){
                label.setText("Window Restored.");
            }
            //DIjalankan ketika jendela menjadi aktif(berfokus)
            public void windowActivated(WindowEvent e){
                label.setText("Window Activated.");
            }
            //DIjalankan ketika jendela kehilangan fokus
            public void windowDeactivated(WindowEvent e){
                label.setText("Window Deactived.");
            }
            
    });
        
        //Menambahkan label ke frame
        frame.add(label);
        
        //Setting frame
        frame.setSize(400,200);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    
}
