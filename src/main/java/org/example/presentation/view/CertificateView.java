package org.example.presentation.view;

import org.example.model.Certificate;
import org.example.presentation.controller.CertificateController;

import java.time.LocalDate;

public class CertificateView {
    public static void main(String[] args) {
        CertificateController controller = new CertificateController();

        Certificate c1 = controller.getCertificateById(2L);
        c1.setReason("updated reason");
        controller.updateCertificate(c1);
//        Certificate c1 = new Certificate("Reason1", LocalDate.of(2000,2,12),LocalDate.of(2000,3,12));
//        Certificate c2 = new Certificate("Reason2", LocalDate.of(2000,2,12),LocalDate.of(2000,3,12));
//
//        controller.addNewCertificate(c1);
//        controller.addNewCertificate(c2);
//
//        controller.displayAllCertificates();

    }
}
