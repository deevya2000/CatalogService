package com.ibm.demo;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.ibm.demo.Product;
import com.ibm.demo.ProductRepository;

@SpringBootApplication
public class CatalogServiceApplication {
    private final Logger logger = LoggerFactory.getLogger(CatalogServiceApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(CatalogServiceApplication.class, args);
	}
	
	@Autowired 
    private ProductRepository repository; 
  
	@EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
		getAllProducts();
		createProduct();
		getAllProducts();
    }

    private void createProduct() {
        Product prod = new Product();
        prod.setProductName("Product1");
        prod.setProductCategory("Category1");
        logger.info("Saving new product...");
        this.repository.save(prod);
    }

    private void getAllProducts() {
        List<Product> products = this.repository.findAll();
        logger.info("Number of customers: " + products.size());
    }

}
