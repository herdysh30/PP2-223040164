package view.jenismember;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.JenisMember;
import dao.JenisMemberDao;

public class JenisMemberButtonDeleteActionListener implements ActionListener {
    private JenisMemberFrame jenisMemberFrame;
    private JenisMemberDao jenisMemberDao;

    public JenisMemberButtonDeleteActionListener(JenisMemberFrame jenisMemberFrame, JenisMemberDao jenisMemberDao) {
        this.jenisMemberFrame = jenisMemberFrame;
        this.jenisMemberDao = jenisMemberDao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedRow = jenisMemberFrame.getTable().getSelectedRow();
        if (selectedRow == -1) {
            jenisMemberFrame.showAlert("Pilih jenis member yang ingin dihapus!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
            jenisMemberFrame,
            "Apakah Anda yakin ingin menghapus jenis member ini?",
            "Konfirmasi Hapus",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            JenisMember selectedJenisMember = jenisMemberFrame.getTableModel().get(selectedRow);
            jenisMemberFrame.getTableModel().remove(selectedRow);
            jenisMemberDao.delete(selectedJenisMember);
            jenisMemberFrame.showAlert("Jenis Member berhasil dihapus!");
        }
    }
}
