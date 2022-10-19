package com.app.atlasultimate.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pension")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor

public class Pension {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 10)
    private Double id;

    @Column(name = "sa")
    private Double sa;
    @Column(name = "ad")
    private Double ad;
    @Column(name = "mp")
    private Double mp;
    @Column(name = "pc")
    private Double pc;

    @JoinTable(
            name = "hotel_pension",
            joinColumns = @JoinColumn(name = "tipo_pension", nullable = false),
            inverseJoinColumns = @JoinColumn(name="hotel", nullable = false)
    )

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Hotel> hotel;
}
