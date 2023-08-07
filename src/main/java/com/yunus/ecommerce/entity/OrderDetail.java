package com.yunus.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_detail", schema = "e_commerce")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id")
    private Long orderDetailId;

    @Column(name = "number_of_product")
    private Integer numberOfProducts;

    @Column(name = "order_product_price")
    private Float orderProductPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_product_id",nullable = false)
    private  Product orderProduct;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id",nullable = false)
    private  Order orderDetailOrderId;
}
