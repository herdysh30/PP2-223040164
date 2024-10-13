package pertemuan2;

import java.awt.event.*;
import javax.swing.*;

public class BiodataApp extends JFrame {
	
	public BiodataApp() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel labelNama = new JLabel("Nama: ");
		labelNama.setBounds(50, 40, 100, 30);
		
		JTextField textFieldNama = new JTextField();
		textFieldNama.setBounds(150, 40, 150, 30);
		
		JLabel labelTelepon = new JLabel("No. Telepon: ");
		labelTelepon.setBounds(50, 80, 100, 30);
		
		JTextField textFieldTelepon = new JTextField();
		textFieldTelepon.setBounds(150, 80, 150, 30);
		
		JButton button = new JButton("Simpan");
		button.setBounds(150, 130, 100, 40);
		
		JTextArea txtOutput = new JTextArea("");
		txtOutput.setBounds(50, 200, 300, 200);
		txtOutput.setEditable(false);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nama = textFieldNama.getText();
				String telepon = textFieldTelepon.getText();
				
				txtOutput.append("Nama: " + nama + "\n" + 
								 "No. Telepon: " + telepon + "\n" + 
								 "======================\n");
				textFieldNama.setText("");
				textFieldTelepon.setText("");
			}
		});
		
		this.add(button);
		this.add(textFieldNama);
		this.add(labelNama);
		this.add(textFieldTelepon);
		this.add(labelTelepon);
		
		this.add(txtOutput);
		
		this.setSize(400,500);
		this.setLayout(null);
	}
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				BiodataApp app = new BiodataApp();
				app.setVisible(true);
			}
		});
	}
}
