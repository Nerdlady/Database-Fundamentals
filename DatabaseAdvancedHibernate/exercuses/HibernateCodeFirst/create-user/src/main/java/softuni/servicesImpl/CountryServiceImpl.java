package softuni.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.entities.Country;
import softuni.repositories.CountryDao;
import softuni.services.CountryService;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryDao countryDao;

    @Autowired
    public CountryServiceImpl(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    @Override
    public void persist(Country country) {
        this.countryDao.saveAndFlush(country);
    }
}
