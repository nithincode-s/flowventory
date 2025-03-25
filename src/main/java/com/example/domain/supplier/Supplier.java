package com.example.domain.supplier;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Table(name = "\"supplier\"")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private UUID id;

    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    private String name;

    @Column(columnDefinition = "VARCHAR(100)", nullable = false)
    private String contactDetails;

    @Column(columnDefinition = "VARCHAR(100)", nullable = false)
    private String address;

    @Column(columnDefinition = "VARCHAR(50)", nullable = false)
    private String email;
}
