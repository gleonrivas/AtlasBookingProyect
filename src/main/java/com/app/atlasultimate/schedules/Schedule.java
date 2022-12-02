package com.app.atlasultimate.schedules;

import com.app.atlasultimate.model.Registro;
import com.app.atlasultimate.model.RegistroPasado;
import com.app.atlasultimate.repository.RegistroPasadoRepository;
import com.app.atlasultimate.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Configuration
@EnableScheduling
public class Schedule {
    @Autowired
    ReservaRepository reservaRepository;

    @Autowired
    RegistroPasadoRepository registroPasadoRepository;


    @Scheduled(cron = "* 0 0 * * *")
    public void cambiarReservaADisable() {
        //to do: comprobar reserva de habitaciones
        List<Registro> reservas = reservaRepository.findAll();
        for (Registro r : reservas) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(r.getF_salida(), formatter);
            if (date.equals(LocalDate.now())|| r.getActiva().equals(false)) {
                r.setActiva(false);
                RegistroPasado registroPasado = new RegistroPasado();
                registroPasado.setF_entrada(r.getF_entrada());
                registroPasado.setF_salida(r.getF_salida());
                registroPasado.setN_personas(r.getN_personas());
                registroPasado.setT_pago(r.getT_pago());
                registroPasado.setT_pension(r.getT_pension());
                registroPasado.setPrecio_total_dias(r.getPrecio_total_dias());
                registroPasado.setN_dias(r.getN_dias());
                registroPasado.setActiva(r.getActiva());
                registroPasado.setCodigo(r.getCodigo());
                registroPasado.setHabitacion(r.getHabitacion());
                registroPasado.setUsuario(r.getUsuario());
                registroPasadoRepository.save(registroPasado);
                reservaRepository.delete(r);

            }
        }

    }

}
