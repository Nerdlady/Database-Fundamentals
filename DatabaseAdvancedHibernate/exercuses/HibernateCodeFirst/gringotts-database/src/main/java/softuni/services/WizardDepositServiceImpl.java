package softuni.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.entities.WizardDeposit;
import softuni.repositories.WizardDepositDao;

@Service
public class WizardDepositServiceImpl implements WizardDepositService {

    private final WizardDepositDao wizardDepositDao;

    @Autowired
    public WizardDepositServiceImpl(WizardDepositDao wizardDepositDao) {
        this.wizardDepositDao = wizardDepositDao;
    }

    @Override
    public void persist(WizardDeposit wizardDeposit) {
        this.wizardDepositDao.saveAndFlush(wizardDeposit);
    }
}
