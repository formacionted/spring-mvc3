package com.telefonica.jee;

import java.math.BigDecimal;

import javax.persistence.CascadeType;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.telefonica.jee.model.Manufacturer;
import com.telefonica.jee.model.Product;
import com.telefonica.jee.model.ProductSize;
import com.telefonica.jee.model.Tag;
import com.telefonica.jee.repository.ManufacturerRepository;
import com.telefonica.jee.repository.ProductRepository;
import com.telefonica.jee.repository.ProductSizeRepository;
import com.telefonica.jee.repository.TagRepository;

@SpringBootApplication
public class SpringMvc3Application implements CommandLineRunner{

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductSizeRepository productSizeRepository;
	
	@Autowired
	private ManufacturerRepository manufacturerRepository;
	
	@Autowired
	private TagRepository tagRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringMvc3Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		/*approach 2: En caso de no utilizar cascade = {CascadeType.ALL} entre Product y ProductSize será necesario descomentar este codigo
		 * y comentar las tres lineas siguientes, tambien descomentar las de sizes de abajo*/
//		ProductSize size1 = productSizeRepository.save(new ProductSize(new BigDecimal(4.99),new BigDecimal(4.99),new BigDecimal(4.99)));
//		ProductSize size2 = productSizeRepository.save(new ProductSize(new BigDecimal(5.99),new BigDecimal(6.99),new BigDecimal(7.99)));
//		ProductSize size3 = productSizeRepository.save(new ProductSize(new BigDecimal(6.99),new BigDecimal(6.99),new BigDecimal(7.99)));
		ProductSize size1 = new ProductSize(new BigDecimal(4.99),new BigDecimal(4.99),new BigDecimal(4.99));
		ProductSize size2 = new ProductSize(new BigDecimal(5.99),new BigDecimal(6.99),new BigDecimal(7.99));
		ProductSize size3 = new ProductSize(new BigDecimal(6.99),new BigDecimal(6.99),new BigDecimal(7.99));
		
		Manufacturer man1 = manufacturerRepository.save(new Manufacturer("Nike", "man1"));
		Manufacturer man2 = manufacturerRepository.save(new Manufacturer("Adidas", "man2"));
		Manufacturer man3 = manufacturerRepository.save(new Manufacturer("Rebook", "man3"));

		Tag tag1 = tagRepository.save(new Tag("Running"));
		Tag tag2 = tagRepository.save(new Tag("Walk"));
		Tag tag3 = tagRepository.save(new Tag("Mountain"));
		
		Product product1 = new Product("Nike Airmax", new BigDecimal(6.99), 6);
//		size1.setProduct(product1);
		
		Product product2 = new Product("Asidras", new BigDecimal(6.99), 6);
//		size2.setProduct(product2);
		
		Product product3 = new Product("Acliclas", new BigDecimal(6.99), 6);
//		size3.setProduct(product3);
		

		product1.setManufacturer(man1);
		product1.getTags().add(tag1);
		product1.getTags().add(tag2);
		product1.getTags().add(tag3);
		product1.setProductSize(size1);

		productRepository.save(product1);
		

		product2.setManufacturer(man2);
		product2.getTags().add(tag2);
		product2.getTags().add(tag3);
		product2.setProductSize(size2);
		productRepository.save(product2);
		
		product3.setManufacturer(man2);
		product3.getTags().add(tag1);
		product3.getTags().add(tag2);
		product3.setProductSize(size3);
		productRepository.save(product3);
		
		/* JPA solo persiste en el lado owner, por lo que esto no va a funcionar: */
//		tag4.getProducts().add(product1);
//		tagRepository.save(tag4);
	}
	
}
