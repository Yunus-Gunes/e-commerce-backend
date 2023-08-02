package com.yunus.ecommerce;

import com.yunus.ecommerce.entity.*;
import com.yunus.ecommerce.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;


import java.time.LocalDateTime;

@RestController
public class YourApplication {

    private final JdbcTemplate jdbcTemplate;
    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final ProductRepository productRepository;

    public YourApplication(CategoryRepository categoryRepository, CustomerRepository customerRepository, JdbcTemplate jdbcTemplate, OrderRepository orderRepository, OrderDetailsRepository orderDetailsRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.jdbcTemplate = jdbcTemplate;
        this.orderRepository = orderRepository;
        this.orderDetailsRepository = orderDetailsRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("/health")
    public ResponseEntity<String> checkHealth() {
        try {
            jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            return ResponseEntity.ok("Uygulama ve PostgreSQL bağlantısı çalışıyor.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("PostgreSQL bağlantısı başarısız: " + e.getMessage());
        }
    }


    @GetMapping("/adddata")
    public ResponseEntity<String> addData() {
        try {


            Category category = new Category();
            category.setCategoryName("Technology");
            categoryRepository.save(category);

            Product product = new Product();
            product.setProductName("Keyboard");
            product.setProductDesc("Q Keybord");
            product.setProductPrice(10F);
            product.setProductCategory(categoryRepository.getReferenceById(6L));
            productRepository.save(product);

            Customer customer = new Customer();
            customer.setCustomerEmail("yunus@gmail.com.tr");
            customer.setCustomerName("Yunus");
            customer.setCustomerAddress("Yozgat");
            customer.setCustomerPassword("123");
            customerRepository.save(customer);

            Order order = new Order();
            order.setCustomer(customerRepository.getReferenceById(2L));
            order.setOrderDate(LocalDateTime.now());
            order.setOrderTotal(100F);
            orderRepository.save(order);

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(orderRepository.getReferenceById(2L));
            orderDetail.setProduct(productRepository.getReferenceById(4L));
            orderDetail.setSubTotal(20F);
            orderDetail.setProductPrice(10f);
            orderDetailsRepository.save(orderDetail);


            return ResponseEntity.status(HttpStatus.CREATED).body("Data added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add data");
        }
    }
}