package softuni.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.entities.Diagnose;
import softuni.repositories.DiagnoseDao;

@Service
public class DiagnoseServiceImpl implements DiagnoseService {
    private final DiagnoseDao diagnoseDao;

    @Autowired
    public DiagnoseServiceImpl(DiagnoseDao diagnoseDao) {
        this.diagnoseDao = diagnoseDao;
    }

    public void persist(Diagnose diagnose) {
        this.diagnoseDao.saveAndFlush(diagnose);
    }
}
