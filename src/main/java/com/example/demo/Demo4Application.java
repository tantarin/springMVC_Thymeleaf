package com.example.demo;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Product;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class Demo4Application implements CommandLineRunner {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(Demo4Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception
    {
        Product product1 = new Product("pr1");
        Product product2 = new Product("pr2");
        Product p = productRepository.save(product1);
        Product p2 = productRepository.save(product2);
        Customer student = new Customer("Ivan", Arrays.asList(p, p2));
        customerRepository.save(student);
        customerRepository.save(new Customer("Ira", "Ivanova", "ta@gmail.com"));
    }

}
