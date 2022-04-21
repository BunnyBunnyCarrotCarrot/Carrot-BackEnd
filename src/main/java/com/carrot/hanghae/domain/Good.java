package com.carrot.hanghae.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Good extends Timestamped{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name="USER_ID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name="ITEM_ID", nullable = false)
    private Item item;


    public Good(User user, Item item){
        this.user = user;
        this.item = item;
    }
}
