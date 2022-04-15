package com.carrot.hanghae.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Item extends timestamp{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private String about;

    @ManyToOne
    @JoinColumn(name="USER_ID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name="CATEGORY_ID", nullable = false)
    private Category category;



    public Item(String title, int price, String about, User user){
        this.title = title;
        this.price = price;
        this.about = about;
        this.user = user;
    }
}
