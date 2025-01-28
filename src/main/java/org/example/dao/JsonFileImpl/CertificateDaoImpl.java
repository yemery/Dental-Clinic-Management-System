package org.example.dao.JsonFileImpl;

import org.example.model.Certificate;

public class CertificateDaoImpl extends JsonDaoImpl <Certificate,Long>{
    public CertificateDaoImpl(String fileName) {
        super(fileName, Certificate.class);
    }
}
