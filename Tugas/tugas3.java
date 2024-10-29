/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tugas;

/**
 *
 * @author Herdy
 * 
 */
import java.awt.event.*;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
import java.util.Dictionary;
import java.util.Hashtable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;


public class tugas3 {
    private boolean checkBoxSelected;
    private DefaultTableModel tableModel;
    private DefaultListModel<String> listModel;
    public tugas3(){
        JFrame frame = new JFrame("Manajemen Keuangan Pribadi");
        
        JLabel labelTransaksi = new JLabel("Transaksi :");
        labelTransaksi.setBounds(15,40,150,20);
        JTextField textFieldTransaksi = new JTextField();
        textFieldTransaksi.setBounds(150, 40, 200, 30);
        
        //Konfigurasi kategori
        JLabel labelKategori = new JLabel("Kategori Transaksi :");
        labelKategori.setBounds(15,80,150,30);
        String[] jenisKategori = {"Makan", "Transportasi", "Gaya Hidup", "Bermain", "Gaji", "Lain-lain"};
        JComboBox<String> comboBox = new JComboBox<>(jenisKategori);
        comboBox.setBounds(150,80,200,30);
        
        //Konfigurasi jenis transkasi
        JLabel labelRadio = new JLabel("Jenis Transaksi :");
        labelRadio.setBounds(15,120,150,20);
        JRadioButton radioButton1 = new JRadioButton("Pengeluaran",true);
        radioButton1.setBounds(150,120,100,20);
        JRadioButton radioButton2 = new JRadioButton("Pemasukan");
        radioButton2.setBounds(250,120,100,20);
        ButtonGroup bg = new ButtonGroup();
        bg.add(radioButton1);
        bg.add(radioButton2);
        
        //Konfigurasi Tanggal Transaksi
        JLabel labelTanggalTransaksi = new JLabel("Tanggal Transaksi :");
        labelTanggalTransaksi.setBounds(15,160,150,20);
        SpinnerModel dateModel = new SpinnerDateModel ();
        JSpinner spinnerTanggalTransaksi = new JSpinner(dateModel);
        spinnerTanggalTransaksi.setBounds(150,160,150,25);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinnerTanggalTransaksi, "dd/MM/yyyy");
        spinnerTanggalTransaksi.setEditor(dateEditor);
        
        //Label Jumlah uang
        JLabel labelJumlahUang = new JLabel("Jumlah Uang :");
        labelJumlahUang.setBounds(15, 200, 150, 20);
        JLabel labelRupiah = new JLabel("Rp.");
        labelRupiah.setBounds(120, 200, 150, 20);
        JTextField fieldJumlahUang = new JTextField();
        fieldJumlahUang.setBounds(150, 200, 200, 30);

        // Konfigurasi JSlider untuk jumlah uang
        JSlider sliderUang = new JSlider(1000, 1000000);
        sliderUang.setBounds(145, 230, 210, 50);
        sliderUang.setMajorTickSpacing(499000);
        sliderUang.setMinorTickSpacing(100000);
        sliderUang.setPaintTicks(true);
        sliderUang.setPaintLabels(true);

        // Label khusus pada titik 1000, 500000, dan 1000000
        Dictionary<Integer, JLabel> labelTable = new Hashtable<>();
        labelTable.put(1000, new JLabel("1.000"));
        labelTable.put(500000, new JLabel("500.000"));
        labelTable.put(1000000, new JLabel("1.000.000"));
        sliderUang.setLabelTable(labelTable);
        
