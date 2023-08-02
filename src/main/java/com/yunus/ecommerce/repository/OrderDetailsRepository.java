package com.yunus.ecommerce.repository;

import com.yunus.ecommerce.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsRepository extends JpaRepository<OrderDetail,Long> {
}
