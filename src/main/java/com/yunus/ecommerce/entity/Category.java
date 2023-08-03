package com.yunus.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(schema = "e_commerce", name = "category")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "category_name")
    private String categoryName;

    @OneToMany(mappedBy = "category_id", fetch = FetchType.EAGER)
    private List<Product> products;

}
