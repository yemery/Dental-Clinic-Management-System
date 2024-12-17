package org.example.dao.implementation;

import org.example.dao.IDao;
import org.example.model.Act;
import org.example.model.Prescription;

import java.util.ArrayList;
import java.util.List;

public class PrescriptionDaoImpl  implements IDao<Prescription,Long> {
    private final List<Act> acts = new ArrayList<>();

}
