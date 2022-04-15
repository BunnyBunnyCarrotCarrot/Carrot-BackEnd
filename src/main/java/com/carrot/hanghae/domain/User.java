package com.carrot.hanghae.domain;

import com.carrot.hanghae.dto.UserRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Setter
@Entity
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false)
    private String userPw;


    @ManyToOne
    @JoinColumn
    private Location location;

    public User (String userId, String userPw, Location location){
        this.userId = userId;
        this.userPw = userPw;
        this.location = location;
    }
}
