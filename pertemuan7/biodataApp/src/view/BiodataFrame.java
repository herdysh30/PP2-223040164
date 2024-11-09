package view;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import dao.biodataDao;
import model.Biodata;

public class BiodataFrame extends JFrame {
    private List<Biodata> biodataList;
    private JTextField textFieldNama;
    private JTextField textFieldNoTelepon; // Field untuk nomor telepon
    private JSpinner spinnerTanggalLahir;
    private JRadioButton radioLaki, radioPerempuan;
    private ButtonGroup genderGroup;
    private BiodataTableModel tableModel;
    private biodataDao biodataDao;
    private JButton buttonSimpan;
    private JButton buttonUpdate;
    private JButton buttonDelete;
    private JTable table;

    public BiodataFrame(biodataDao biodataDao) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.biodataDao = biodataDao;
        this.biodataList = this.biodataDao.findAll();

        // Label Nama
        JLabel labelNama = new JLabel("Nama:");
        labelNama.setBounds(15, 20, 100, 20);
        
        // TextField Nama
        textFieldNama = new JTextField();
        textFieldNama.setBounds(15, 40, 350, 20);

        // Label Tanggal Lahir
        JLabel labelTanggalLahir = new JLabel("Tanggal Lahir:");
        labelTanggalLahir.setBounds(15, 70, 100, 20);

        // Spinner untuk Tanggal Lahir
        spinnerTanggalLahir = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinnerTanggalLahir, "yyyy-MM-dd");
        spinnerTanggalLahir.setEditor(dateEditor);
        spinnerTanggalLahir.setBounds(15, 90, 200, 20);

        // Label Jenis Kelamin
        JLabel labelJenisKelamin = new JLabel("Jenis Kelamin:");
        labelJenisKelamin.setBounds(15, 120, 100, 20);

        // RadioButton Laki-Laki
        radioLaki = new JRadioButton("Laki-Laki");
        radioLaki.setBounds(15, 140, 100, 20);

        // RadioButton Perempuan
        radioPerempuan = new JRadioButton("Perempuan");
        radioPerempuan.setBounds(120, 140, 100, 20);

        // ButtonGroup untuk Jenis Kelamin
        genderGroup = new ButtonGroup();
        genderGroup.add(radioLaki);
        genderGroup.add(radioPerempuan);

        // Label No Telepon
        JLabel labelNoTelepon = new JLabel("No Telepon:");
        labelNoTelepon.setBounds(15, 170, 100, 20);
        
        // TextField No Telepon
        textFieldNoTelepon = new JTextField();
        textFieldNoTelepon.setBounds(15, 190, 350, 20);

        // Tombol Simpan
        buttonSimpan = new JButton("Simpan");
        buttonSimpan.setBounds(15, 220, 80, 30);

        // Tombol Update
        buttonUpdate = new JButton("Update");
        buttonUpdate.setBounds(100, 220, 80, 30);
        this.add(buttonUpdate);

        // Tombol Delete
        buttonDelete = new JButton("Delete");
        buttonDelete.setBounds(185, 220, 80, 30);
        this.add(buttonDelete);

        
        // Table untuk menampilkan data
        tableModel = new BiodataTableModel(biodataList);
        table = new JTable(tableModel); // Pastikan menggunakan variabel instance 'table'
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(15, 270, 350, 200);



        // Menambahkan ActionListener ke tombol simpan
        buttonSimpan.addActionListener(new BiodataButtonSimpanActionListener(this, biodataDao));

        // Menambahkan ActionListener untuk tombol Update
        buttonUpdate.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            updateBiodata();
        }
        });

        // Menambahkan ActionListener untuk tombol Delete
        buttonDelete.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            deleteBiodata();
        }
        });

        // Tambahkan komponen ke frame
        this.add(labelNama);
        this.add(textFieldNama);
        this.add(labelTanggalLahir);
        this.add(spinnerTanggalLahir);
        this.add(labelJenisKelamin);
        this.add(radioLaki);
        this.add(radioPerempuan);
        this.add(labelNoTelepon);
        this.add(textFieldNoTelepon);
        this.add(buttonSimpan);
        this.add(scrollPane);

        // Konfigurasi frame
        this.setSize(400, 550);
        this.setLayout(null);
    }

    // Metode untuk mengambil nilai Nama dari input
    public String getNama() {
        return textFieldNama.getText();
    }

    // Metode untuk mengambil nilai Tanggal Lahir dari input dalam format String
    public String getTanggalLahir() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(spinnerTanggalLahir.getValue());
    }

    // Metode untuk mengambil nilai Jenis Kelamin dari input
    public String getJenisKelamin() {
        if (radioLaki.isSelected()) {
            return "Laki-Laki";
        } else if (radioPerempuan.isSelected()) {
            return "Perempuan";
        }
        return null; // Mengembalikan null jika tidak ada pilihan
    }

    // Metode untuk mengambil nilai No Telepon dari input
    public String getNoTelepon() {
        return textFieldNoTelepon.getText();
    }

    public void addBiodata(Biodata biodata) {
        tableModel.add(biodata);
        textFieldNama.setText("");
        textFieldNoTelepon.setText("");
        genderGroup.clearSelection();
    }

    public void showAlert(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    private void updateBiodata() {
        if (table == null) {
            showAlert("Table belum diinisialisasi.");
            return;
        }
    
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            showAlert("Pilih data yang ingin diupdate.");
            return;
        }
    
        // Mengambil biodata dari tabel berdasarkan row yang dipilih
        Biodata biodata = tableModel.getData().get(selectedRow);
    
        // Mengambil data dari input form
        String nama = getNama();
        String tanggalLahir = getTanggalLahir();
        String jenisKelamin = getJenisKelamin();
        String noTelepon = getNoTelepon();
    
        // Update data di objek Biodata
        biodata.setNama(nama);
        biodata.setTanggalLahir(tanggalLahir);
        biodata.setJenisKelamin(jenisKelamin);
        biodata.setNoTelepon(noTelepon);
    
        // Melakukan update ke database
        if (biodataDao.update(biodata) > 0) {
            showAlert("Biodata berhasil diupdate.");
            tableModel.update(selectedRow, biodata);
        } else {
            showAlert("Gagal mengupdate biodata.");
        }
    }
    
    private void deleteBiodata() {
        if (table == null) {
            showAlert("Table belum diinisialisasi.");
            return;
        }
    
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            showAlert("Pilih data yang ingin dihapus.");
            return;
        }
    
        // Konfirmasi sebelum menghapus
        int confirm = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin menghapus data ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }
    
        // Mengambil biodata dari tabel berdasarkan row yang dipilih
        Biodata biodata = tableModel.getData().get(selectedRow);
    
        // Menghapus data dari database
        if (biodataDao.delete(biodata) > 0) {
            showAlert("Biodata berhasil dihapus.");
            tableModel.remove(selectedRow);
        } else {
            showAlert("Gagal menghapus biodata.");
        }
    }
    
    
}
