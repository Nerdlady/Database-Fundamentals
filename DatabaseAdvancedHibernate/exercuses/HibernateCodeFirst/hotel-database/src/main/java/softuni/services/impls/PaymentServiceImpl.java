package softuni.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.entities.Payment;
import softuni.repositories.PaymentDao;
import softuni.services.interfaces.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {
   private final PaymentDao paymentDao;

    @Autowired
    public PaymentServiceImpl(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }

    @Override
    public void persist(Payment payment) {
        this.paymentDao.saveAndFlush(payment);
    }
}
