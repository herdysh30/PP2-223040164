package pertemuan2;

import java.awt.event.*;
import javax.swing.*;

public class Latihan7 extends JFrame {
	private boolean checkBoxSelected;
	
	public Latihan7() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel labelNama = new JLabel("Nama: ");
		labelNama.setBounds(15, 40, 350, 10);
		
		JTextField textFieldNama = new JTextField();
		textFieldNama.setBounds(15, 70, 350, 30);
		
		JLabel labelTelepon = new JLabel("No. HP: ");
		labelTelepon.setBounds(15, 110, 350, 10);
		
		JTextField textFieldTelepon = new JTextField();
		textFieldTelepon.setBounds(15, 130, 350, 30);
		
		JLabel labelRadio = new JLabel("Jenis Kelamin: ");
		labelRadio.setBounds(15,170,350,10);
		
		JRadioButton radioButton1 = new JRadioButton("Laki-Laki", true);
		radioButton1.setBounds(20,190,350,20);
		
		JRadioButton radioButton2 = new JRadioButton("Perempuan");
		radioButton2.setBounds(20,220,350,20);
		
		JCheckBox checkBox = new JCheckBox("Warga Negara Asing");
		checkBox.setBounds(20,250,350,20);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(radioButton1);
		bg.add(radioButton2);
		
		JButton button = new JButton("Simpan");
		button.setBounds(15, 280, 100, 40);
		
		JTextArea txtOutput = new JTextArea("");
		txtOutput.setBounds(10, 340, 950, 100);
		txtOutput.setEditable(false);
		
		checkBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				checkBoxSelected = e.getStateChange()==1;
			}
		});
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String jenisKelamin = "";
				if(radioButton1.isSelected()) {
					jenisKelamin = radioButton1.getText();
				}
				if(radioButton2.isSelected()) {
					jenisKelamin = radioButton2.getText();
				}
				
				String nama = textFieldNama.getText();
				String telepon = textFieldTelepon.getText();
				String wna = "Bukan";
				if(checkBoxSelected) {
					wna = "Ya";
				}
				txtOutput.append("Nama          : " + nama + "\n" + 
								 "No. Telepon   : " + telepon + "\n" + 
								 "Jenis Kelamin : "+jenisKelamin+"\n"+
								 "WNA           : "+wna+"\n"+
								 "======================\n");
				textFieldNama.setText("");
				textFieldTelepon.setText("");
			}
		});
		
		this.add(button);
		this.add(textFieldNama);
		this.add(labelNama);
		this.add(textFieldTelepon);
		this.add(radioButton1);
		this.add(radioButton2);
		this.add(labelTelepon);
		this.add(labelRadio);
		this.add(checkBox);
		this.add(txtOutput);
		
		this.setSize(1000, 500);
		this.setLayout(null);
	}
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Latihan7 app = new Latihan7();
				app.setVisible(true);
			}
		});
	}

}
