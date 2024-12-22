package controller;

import view.UserView;
import javax.swing.*;
import model.User;
import model.UserMapper;
import view.UserPdf;
import org.apache.ibatis.session.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserController {
    private UserView view;
    private UserMapper mapper;
    private UserPdf pdf;
    private SqlSession session;

    public UserController(UserView view, UserMapper mapper, UserPdf pdf, SqlSession session) {
        this.view = view;
        this.mapper = mapper;
        this.pdf = pdf;
        this.session = session;

        this.view.addAddUserListener(new AddUserListener());
        this.view.addRefreshUserListener(new RefreshListener());
        this.view.addExportListener(new ExportListener());
    }

    class AddUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = view.getNameInput();
            String email = view.getEmailInput();
            if (!name.isEmpty() && !email.isEmpty()) {
                // Disable tombol untuk mencegah klik ganda
                view.getAddButton().setEnabled(false);
                view.setStatus("Menambahkan pengguna...");
                view.showProgressBar(true); 
                view.resetProgressBar(); 

                // Gunakan SwingWorker untuk menjalankan operasi database di thread terpisah
                SwingWorker<Void, Integer> worker = new SwingWorker<>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        // Menambahkan user ke database
                        User user = new User();
                        user.setName(name);
                        user.setEmail(email);

                        for (int i = 0; i <= 100; i++) {
                            Thread.sleep(50); 
                            publish(i); 
                        }

                        try {
                            mapper.insertUser(user);
                            session.commit();
                        } catch (Exception ex) {
                            session.rollback();
                            throw new RuntimeException("Error inserting user", ex);
                        }
                        return null;
                    }

                    @Override
                    protected void process(List<Integer> chunks) {
                        int progress = chunks.get(chunks.size() - 1);
                        view.setProgress(progress);
                    }

                    @Override
                    protected void done() {
                        view.getAddButton().setEnabled(true);
                        view.setStatus("Pengguna berhasil ditambahkan!");
                        view.showProgressBar(false); 
                    }
                };

                worker.execute();
            } else {
                JOptionPane.showMessageDialog(view, "Please fill in all fields");
            }
        }
    }

    class RefreshListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Disable tombol refresh sementara
            view.getRefreshButton().setEnabled(false);
            view.setStatus("Menyegarkan data...");
            view.showProgressBar(true); 
            view.resetProgressBar(); 

            // Gunakan SwingWorker untuk menjalankan refresh di thread terpisah
            SwingWorker<Void, String[]> worker = new SwingWorker<>() {
                @Override
                protected Void doInBackground() throws Exception {
                    List<User> users = mapper.getAllUsers();
                    String[] userArray = users.stream()
                            .map(u -> u.getName() + " (" + u.getEmail() + ")")
                            .toArray(String[]::new);

                    for (int i = 0; i < userArray.length; i++) {
                        Thread.sleep(50); 
                        publish(userArray); 
                    }

                    return null;
                }

                @Override
                protected void process(List<String[]> chunks) {
                    // Mengupdate UI dengan daftar pengguna
                    view.setUserList(chunks.get(chunks.size() - 1));
                    int progress = (int) ((chunks.size() * 100) / 100); 
                    view.setProgress(progress);
                }

                @Override
                protected void done() {
                    // Setelah selesai, aktifkan kembali tombol dan perbarui status
                    view.getRefreshButton().setEnabled(true);
                    view.setStatus("Data berhasil disegarkan!");
                    view.showProgressBar(false); 
                }
            };

            worker.execute();
        }
    }

    class ExportListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Disable tombol export saat proses ekspor sedang berjalan
            view.getExportButton().setEnabled(false);
            view.setStatus("Mengekspor data...");
            view.showProgressBar(true); 
            view.resetProgressBar(); 

            // Gunakan SwingWorker untuk menjalankan ekspor di thread terpisah
            SwingWorker<Void, Integer> worker = new SwingWorker<>() {
                @Override
                protected Void doInBackground() throws Exception {
                    List<User> users = mapper.getAllUsers();
                    for (int i = 0; i <= 100; i++) {
                        Thread.sleep(50);
                        publish(i); 
                    }
                    pdf.exportPdf(users); // Ekspor PDF
                    return null;
                }

                @Override
                protected void process(List<Integer> chunks) {
                    // Update progress bar incrementally
                    if (!chunks.isEmpty()) {
                        int progress = chunks.get(chunks.size() - 1);
                        view.setProgress(progress);
                    }
                }

                @Override
                protected void done() {
                    // Setelah ekspor selesai, aktifkan kembali tombol dan perbarui status
                    view.getExportButton().setEnabled(true);
                    view.setStatus("Data berhasil diekspor!");
                    view.showProgressBar(false); 
                }
            };

            worker.execute();
        }
    }

}
