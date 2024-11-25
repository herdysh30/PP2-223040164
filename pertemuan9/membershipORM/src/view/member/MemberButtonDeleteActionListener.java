package view.member;

import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import java.util.UUID;  
import model.JenisMember;  
import model.Member;  
import dao.MemberDao;  
import javax.swing.JOptionPane;



public class MemberButtonDeleteActionListener implements ActionListener {
    private MemberFrame memberFrame;
    private MemberDao memberDao;

    public MemberButtonDeleteActionListener(MemberFrame memberFrame, MemberDao memberDao) {
        this.memberFrame = memberFrame;
        this.memberDao = memberDao;
    }

    @Override
public void actionPerformed(ActionEvent e) {
    int selectedRow = memberFrame.getTable().getSelectedRow();
    if (selectedRow == -1) {
        memberFrame.showAlert("Pilih member yang ingin dihapus!");
        return;
    }

    int confirm = JOptionPane.showConfirmDialog(
        memberFrame,
        "Apakah Anda yakin ingin menghapus member ini?",
        "Konfirmasi Hapus",
        JOptionPane.YES_NO_OPTION
    );

    if (confirm == JOptionPane.YES_OPTION) {
        Member selectedMember = memberFrame.getTableModel().get(selectedRow);
        memberFrame.getTableModel().remove(selectedRow); 
        memberDao.delete(selectedMember); 
        memberFrame.showAlert("Member berhasil dihapus!");
    }
}
}

