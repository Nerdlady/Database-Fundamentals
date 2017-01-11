package bills.system.services;

import bills.system.domain.BasicBillingDetail;
import bills.system.repositories.BasicBillingDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasicBillingDetailServiceImpl implements BasicBillingDetailService {
    @Autowired
    private BasicBillingDetailRepository basicBillingDetailRepository;

    @Override
    public void persist(BasicBillingDetail basicBillingDetail) {
        this.basicBillingDetailRepository.saveAndFlush(basicBillingDetail);
    }
}
