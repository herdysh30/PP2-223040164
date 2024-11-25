package view.member;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.JenisMember;
import model.Member;
import dao.MemberDao;

public class MemberButtonUpdateActionListener implements ActionListener {
    private MemberFrame memberFrame;
    private MemberDao memberDao;

    public MemberButtonUpdateActionListener(MemberFrame memberFrame, MemberDao memberDao) {
        this.memberFrame = memberFrame;
        this.memberDao = memberDao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedRow = memberFrame.getTable().getSelectedRow();
        if (selectedRow == -1) {
            memberFrame.showAlert("Pilih member yang ingin diupdate!");
            return;
        }

        String nama = memberFrame.getNama();
        if (nama.isEmpty()) {
            memberFrame.showAlert("Nama tidak boleh kosong!");
            return;
        }

        Member selectedMember = memberFrame.getTableModel().get(selectedRow);

        selectedMember.setNama(nama);
        
        JenisMember selectedJenisMember = memberFrame.getJenisMember();
        if (selectedJenisMember != null) {
            selectedMember.setJenisMember(selectedJenisMember);
            selectedMember.setJenisMemberId(selectedJenisMember.getId());
        } else {
            memberFrame.showAlert("Jenis Member tidak valid!");
            return;
        }

        memberFrame.getTableModel().update(selectedRow, selectedMember);
        memberDao.update(selectedMember);

        memberFrame.showAlert("Member berhasil diupdate!");
    }
}
