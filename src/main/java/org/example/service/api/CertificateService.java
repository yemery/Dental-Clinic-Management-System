package org.example.service.api;

import org.example.dao.IDao;
import org.example.model.Certificate;

import java.util.List;

public interface CertificateService{
   Certificate addCertificate(Certificate certificate);
   Certificate updateCertificate(Certificate certificate);
   Certificate getCertificate(Long certificateId);
   List<Certificate> getAllCertificates();
   void deleteCertificate(Long ID);
   boolean removeFromConsultation(Long ID);

}
