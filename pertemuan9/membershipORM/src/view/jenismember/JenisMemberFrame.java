package view.jenismember;  

import javax.swing.JButton;  
import javax.swing.JFrame;  
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;  
import javax.swing.JTable;  
import javax.swing.JTextField;  
import java.util.List;  
import model.JenisMember;
import view.member.MemberButtonDeleteActionListener;
import view.member.MemberButtonUpdateActionListener;
import dao.JenisMemberDao;  

public class JenisMemberFrame extends JFrame {  
    private List<JenisMember> jenisMemberList;  
    private JTextField textFieldNama;  
    private JenisMemberTableModel tableModel;  
    private JenisMemberDao jenisMemberDao;  
    private JTable table;

    public JenisMemberFrame(JenisMemberDao jenisMemberDao) {  
        this.jenisMemberDao = jenisMemberDao;  
        this.jenisMemberList = jenisMemberDao.findAll();  
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  

        JLabel labelInput = new JLabel("Nama:");  
        labelInput.setBounds(15, 40, 350, 10);  

        textFieldNama = new JTextField();  
        textFieldNama.setBounds(15, 60, 350, 20);  

        JButton button = new JButton("Simpan");  
        button.setBounds(15, 100, 100, 40);  

        JButton buttonDelete = new JButton("Delete");
        buttonDelete.setBounds(120,100,100,40);
        
        JButton buttonUpdate = new JButton("Update");
        buttonUpdate.setBounds(230,100,100,40);

        table = new JTable();  
        JScrollPane scrollableTable = new JScrollPane(table);  
        scrollableTable.setBounds(15, 150, 350, 200);  

        tableModel = new JenisMemberTableModel(jenisMemberList);  
        table.setModel(tableModel);  

        JenisMemberButtonSimpanActionListener actionListener = new JenisMemberButtonSimpanActionListener(this, jenisMemberDao);  
        button.addActionListener(actionListener);  
        buttonUpdate.addActionListener(new JenisMemberButtonUpdateActionListener(this, jenisMemberDao));
        buttonDelete.addActionListener(new JenisMemberButtonDeleteActionListener(this, jenisMemberDao));

        this.add(button);  
        this.add(buttonDelete);
        this.add(buttonUpdate);
        this.add(textFieldNama);  
        this.add(labelInput);  
        this.add(scrollableTable);  

        this.setSize(400, 500);  
        this.setLayout(null);  
    }  

    public String getNama() {  
        return textFieldNama.getText();  
    }  

    public void addJenisMember(JenisMember jenisMember) {  
        tableModel.add(jenisMember);  
        textFieldNama.setText("");  
    }  
    public void showAlert(String message) {  
        JOptionPane.showMessageDialog(this, message);  
    }  

    public JTable getTable() {
        return table;
    }
    
    public JenisMemberTableModel getTableModel() {
        return tableModel;
    }
}