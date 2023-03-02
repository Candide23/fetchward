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
    private String purchaseDate;
    @Column(name = "purchaseTime", nullable = false)
    private String purchaseTime;
    @Column(name = "total", nullable = false)
    private Long total;
    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "receipt_id")
    private List<Item> items;




}