        // Membuat DocumentFilter untuk membatasi input hanya angka
        ((AbstractDocument) fieldJumlahUang.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (string.matches("\\d+")) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (text.matches("\\d+")) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });

        // Update fieldJumlahUang saat slider berubah
        sliderUang.addChangeListener(e -> fieldJumlahUang.setText(String.valueOf(sliderUang.getValue())));

        // Update slider saat fieldJumlahUang berubah
        fieldJumlahUang.addActionListener(e -> {
            try {
                int value = Integer.parseInt(fieldJumlahUang.getText());
                if (value >= 1000 && value <= 1000000) {
                    sliderUang.setValue(value);
                } else {
                    JOptionPane.showMessageDialog(frame, "Masukkan jumlah antara 1.000 dan 1.000.000.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Masukkan angka yang valid.");
            }
        });
        
        //checkbox
        JCheckBox checkBox = new JCheckBox("Bulanan");
        checkBox.setBounds(150, 280, 150, 30);
        checkBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                checkBoxSelected = e.getStateChange() == ItemEvent.SELECTED;
            }
        });
        
        //Output
        JTextArea txtOutput = new JTextArea();
        txtOutput.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtOutput);
        scrollPane.setBounds(400, 40, 350, 300);
        
        // Button Tambah Transkasi
        JButton button = new JButton("Tambah Transaksi");
        button.setBounds(150, 330, 200, 30);
        
        // Model untuk JTable
        tableModel = new DefaultTableModel(new String[]{"Transaksi", "Kategori", "Jenis", "Tanggal", "Jumlah", "Bulanan"}, 0);
        JTable table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        
        // Filter JList
        listModel = new DefaultListModel<>(); // Initialize the model for JList
        JList<String> categoryList = new JList<>(listModel);
        categoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane listScrollPane = new JScrollPane(categoryList);
        listModel.addElement("All");
        for (String category : jenisKategori) {
            listModel.addElement(category);
        }
        
        button.addActionListener(e -> {
            String transaksi = textFieldTransaksi.getText();
            String kategori = comboBox.getSelectedItem().toString();
            String jenis = radioButton1.isSelected() ? "Pengeluaran" : "Pemasukan";
            Date tanggal = (Date) spinnerTanggalTransaksi.getValue();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String tanggalStr = dateFormat.format(tanggal);
            String jumlah = fieldJumlahUang.getText();
            String bulanan = checkBoxSelected ? "Ya" : "Tidak";
            
            if (transaksi.isEmpty() || kategori == null || jumlah.isEmpty()){
                JOptionPane.showMessageDialog(null, "Harap mengisi semua data yang diperlukan!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
            }

           txtOutput.append( "Transaksi Berhasil Ditambahkan!" + "\n"
                  +"Transaksi: " + transaksi + "\n"
                  + "Kategori: " + kategori + "\n"
                  + "Jenis: " + jenis + "\n"
                  + "Tanggal: " + tanggalStr + "\n"
                  + "Jumlah: Rp." + jumlah + "\n"
                  + "Bulanan: " + bulanan + "\n"
                  + "======================\n");
           
           tableModel.addRow(new Object[]{transaksi, kategori, jenis, tanggalStr, "Rp." + jumlah, bulanan});
        });
        
        //Menubar
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("File");
        JMenuItem menuItemReset = new JMenuItem("Reset");
        JMenuItem menuItemExit = new JMenuItem("Exit");
        
        menuItemReset.addActionListener(e -> {
            textFieldTransaksi.setText("");
            comboBox.setSelectedIndex(0);
            radioButton1.setSelected(true);
            spinnerTanggalTransaksi.setValue(new Date()); 
            fieldJumlahUang.setText("");
            sliderUang.setValue(1000); 
            checkBox.setSelected(false); 
            txtOutput.setText(""); 
        });

        menuItemExit.addActionListener(e -> System.exit(0));
        
        menuFile.add(menuItemReset);
        menuFile.add(menuItemExit);
        menuBar.add(menuFile);
        
        JMenu menuData = new JMenu("Data");
        JMenuItem menuItemViewData = new JMenuItem("Tampilkan Data");
        
        menuItemViewData.addActionListener(e -> {
            JFrame dataFrame = new JFrame("Data Transaksi");
            dataFrame.setSize(800, 400);
            dataFrame.setLayout(null);
            
            // JList Filter
            listScrollPane.setBounds(10, 10, 200, 300);
            dataFrame.add(listScrollPane);
            
            // JTable untuk menampilkan data
            tableScrollPane.setBounds(220, 10, 500, 300);
            dataFrame.add(tableScrollPane);
            
            dataFrame.setVisible(true);
        });

        menuData.add(menuItemViewData);
        menuBar.add(menuData);

        frame.setJMenuBar(menuBar);
        
        // After creating your table model and table
        TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(rowSorter);

        // Add List Selection Listener to JList
        categoryList.addListSelectionListener(new ListSelectionListener() {
         @Override
         public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) { // Check if selection is final
                String selectedCategory = categoryList.getSelectedValue();
                if (selectedCategory != null) {
                     if (selectedCategory.equals("All")) {
                        // Clear the filter to show all transactions
                        rowSorter.setRowFilter(null);
                     } else {
                    // Filter the JTable based on selected category
                    rowSorter.setRowFilter(RowFilter.regexFilter(selectedCategory, 1)); // 1 is the index of the category column
                    }   
                }
            }
         }
        });

        
        // Menambahkan komponen ke frame
        frame.add(labelTransaksi);
        frame.add(textFieldTransaksi);
        frame.add(labelKategori);
        frame.add(comboBox);
        frame.add(labelRadio);
        frame.add(radioButton1);
        frame.add(radioButton2);
        frame.add(labelTanggalTransaksi);
        frame.add(spinnerTanggalTransaksi);
        frame.add(labelJumlahUang);
        frame.add(labelRupiah);
        frame.add(fieldJumlahUang);
        frame.add(sliderUang);
        frame.add(checkBox);
        frame.add(button);
        frame.add(scrollPane);

        frame.setLayout(null);
        frame.setSize(800, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                tugas3 app = new tugas3();
                
            }
        });
    }
}
