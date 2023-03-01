package com.fetchAward.demo.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "receipts")
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    @Column(name = "retailer", nullable = false)
    private String retailer;
    @Column(name = "purchaseDate", nullable = false)
    private LocalDate purchaseDate;
    @Column(name = "purchaseTime", nullable = false)
    private LocalTime purchaseTime;
    @Column(name = "total", nullable = false)
    private Long total;
    @OneToMany(mappedBy = "receipt", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items;




}
