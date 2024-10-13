package pertemuan2;

import java.awt.event.*;
import javax.swing.*;
import java.util.Date;
import java.util.Dictionary;
import java.util.Hashtable;

public class Tugas extends JFrame {
    private boolean checkBoxSelected;

    public Tugas() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Label Nama
        JLabel labelNama = new JLabel("Nama: ");
        labelNama.setBounds(15, 20, 350, 10);

        JTextField textFieldNama = new JTextField();
        textFieldNama.setBounds(15, 40, 350, 30);

        // Label No HP
        JLabel labelTelepon = new JLabel("No. HP: ");
        labelTelepon.setBounds(15, 80, 350, 10);

        JTextField textFieldTelepon = new JTextField();
        textFieldTelepon.setBounds(15, 100, 350, 30);

        // Jenis Kelamin
        JLabel labelRadio = new JLabel("Jenis Kelamin: ");
        labelRadio.setBounds(15, 140, 350, 10);

        JRadioButton radioButton1 = new JRadioButton("Laki-Laki", true);
        radioButton1.setBounds(20, 160, 150, 20);

        JRadioButton radioButton2 = new JRadioButton("Perempuan");
        radioButton2.setBounds(20, 180, 150, 20);

        ButtonGroup bg = new ButtonGroup();
        bg.add(radioButton1);
        bg.add(radioButton2);

        // WNA Checkbox
        JCheckBox checkBox = new JCheckBox("Warga Negara Asing");
        checkBox.setBounds(10, 220, 350, 20);

        // Pilih Jenis Tabungan (JList)
        JLabel labelTabungan = new JLabel("Pilih Jenis Tabungan: ");
        labelTabungan.setBounds(400, 20, 350, 20);

        String[] jenisTabungan = {"Tabungan Reguler", "Tabungan Berjangka", "Deposito", "Tabungan Haji"};
        JList<String> listTabungan = new JList<>(jenisTabungan);
        listTabungan.setBounds(400, 40, 200, 100);
        listTabungan.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
     // Slider untuk Frekuensi Transaksi per Bulan
        JLabel labelFrekuensi = new JLabel("Frekuensi Transaksi per Bulan: ");
        labelFrekuensi.setBounds(400, 160, 350, 10);

        // Slider dari 1 hingga 100
        JSlider sliderFrekuensi = new JSlider(1, 100);
        sliderFrekuensi.setBounds(400, 180, 250, 50);
        sliderFrekuensi.setMajorTickSpacing(20);
        sliderFrekuensi.setMinorTickSpacing(5);
        sliderFrekuensi.setPaintTicks(true);
        sliderFrekuensi.setPaintLabels(true);
        
        // Custom tick labels (1, 20, 40, 60, 80, 100)
        Dictionary<Integer, JLabel> labelTable = new Hashtable<>();
        labelTable.put(1, new JLabel("1"));
        labelTable.put(20, new JLabel("20"));
        labelTable.put(40, new JLabel("40"));
        labelTable.put(60, new JLabel("60"));
        labelTable.put(80, new JLabel("80"));
        labelTable.put(100, new JLabel("100"));

        sliderFrekuensi.setLabelTable(labelTable); 

        // Password dan Confirm Password
        JLabel labelPassword = new JLabel("Password: ");
        labelPassword.setBounds(15, 260, 350, 10);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(15, 280, 350, 30);

        JLabel labelConfirmPassword = new JLabel("Confirm Password: ");
        labelConfirmPassword.setBounds(15, 320, 350, 10);

        JPasswordField confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(15, 340, 350, 30);

        // Spinner untuk Tanggal Lahir
        JLabel labelTanggalLahir = new JLabel("Tanggal Lahir: ");
        labelTanggalLahir.setBounds(400, 240, 350, 20);

        SpinnerModel dateModel = new SpinnerDateModel();
        JSpinner spinnerTanggalLahir = new JSpinner(dateModel);
        spinnerTanggalLahir.setBounds(400, 260, 150, 30);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinnerTanggalLahir, "dd/MM/yyyy");
        spinnerTanggalLahir.setEditor(dateEditor);
 
        // Output Area
        JTextArea txtOutput = new JTextArea("");
        txtOutput.setBounds(15, 420, 750, 100);
        txtOutput.setEditable(false);

        // Button Simpan
        JButton button = new JButton("Simpan");
        button.setBounds(15, 380, 100, 30);

        checkBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                checkBoxSelected = e.getStateChange() == ItemEvent.SELECTED;
            }
        });

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Validasi input
                String nama = textFieldNama.getText();
                String telepon = textFieldTelepon.getText();
                String jenisKelamin = radioButton1.isSelected() ? radioButton1.getText() : radioButton2.getText();
                String jenisTabunganDipilih = listTabungan.getSelectedValue();
                int frekuensi = (Integer) sliderFrekuensi.getValue();
                Date tanggalLahir = (Date) spinnerTanggalLahir.getValue();

                if (nama.isEmpty() || telepon.isEmpty() || jenisTabunganDipilih == null) {
                    JOptionPane.showMessageDialog(null, "Harap mengisi semua data yang diperlukan!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validasi password
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());

                if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(null, "Password dan Confirm Password tidak cocok!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Output ke JTextArea
                txtOutput.append("Nama          : " + nama + "\n" +
                                 "No. Telepon   : " + telepon + "\n" +
                                 "Jenis Kelamin : " + jenisKelamin + "\n" +
                                 "WNA           : " + (checkBoxSelected ? "Ya" : "Bukan") + "\n" +
                                 "Jenis Tabungan: " + jenisTabunganDipilih + "\n" +
                                 "Frekuensi Transaksi per Bulan: " + frekuensi + "\n" +
                                 "Tanggal Lahir : " + dateEditor.getFormat().format(tanggalLahir) + "\n" +
                                 "======================\n");
            }
        });

        // MenuBar dan Menu untuk Reset dan Exit
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem menuItemReset = new JMenuItem("Reset");
        JMenuItem menuItemExit = new JMenuItem("Exit");

        menuItemReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textFieldNama.setText("");
                textFieldTelepon.setText("");
                bg.clearSelection();
                radioButton1.setSelected(true); 
                checkBox.setSelected(false);
                listTabungan.clearSelection();
                sliderFrekuensi.setValue(1);
                passwordField.setText("");
                confirmPasswordField.setText("");
                spinnerTanggalLahir.setValue(new Date());
                txtOutput.setText("");
            }
        });

        menuItemExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menu.add(menuItemReset);
        menu.add(menuItemExit);
        menuBar.add(menu);
        this.setJMenuBar(menuBar);

        //Komponen JFrame
        this.add(button);
        this.add(textFieldNama);
        this.add(labelNama);
        this.add(textFieldTelepon);
        this.add(radioButton1);
        this.add(radioButton2);
        this.add(labelTelepon);
        this.add(labelRadio);
        this.add(checkBox);
        this.add(listTabungan);
        this.add(labelTabungan);
        this.add(sliderFrekuensi);
        this.add(labelFrekuensi);
        this.add(passwordField);
        this.add(labelPassword);
        this.add(confirmPasswordField);
        this.add(labelConfirmPassword);
        this.add(labelTanggalLahir);
        this.add(spinnerTanggalLahir);
        this.add(txtOutput);

        // Pengaturan JFrame
        this.setSize(800, 600);
        this.setLayout(null);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Tugas app = new Tugas();
                app.setVisible(true);
            }
        });
    }
}
