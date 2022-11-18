package com.app.atlasultimate.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity(name="registro")
@Table(name = "registro")
@Getter
@Setter
public class Registro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 10)
    private Integer id;


    @Column(name = "f_entrada")
    private String f_entrada;
    @Column(name = "f_salida")
    private String f_salida;
    @Column(name = "n_personas", length = 1)
    private Integer n_personas;
    @Column(name = "t_pago", length = 150)
    @Enumerated(value = EnumType.STRING)
    private tipo_pago t_pago;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "t_pension", length = 150)
    private tipo_pension t_pension;
    @Column(name = "precio_total_dias")
    private Double precio_total_dias;
    @Column(name = "n_dias", length = 3)
    private Integer n_dias;
    @Column(name = "activa")
    private Boolean activa;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_habitacion")
    private Habitacion habitacion;

    public Registro(String f_entrada, String f_salida, Integer n_personas, tipo_pago t_pago, tipo_pension t_pension,
                    Double precio_total_dias, Integer n_dias, Usuario usuario, Habitacion habitacion, Boolean activa) {

        this.f_entrada = f_entrada;
        this.f_salida = f_salida;
        this.n_personas = n_personas;
        this.t_pago = t_pago;
        this.t_pension = t_pension;
        this.precio_total_dias = precio_total_dias;
        this.n_dias = n_dias;
        this.usuario = usuario;
        this.habitacion = habitacion;
        this.activa = activa;

    }


    public Registro() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Registro)) return false;
        Registro registro = (Registro) o;
        return Objects.equals(getId(), registro.getId()) && Objects.equals(getF_entrada(), registro.getF_entrada()) && Objects.equals(getF_salida(), registro.getF_salida()) && Objects.equals(getN_personas(), registro.getN_personas()) && getT_pago() == registro.getT_pago() && getT_pension() == registro.getT_pension() && Objects.equals(getPrecio_total_dias(), registro.getPrecio_total_dias()) && Objects.equals(getN_dias(), registro.getN_dias()) && Objects.equals(getActiva(), registro.getActiva()) && Objects.equals(getUsuario(), registro.getUsuario()) && Objects.equals(getHabitacion(), registro.getHabitacion());
    }

}
