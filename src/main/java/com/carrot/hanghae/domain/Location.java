package com.carrot.hanghae.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Setter
@Entity
@AllArgsConstructor
public class Location {
    @Id
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    private String name;
}
