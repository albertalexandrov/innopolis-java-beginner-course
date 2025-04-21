package ru.innopolis.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String locality;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String house;

    private String porch;

    private String floor;

    @Column(nullable = false)
    private String apartment;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
