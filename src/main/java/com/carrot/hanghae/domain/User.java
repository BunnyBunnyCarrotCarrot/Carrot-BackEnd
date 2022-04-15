package com.carrot.hanghae.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class User extends timestamp {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String userPw;

    @OneToOne
    @JoinColumn(name="LOCATION_ID", nullable = false)
    private Location location;

    @OneToMany(mappedBy = "user")
    private List<Item> item;

    @OneToMany(mappedBy = "user")
    private List<Good> good;


    public User(String userId, String userPw, Location location){
        this.userId = userId;
        this.userPw = userPw;
        this.location = location;
    }
}
