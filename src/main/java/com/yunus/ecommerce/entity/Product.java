package com.yunus.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "product", schema = "e_commerce")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name")
    private  String productName;

    @Column(name = "product_desc")
    private  String productDesc;

    @Column(name = "product_price")
    private  Float productPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_category",nullable = false)
    private  Category productCategory;


    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private List<OrderDetail> orderDetails;

}
