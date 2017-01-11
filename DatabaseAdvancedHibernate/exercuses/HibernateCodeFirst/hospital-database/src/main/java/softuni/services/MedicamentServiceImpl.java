package softuni.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.entities.Medicament;
import softuni.repositories.MedicamentDao;

@Service
public class MedicamentServiceImpl implements MedicamentService {
    private final MedicamentDao medicamentDao;

    @Autowired
    public MedicamentServiceImpl(MedicamentDao medicamentDao) {
        this.medicamentDao = medicamentDao;
    }

    @Override
    public void persist(Medicament medicament) {
        this.medicamentDao.saveAndFlush(medicament);
    }
}
