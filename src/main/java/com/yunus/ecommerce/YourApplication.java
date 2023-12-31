package com.yunus.ecommerce;

import com.yunus.ecommerce.entity.*;
import com.yunus.ecommerce.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;


import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class YourApplication {

    private final JdbcTemplate jdbcTemplate;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final BasketRepository basketsRepository;
    private final ProductRepository productRepository;


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
            product.setCategory_id(categoryRepository.getReferenceById(6L));
            productRepository.save(product);

            User user = new User();
            user.setUserEmail("yunus@gmail.com.tr");
            user.setUserName("Yunus");
            user.setUserAddress("Yozgat");
            user.setUserPassword("123");
            userRepository.save(user);

            Order order = new Order();
            order.setUser(userRepository.getReferenceById(2L));
            order.setOrderDate(LocalDateTime.now());
            order.setOrderTotal(100F);
            orderRepository.save(order);

            /*
            Basket basket = new Basket();
            basket.setBasketOrder(orderRepository.getReferenceById(2L));
            basket.setBasketProduct(productRepository.getReferenceById(4L));
            basket.setSubTotal(20F);
            basket.setProductPrice(10f);
            basketsRepository.save(basket);

             */


            return ResponseEntity.status(HttpStatus.CREATED).body("Data added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add data");
        }
    }
}