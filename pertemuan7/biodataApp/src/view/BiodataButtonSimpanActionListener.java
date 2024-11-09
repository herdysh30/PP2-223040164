package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Biodata;
import dao.biodataDao;

public class BiodataButtonSimpanActionListener implements ActionListener {
    private BiodataFrame biodataFrame;
    private biodataDao biodataDao;

    public BiodataButtonSimpanActionListener(BiodataFrame biodataFrame, biodataDao biodataDao) {
        this.biodataFrame = biodataFrame;
        this.biodataDao = biodataDao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nama = biodataFrame.getNama();
        String tanggalLahir = biodataFrame.getTanggalLahir();
        String jenisKelamin = biodataFrame.getJenisKelamin();
        String noTelepon = biodataFrame.getNoTelepon();

        if (nama.isEmpty() || tanggalLahir.isEmpty() || jenisKelamin.isEmpty() || noTelepon.isEmpty()) {
            biodataFrame.showAlert("Semua field harus diisi!");
            return;
        }

        Biodata biodata = new Biodata();
        biodata.setNama(nama);
        biodata.setTanggalLahir(tanggalLahir);
        biodata.setJenisKelamin(jenisKelamin);
        biodata.setNoTelepon(noTelepon);

        if (biodataDao.insert(biodata) > 0) {
            biodataFrame.addBiodata(biodata);
            biodataFrame.showAlert("Biodata berhasil disimpan.");
        } else {
            biodataFrame.showAlert("Gagal menyimpan biodata.");
        }
    }
}
