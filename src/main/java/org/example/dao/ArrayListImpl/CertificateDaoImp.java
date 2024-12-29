package org.example.dao.ArrayListImpl;

import org.example.dao.IDao;
import org.example.model.Act;
import org.example.model.Certificate;

import java.util.ArrayList;
import java.util.List;

public class CertificateDaoImp implements IDao<Certificate,Long> {
    private final List<Certificate> certificates = new ArrayList<Certificate>();

    @Override
    public List<Certificate> getAll() throws Exception {
        if (!certificates.isEmpty()) {
            return certificates;
        }
        throw new Exception("No Certificate found");
    }

    @Override
    public Certificate getById(Long ID) throws Exception {
        return certificates.stream()
                .filter(certificate -> ID.equals(certificate.getID()) )
                .findFirst()
                .orElseThrow(()-> new Exception("No Certificate found with ID: " + ID));
    }

    @Override
    public Certificate add(Certificate certificate) throws Exception {
        if (certificate != null) {
            certificates.add(certificate);
            return certificate;
        }
        throw new Exception("No Certificate Been Added");
    }

    @Override
    public Certificate update(Certificate certificate) throws Exception {
        Certificate existingCertificate = getById(certificate.getID());
        if (existingCertificate != null) {
            existingCertificate.setEndDate(certificate.getEndDate());
            existingCertificate.setReason(certificate.getReason());
            existingCertificate.setStartDate(certificate.getStartDate());
            return existingCertificate;
        }
        throw new Exception("No Certificate Been Updated");
    }

    @Override
    public void delete(Long ID) throws Exception {
        Certificate existingCertificate = this.getById(ID);

        certificates.remove(existingCertificate);
    }
}
