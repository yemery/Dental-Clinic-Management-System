package org.example.service.implementation;

import org.example.dao.IDao;
import org.example.dao.implementation.CertificateDaoImp;
import org.example.model.Certificate;
import org.example.service.api.CertificateService;

import java.util.List;

public class CertificateServiceImpl implements CertificateService {
    private  final IDao<Certificate,Long> dao = new CertificateDaoImp();

    @Override
    public Certificate addCertificate(Certificate certificate) {
        try{

            Certificate cer= dao.add(certificate);
            System.out.println("Certificate added");
            return cer;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Certificate updateCertificate(Certificate certificate) {
        try{
            dao.update(certificate);
            System.out.println("Certificate updated");
            return certificate;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Certificate getCertificate(Long certificateId) {
        try{
            return dao.getById(certificateId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Certificate> getAllCertificates() {
        try{
            return dao.getAll();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteCertificate(Certificate certificate) {
        try{
            dao.delete(certificate);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
