package com.app.atlasultimate.controller;

import com.app.atlasultimate.model.Habitacion;
import com.app.atlasultimate.model.Hotel;
import com.app.atlasultimate.model.Registro;
import com.app.atlasultimate.repository.HabitacionRepository;
import com.app.atlasultimate.repository.ReservaRepository;
import com.app.atlasultimate.repository.TemporadaRepository;
import com.app.atlasultimate.service.HabitacionService;
import com.app.atlasultimate.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;

@Controller
@RequestMapping("habitacion")
public class HabitacionController {
    @Autowired
    private HabitacionService servicio;
    @Autowired
    private HotelService hotelServicio;
    @Autowired
    private HabitacionRepository habitacionRepository;
    @Autowired
    private ReservaRepository reservaRepository;

    //MUESTRA FORMULARIO CREACION DE HABITACION
    @GetMapping("nueva/{id_hotel}")
    public String crearHabitacion(@PathVariable Integer id_hotel, Model model, Model mod2) {
        Habitacion habitacion = new Habitacion();
        Hotel hotel = hotelServicio.buscarHotel(id_hotel);
        model.addAttribute("habitacion", habitacion);
        mod2.addAttribute("hotel", hotel);

        return "/crearhabitacion.html";
    }

    //GUARDAR DATOS HABITACION EN BBDD
    @PostMapping("nueva/{id}")
    public String guardarHabitacion(@ModelAttribute("habitacion") Habitacion hab, @ModelAttribute("hotel") Hotel hot) {
        chequearBooleanHabitacion(hab);
        hab.setHotel(hot);
        servicio.guardarHabMultiple(hab.getNum_habitaciones_iguales(), hab);
        return "redirect:/hotel/habitacion/{id}";
    }

    //convertir boolean en true o false
    public void chequearBooleanHabitacion(Habitacion h) {
        h.setBano(h.getBano() == null ? false : true);
        h.setVistas(h.getVistas() == null ? false : true);
    }

    //Crear y editar en graphql
    @PostMapping("/habitacion/crear/graphiql/")
    @SchemaMapping(typeName = "Mutation", value = "crearEditarHabitacion")
    public String crearEditarHotel(@RequestParam(required = false) @Argument Integer id_habitacion,
                                   @RequestParam @Argument Integer cama_individual,
                                   @RequestParam @Argument Integer cama_doble,
                                   @RequestParam @Argument Double precio_base,
                                   @RequestParam @Argument Integer num_maximo_personas,
                                   @RequestParam(required = false) @Argument Boolean bano,
                                   @RequestParam(required = false) @Argument Boolean vistas) {
        Habitacion habitacion = new Habitacion();
        if (id_habitacion != null) {
            habitacion = habitacionRepository.findTopById(id_habitacion);
            if (habitacion == null) {
                return "esta habitación no existe";
            } else {
                habitacion.setId(id_habitacion);
                habitacion.setC_individual(cama_individual);
                habitacion.setC_doble(cama_doble);
                habitacion.setPrecio_base(precio_base);
                habitacion.setN_max_personas(num_maximo_personas);
                habitacion.setBano(bano);
                habitacion.setVistas(vistas);
                chequearBooleanHabitacion(habitacion);
                habitacionRepository.save(habitacion);
                return "Su habitacion se ha editado correctamente";
            }
        } else {
            habitacion.setId(id_habitacion);
            habitacion.setC_individual(cama_individual);
            habitacion.setC_doble(cama_doble);
            habitacion.setPrecio_base(precio_base);
            habitacion.setN_max_personas(num_maximo_personas);
            habitacion.setBano(bano);
            habitacion.setVistas(vistas);
            chequearBooleanHabitacion(habitacion);
            habitacionRepository.save(habitacion);
            return "Su habitacion se ha creado correctamente";
        }


    }

    @DeleteMapping("/delete/graphiql/")
    @SchemaMapping(typeName = "Mutation", value = "eliminarHabitacion")
    public String eliminarHabitacion(@RequestParam(required = false) @Argument Integer id_habitacion) {

        Habitacion hab = habitacionRepository.findTopById(id_habitacion);

        if (hab == null) {
            return "la habitación no existe";
        } else {
            List<Registro> listaDeRegistrosPorIdHabitacion = reservaRepository.listaregistroPorIdHab(id_habitacion);
            for (Registro r : listaDeRegistrosPorIdHabitacion) {
                if (r.getActiva().equals(true)) {
                    return "la habitación no se puede eliminar porque tiene una reserva";
                } else {
                    r.setHabitacion(null);
                    reservaRepository.save(r);
                }
            }
            habitacionRepository.deleteById(id_habitacion);

            return "La habitación se ha borrado correctamente";
        }

    }
}