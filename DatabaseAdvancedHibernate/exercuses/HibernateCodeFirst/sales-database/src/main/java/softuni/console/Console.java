package softuni.console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import softuni.entities.Customer;
import softuni.entities.Product;
import softuni.entities.Sale;
import softuni.entities.StoreLocation;
import softuni.services.CustomerService;
import softuni.services.ProductService;
import softuni.services.SaleService;
import softuni.services.StoreLocationService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Component
public class Console implements CommandLineRunner {

    private final CustomerService customerService;
    private final ProductService productService;
    private final SaleService saleService;
    private final StoreLocationService storeLocationService;

    @Autowired
    public Console(CustomerService customerService, ProductService productService, SaleService saleService, StoreLocationService storeLocationService) {
        this.customerService = customerService;
        this.productService = productService;
        this.saleService = saleService;
        this.storeLocationService = storeLocationService;
    }

    @Override
    public void run(String... strings) throws Exception {
        List<Sale> sales = this.saleService.getAll();
        List<Customer> customers = this.customerService.getAll();
        List<Product> products = this.productService.getAll();
        List<StoreLocation> storeLocations = this.storeLocationService.getAll();


        if (customers.size() < 1 && products.size() < 1 && storeLocations.size() < 1 && sales.size() < 1) {
            this.fill();
        }

         customers = this.customerService.getAll();

        for (Customer customer : customers) {
            for (Sale sale : customer.getSalesForCustomer()) {
                System.out.println(sale.getProduct().getName());
            }
        }

    }

    private void fill() {
        Product product = new Product();
        product.setName("Ice cream");
        product.setPrice(new BigDecimal("1.50"));

        this.productService.persist(product);

        Customer customer = new Customer();
        customer.setName("Ivan");
        customer.setEmail("ivan@ivan.com");
        customer.setCreditCardNumber("1575f48sf48");

        this.customerService.persist(customer);

        StoreLocation storeLocation = new StoreLocation();
        storeLocation.setLocationName("Sofia");

        this.storeLocationService.persist(storeLocation);

        Sale sale = new Sale();
        sale.setDate(new Date());
        sale.setCustomer(customer);
        sale.setProduct(product);
        sale.setStoreLocation(storeLocation);

        this.saleService.persist(sale);
    }
}
