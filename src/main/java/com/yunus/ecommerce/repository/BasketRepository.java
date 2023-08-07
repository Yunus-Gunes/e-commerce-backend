package com.yunus.ecommerce.repository;

import com.yunus.ecommerce.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BasketRepository extends JpaRepository<Basket,Long> {

    @Query("SELECT p FROM Basket p WHERE p.userBasket.userId = :userId")
    List<Basket> findByUserId(@Param("userId") long userId);

    @Modifying
    @Query("DELETE FROM Basket b WHERE b.userBasket.userId = :userId")
    void deleteBasketByUserId(@Param("userId") long userId);


}
