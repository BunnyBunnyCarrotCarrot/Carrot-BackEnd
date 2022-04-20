package com.carrot.hanghae.domain;

import com.carrot.hanghae.dto.ItemRequestDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Item extends Timestamped {
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

    @ManyToOne //(fetch = FetchType.LAZY)
    @JoinColumn(name="CATEGORY_ID", nullable = false)
    private Category category;

    @JsonManagedReference
    @OneToMany(mappedBy = "item")
    private List<ImageUrl> imageUrls;

    @Column
    private Boolean status=false;

    public Item(String title, int price, String about, User user, Category category){
        this.title = title;
        this.price = price;
        this.about = about;
        this.user = user;
        this.category = category;
    }

    public void update(ItemRequestDto itemDto, Category category) {
        this.title = itemDto.getTitle();
        this.price = itemDto.getPrice();
        this.about = itemDto.getAbout();
        this.category = category;
    }

    public void addImage(ImageUrl imageUrl){
        this.imageUrls.add(imageUrl);
    }
}
