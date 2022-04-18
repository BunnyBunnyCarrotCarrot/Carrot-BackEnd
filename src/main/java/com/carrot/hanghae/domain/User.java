package com.carrot.hanghae.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

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

    @Column(nullable = false)
    private String userName;

    @ManyToOne
    @JoinColumn
    private Location location;

    @ColumnDefault("'https://bucketlist5.s3.ap-northeast-2.amazonaws.com/당근이.png'")
    private String imgUrl;

    public User (String userId, String userPw, String userName, Location location, String imgUrl){
        this.userId = userId;
        this.userPw = userPw;
        this.userName = userName;
        this.location = location;
        this.imgUrl = imgUrl;
    }
}
