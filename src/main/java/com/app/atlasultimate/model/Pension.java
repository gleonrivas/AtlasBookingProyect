package com.app.atlasultimate.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pension")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Pension {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 10)
    private Integer id;
    //solo alojamiento
    @Column(name = "sa")
    private Double sa;

    @Column(name= "boolean_sa")
    private Boolean booleanSa;

    //almuerzo+desayuno
    @Column(name = "ad")
    private Double ad;

    @Column(name= "boolean_ad")
    private Boolean booleanAd;


    //media pension alumerzo+cena
    @Column(name = "mp")
    private Double mp;

    @Column(name= "boolean_mp")
    private Boolean booleanMp;

    //pension completa
    @Column(name = "pc")
    private Double pc;

    @Column(name= "boolean_pc")
    private Boolean booleanPc;

    @JsonBackReference
    @OneToOne( optional = true, cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_hotel")
    private Hotel id_hotel;


    public Pension(Double sa, Boolean booleanSa, Double ad, Boolean booleanAd, Double mp, Boolean booleanMp, Double pc, Boolean booleanPc, Hotel id_hotel) {
        this.sa = sa;
        this.booleanSa = booleanSa;
        this.ad = ad;
        this.booleanAd = booleanAd;
        this.mp = mp;
        this.booleanMp = booleanMp;
        this.pc = pc;
        this.booleanPc = booleanPc;
        this.id_hotel = id_hotel;
    }
}
