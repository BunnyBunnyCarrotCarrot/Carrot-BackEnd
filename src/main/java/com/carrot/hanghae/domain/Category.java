package com.carrot.hanghae.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category {
    @Id
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    private String name;

//    @OneToMany(mappedBy = "category")
//    private List<Item> item;

    public Category(String name){
        this.name = name;
    }
}
