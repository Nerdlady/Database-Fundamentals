package softuni.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.entities.Sale;
import softuni.repositories.SaleDao;

import java.util.List;
@Service
public class SaleServiceImpl implements SaleService {

    private final SaleDao saleDao;

    @Autowired
    public SaleServiceImpl(SaleDao saleDao) {
        this.saleDao = saleDao;
    }

    @Override
    public void persist(Sale sale) {
        this.saleDao.saveAndFlush(sale);
    }

    @Override
    public List<Sale> getAll() {
        return this.saleDao.findAll();
    }
}
