package pertemuan3;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Latihan5 extends JFrame {

    public Latihan5() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Form Biodata");

        
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setSize(300, 300);
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel headerLabel = new JLabel("Form Biodata", JLabel.CENTER);
        JLabel labelNama = new JLabel("Nama:");
        JTextField textFieldNama = new JTextField(5);
        JLabel labelTelepon = new JLabel("Nomor HP:");
        JTextField textFieldTelepon = new JTextField(5);
        JLabel labelGender = new JLabel("Jenis Kelamin:");
        JRadioButton radioLaki = new JRadioButton("Laki-Laki");
        JRadioButton radioPerempuan = new JRadioButton("Perempuan");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(radioLaki);
        genderGroup.add(radioPerempuan);
        JCheckBox checkBoxWNA = new JCheckBox("Warga Negara Asing");
        JButton buttonSimpan = new JButton("Simpan");

        JTextArea txtOutput = new JTextArea(5, 20);
        txtOutput.setEditable(false);
        
        JScrollPane scrollPane = new JScrollPane(txtOutput);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(labelNama, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(textFieldNama, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(labelGender, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(radioLaki, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(radioPerempuan, gbc);
       
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(labelTelepon, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(textFieldTelepon, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(checkBoxWNA, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2; 
        gbc.fill = GridBagConstraints.NONE; 
        gbc.anchor = GridBagConstraints.CENTER; 
        panel.add(buttonSimpan, gbc);
        
        buttonSimpan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nama = textFieldNama.getText();
                String telepon = textFieldTelepon.getText();
                String gender = radioLaki.isSelected() ? "Laki-Laki" : "Perempuan";
                String wna = checkBoxWNA.isSelected() ? "Ya" : "Tidak";
                
                txtOutput.append("Nama: " + nama + "\n"
                        + "Jenis Kelamin: " + gender + "\n"
                        + "Nomor HP: " + telepon + "\n"
                        + "Warga Negara Asing: " + wna + "\n"
                        + "======================\n");
                
                textFieldNama.setText("");
                textFieldTelepon.setText("");
                genderGroup.clearSelection();
                checkBoxWNA.setSelected(false);
            }
        });
        
        this.setLayout(new GridLayout(3, 1)); 
        this.add(headerLabel); 
        this.add(panel); 
        this.add(scrollPane); 
        
        this.setSize(400, 400); 
        this.setLocationRelativeTo(null); 
        this.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Latihan5();
            }
        });
    }
}
