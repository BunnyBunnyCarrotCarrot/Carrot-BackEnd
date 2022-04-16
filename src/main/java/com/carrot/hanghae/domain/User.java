package com.carrot.hanghae.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.mapping.ToOne;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class User extends timestamped {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String userPw;

    @ManyToOne
    @JoinColumn(name="LOCATION_ID")
    private Location location;


    public User(String userId, String userPw, Location location){
        this.userId = userId;
        this.userPw = userPw;
        this.location = location;
    }
}
