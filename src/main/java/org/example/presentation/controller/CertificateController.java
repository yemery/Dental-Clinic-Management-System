package org.example.presentation.controller;

import org.example.model.Certificate;
import org.example.service.api.CertificateService;
import org.example.service.implementation.CertificateServiceImpl;

import java.util.List;

public class CertificateController {
    private final CertificateService certificateService = new CertificateServiceImpl();

    public List<Certificate> displayAllCertificates() {
        return certificateService.getAllCertificates();
    }

    public void addNewCertificate(Certificate certificate) {
        certificateService.addCertificate(certificate);
    }

    public Certificate getCertificateById(Long id) {
        return certificateService.getCertificate(id);
    }

    public void updateCertificate(Certificate certificate) {certificateService.updateCertificate(certificate);}
}
