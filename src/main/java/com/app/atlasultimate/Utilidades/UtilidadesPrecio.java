package com.app.atlasultimate.Utilidades;

import com.app.atlasultimate.model.Temporada;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.Map;
import java.util.function.IntToDoubleFunction;


public class UtilidadesPrecio {

    public static Integer duracionReserva(LocalDate fechaInicio, LocalDate fechaFin){
        Integer duracion= null;
        Period intervalo = Period.between(fechaInicio, fechaFin);
        duracion = intervalo.getDays();
        return duracion;
    }

    public static Map<Integer,Double> temporada (LocalDate fechaInicio, LocalDate fechaFin,
                                          Temporada temporadaporHabitacion){
        Integer duracion = duracionReserva(fechaInicio, fechaFin);
        Map<Integer, Double> mapafinal = new HashMap<>();

        LocalDate fechaInicioInvierno = LocalDate.of(fechaInicio.getYear(), 12,21);
        LocalDate fechafinInvierno = LocalDate.of(fechaInicio.getYear(), 3,19);
        LocalDate fechaInicioPrimavera = LocalDate.of(fechaInicio.getYear(), 3,20);
        LocalDate fechafinPrimavera = LocalDate.of(fechaInicio.getYear(), 6,20);
        LocalDate fechaInicioVerano = LocalDate.of(fechaInicio.getYear(), 6,21);
        LocalDate fechafinVerano = LocalDate.of(fechaInicio.getYear(), 9,22);
        LocalDate fechaInicioOtono = LocalDate.of(fechaInicio.getYear(), 9,22);
        LocalDate fechafinOtono = LocalDate.of(fechaInicio.getYear(), 12,20);

        if(fechaInicio.isAfter(fechaInicioInvierno)  && fechaFin.isBefore(fechafinInvierno)){

            mapafinal.put(duracionReserva(fechaInicio,fechaFin),
                    temporadaporHabitacion.getPrecioInvierno());
            return mapafinal;
        }

        else if (fechaInicio.isAfter(fechaInicioPrimavera)  && fechaFin.isBefore(fechafinPrimavera)){

            mapafinal.put(duracionReserva(fechaInicio,fechaFin),
                    temporadaporHabitacion.getPrecioPrimavera());
            return mapafinal;
        }

        else if(fechaInicio.isAfter(fechaInicioVerano)  && fechaFin.isBefore(fechafinVerano)){

            mapafinal.put(duracionReserva(fechaInicio,fechaFin),
                    temporadaporHabitacion.getPrecioVerano());
            return mapafinal;
        }
        else {

            mapafinal.put(duracionReserva(fechaInicio,fechaFin),
                    temporadaporHabitacion.getPrecioOtono());
            return mapafinal;
        }

    }

    public static Double precioSemiFinal( LocalDate fechaInicio, LocalDate fechaFin,
                                          Temporada temporadaporHabitacion,
                                          Double precioBase){
        Double preciofin;
        Integer duracion = duracionReserva(fechaInicio, fechaFin);

        Double precioporhab= precioBase * duracion;

        Map<Integer,Double> mapafin = temporada(fechaInicio, fechaFin, temporadaporHabitacion);

        preciofin = (double) mapafin.keySet().stream().reduce(1,(a, b) -> a*b);


        return preciofin + precioporhab;
    }

    public static Double preciototal(Integer numPersona, Integer duracion,
                              Double pension, Double temporada,
                              Double precioBase){
        Double preciofin;

        preciofin = precioBase +temporada + pension;
        preciofin = preciofin * duracion;
        preciofin = preciofin * numPersona;

        return preciofin;
    }


}
