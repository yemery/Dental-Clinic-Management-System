package org.example.presentation.controller;

import org.example.model.Certificate;
import org.example.service.api.CertificateService;
import org.example.service.implementation.CertificateServiceImpl;

public class CertificateController {
    private final CertificateService certificateService = new CertificateServiceImpl();

    public void displayAllCertificates() {
        certificateService.getAllCertificates().forEach(System.out::println);
    }
    public void AddNewCertificate(Certificate certificate) {
        certificateService.addCertificate(certificate);
    }


}
