package controller;

import view.CustomerView;
import model.Customer;
import model.CustomerMapper;
import org.apache.ibatis.session.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

public class CustomerController {
    private CustomerView view;
    private CustomerMapper mapper;
    private SqlSession session;

    public CustomerController(CustomerView view, CustomerMapper mapper, SqlSession session) {
        this.view = view;
        this.mapper = mapper;
        this.session = session;

        view.addAddListener(new AddCustomerListener());
        view.addUpdateListener(new UpdateCustomerListener());
        view.addDeleteListener(new DeleteCustomerListener());
        view.addRefreshListener(new RefreshCustomerListener());

        refreshCustomerList();
    }

    class AddCustomerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nama = view.getNamaInput();
            String email = view.getEmailInput();
            String noTelp = view.getNoTelpInput();
            String alamat = view.getAlamatInput();

            if (!nama.isEmpty() && !email.isEmpty() && !noTelp.isEmpty() && !alamat.isEmpty()) {
                Customer customer = new Customer();
                customer.setNama(nama);
                customer.setEmail(email);
                customer.setNoTelp(noTelp);
                customer.setAlamat(alamat);

                mapper.insertUser(customer);
                session.commit();
                JOptionPane.showMessageDialog(view, "Customer added successfully!");

                refreshCustomerList();
            } else {
                JOptionPane.showMessageDialog(view, "Please fill in all fields.");
            }
        }
    }

    class UpdateCustomerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedId = view.getSelectedCustomerId();
            if (selectedId != -1) {
                String nama = view.getNamaInput();
                String email = view.getEmailInput();
                String noTelp = view.getNoTelpInput();
                String alamat = view.getAlamatInput();

                if (!nama.isEmpty() && !email.isEmpty() && !noTelp.isEmpty() && !alamat.isEmpty()) {
                    Customer customer = new Customer();
                    customer.setId(selectedId);
                    customer.setNama(nama);
                    customer.setEmail(email);
                    customer.setNoTelp(noTelp);
                    customer.setAlamat(alamat);

                    mapper.updateUser(customer); 
                    session.commit();
                    JOptionPane.showMessageDialog(view, "Customer updated successfully!");

                    refreshCustomerList();
                } else {
                    JOptionPane.showMessageDialog(view, "Please fill in all fields.");
                }
            } else {
                JOptionPane.showMessageDialog(view, "Please select a customer to update.");
            }
        }
    }

    class DeleteCustomerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedId = view.getSelectedCustomerId();
            if (selectedId != -1) {
                int confirm = JOptionPane.showConfirmDialog(view, "Are you sure you want to delete this customer?");
                if (confirm == JOptionPane.YES_OPTION) {
                    mapper.deleteUser(selectedId); 
                    session.commit();
                    JOptionPane.showMessageDialog(view, "Customer deleted successfully!");

                    refreshCustomerList();
                }
            } else {
                JOptionPane.showMessageDialog(view, "Please select a customer to delete.");
            }
        }
    }

    class RefreshCustomerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            refreshCustomerList();
        }
    }

    private void refreshCustomerList() {
        List<Customer> customers = mapper.getAllUsers();
        Object[][] customerData = new Object[customers.size()][5];
        for (int i = 0; i < customers.size(); i++) {
            customerData[i][0] = customers.get(i).getId();
            customerData[i][1] = customers.get(i).getNama();
            customerData[i][2] = customers.get(i).getEmail();
            customerData[i][3] = customers.get(i).getNoTelp();
            customerData[i][4] = customers.get(i).getAlamat();
        }
        view.setCustomerList(customerData);
    }
}
