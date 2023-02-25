package com.example.demo.controller;

import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerService customerService, CustomerRepository customerRepository) {
        this.customerService = customerService;
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public String getAllCustomers(Model model){
        List<Customer> customers = customerService.getAll();
        model.addAttribute("customers", customers);
        return "customers";
    }

    @GetMapping(path = { "addOrEdit", "addOrEdit/{id}" })
    public String editCustomerById(Model model,
                               @PathVariable("id") Optional<Long> id) {
        if (id.isPresent()) {
            Customer customer = customerService.getCustomerById(id.get());
            model.addAttribute("customer", customer);
        } else {
            model.addAttribute("customer", new Customer());
        }
        return "add-edit-customer";
    }

    @PostMapping(path = "createOrUpdateCustomer")
    public String createOrUpdateCustomer(Customer customer) {
        customerRepository.save(customer);
        return "redirect:/";
    }

    @GetMapping(path = "/delete/{id}")
    public String deleteCustomerById(Model model,
                                 @PathVariable("id") Long id) {
        customerRepository.deleteById(id);
        return "redirect:/";
    }
}