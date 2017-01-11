package bank.system.services;

import bank.system.domain.BasicAccount;
import bank.system.domain.User;
import bank.system.repositories.BasicAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final BasicAccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(BasicAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void persist(BasicAccount account) {
        this.accountRepository.saveAndFlush(account);
    }

    @Override
    public BasicAccount getById(String id) {
        return this.accountRepository.getOne(id);
    }

    @Override
    public List<BasicAccount> getAllWithUser(User user) {

        return this.accountRepository.findAllByUser(user);
    }

    @Override
    public List<BasicAccount> getAllWithUser(User usere, String clazz) {
        return this.accountRepository.findAllByUserAndClass(usere,clazz);
    }


}
