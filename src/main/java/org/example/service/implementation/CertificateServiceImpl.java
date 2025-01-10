package org.example.service.implementation;

import org.example.dao.IDao;
import org.example.dao.ArrayListImpl.CertificateDaoImp;
import org.example.dao.JsonFileImpl.JsonDaoImpl;
import org.example.model.Certificate;
import org.example.model.Consultation;
import org.example.model.Intervention;
import org.example.service.api.CertificateService;
import org.example.service.api.ConsultationService;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class CertificateServiceImpl implements CertificateService {
//    private  final IDao<Certificate,Long> dao = new CertificateDaoImp();
    public final IDao<Certificate, Long> dao = new JsonDaoImpl<>("Certifications.json" , Certificate.class);

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
    public void deleteCertificate(Long ID) {
        try{
            dao.delete(ID);
            this.removeFromConsultation(ID);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean removeFromConsultation(Long ID) {
        ConsultationService consultationsService = new ConsultationServiceImpl();
        List<Consultation> consultations = consultationsService.getAllConsultations();

        AtomicBoolean updated = new AtomicBoolean(false);
        consultations.stream().filter(consultation -> consultation.getCertificate().equals(ID))
                .forEach(consultation -> {
                    consultation.setCertificate(0L);
                    consultationsService.updateConsultation(consultation);
                    updated.set(true);
                });

        return updated.get();
    }
}
