package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

public class CustomerView extends JFrame {
    private JTextField txtNama = new JTextField(20);
    private JTextField txtEmail = new JTextField(20);
    private JTextField txtNoTelp = new JTextField(20);
    private JTextField txtAlamat = new JTextField(20);
    private JButton btnAdd = new JButton("Add Customer");
    private JButton btnRefresh = new JButton("Refresh");
    private JButton btnUpdate = new JButton("Update");
    private JButton btnDelete = new JButton("Delete");
    private JTable customerTable;
    private DefaultTableModel tableModel;

    public CustomerView() {
        setTitle("Customer Management");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("Name:"));
        panel.add(txtNama);
        panel.add(new JLabel("Email:"));
        panel.add(txtEmail);
        panel.add(new JLabel("Phone Number:"));
        panel.add(txtNoTelp);
        panel.add(new JLabel("Address:"));
        panel.add(txtAlamat);
        panel.add(btnAdd);
        panel.add(btnUpdate);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnDelete);

        String[] columnNames = {"ID", "Name", "Email", "Phone", "Address"};
        tableModel = new DefaultTableModel(columnNames, 0);
        customerTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(customerTable);
        
        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public String getNamaInput() {
        return txtNama.getText();
    }

    public String getEmailInput() {
        return txtEmail.getText();
    }

    public String getNoTelpInput() {
        return txtNoTelp.getText();
    }

    public String getAlamatInput() {
        return txtAlamat.getText();
    }

    public void setCustomerList(Object[][] customerData) {
        tableModel.setRowCount(0); 
        for (Object[] customer : customerData) {
            tableModel.addRow(customer);
        }
    }

    public void addAddListener(ActionListener listener) {
        btnAdd.addActionListener(listener);
    }

    public void addUpdateListener(ActionListener listener) {
        btnUpdate.addActionListener(listener);
    }

    public void addDeleteListener(ActionListener listener) {
        btnDelete.addActionListener(listener);
    }

    public void addRefreshListener(ActionListener listener) {
        btnRefresh.addActionListener(listener);
    }

    public int getSelectedCustomerId() {
        int selectedRow = customerTable.getSelectedRow();
        if (selectedRow != -1) {
            return (int) customerTable.getValueAt(selectedRow, 0); 
        }
        return -1;
    }
}