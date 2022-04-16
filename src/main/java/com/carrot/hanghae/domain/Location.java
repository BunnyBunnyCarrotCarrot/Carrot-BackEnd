package com.carrot.hanghae.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Setter
@Entity
@AllArgsConstructor
public class Location {
    @Id
    @Column(nullable = false, unique = true)
    private Long id;

    @Column
    private String name;
}
