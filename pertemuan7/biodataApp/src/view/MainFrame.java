package view;

import javax.swing.*;
import dao.biodataDao;
import view.BiodataFrame;

public class MainFrame extends JFrame {
    private JButton buttonBiodata;
    private biodataDao biodataDao;
    private BiodataFrame biodataFrame;

    public MainFrame() {
        // Konfigurasi dasar frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 200);
        this.setLayout(null);

        // Inisialisasi DAO
        this.biodataDao = new biodataDao();

        // Buat tombol untuk membuka frame Biodata
        buttonBiodata = new JButton("Buka Biodata");
        buttonBiodata.setBounds(100, 70, 200, 30);
        buttonBiodata.addActionListener(e -> showBiodataFrame());

        // Tambahkan tombol ke frame
        this.add(buttonBiodata);
    }

    private void showBiodataFrame() {
        if (biodataFrame == null) {
            biodataFrame = new BiodataFrame(biodataDao);
        }
        biodataFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}
