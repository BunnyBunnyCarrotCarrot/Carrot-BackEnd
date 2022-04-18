package com.carrot.hanghae.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.mapping.ToOne;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User extends timestamped {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String userPw;

    @Column
    private String imgUrl;

    @ManyToOne
    @JoinColumn(name="LOCATION_ID")
    private Location location;


    public User(String userId, String userName, String userPw, Location location){
        this.userId = userId;
        this.userName = userName;
        this.userPw = userPw;
        this.location = location;
    }
}
