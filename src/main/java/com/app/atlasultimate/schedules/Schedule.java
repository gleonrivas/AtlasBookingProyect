package com.app.atlasultimate.schedules;

import com.app.atlasultimate.model.Registro;
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


    @Scheduled(cron = "* 0 0 * * *")
    public void cambiarReservaADisable() {
        //to do: comprobar reserva de habitaciones
        List<Registro> reservas = reservaRepository.findAll();
        for (Registro r : reservas) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(r.getF_entrada(), formatter);
            if (date.equals(LocalDate.now())) {
                r.setActiva(false);
                reservaRepository.save(r);
            }
        }

    }

}
