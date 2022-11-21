package com.app.atlasultimate.controller;

import com.app.atlasultimate.model.Habitacion;
import com.app.atlasultimate.model.Hotel;
import com.app.atlasultimate.model.Registro;
import com.app.atlasultimate.repository.HabitacionRepository;
import com.app.atlasultimate.repository.HotelRepository;
import com.app.atlasultimate.repository.ReservaRepository;
import com.app.atlasultimate.repository.TemporadaRepository;
import com.app.atlasultimate.service.HabitacionService;
import com.app.atlasultimate.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
    @Autowired
    private HotelRepository hotelRepository;

    //MUESTRA FORMULARIO CREACION DE HABITACION
    @GetMapping("nueva/{id_hotel}")
    public String crearHabitacion(@PathVariable Integer id_hotel, Model model, Model mod2) {

        Hotel hotel = hotelRepository.findTopById(id_hotel);
        Habitacion habitacion = new Habitacion();
        model.addAttribute("habitacion", habitacion);
        mod2.addAttribute("hotel", hotel);

        return "/crearhabitacion.html";
    }

    //GUARDAR DATOS HABITACION EN BBDD
    @PostMapping("nueva/{id}")
    public String guardarHabitacion(@ModelAttribute("habitacion") Habitacion hab, @ModelAttribute("hotel") Hotel hot, @RequestParam("file") MultipartFile file) throws IOException {
        chequearBooleanHabitacion(hab);
        Hotel hotel = hotelRepository.findTopById(hot.getId());
        hab.setId(null);
        hab.setHotel(hotel);
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        hab.setImg(fileName);
        List<Integer> ListaIds = servicio.guardarHabMultiple(hab.getNum_habitaciones_iguales(), hab);
        for (Integer id :ListaIds){
            String uploadDir = "./src/main/resources/static/img/imgHab/" + id;
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            try (InputStream inputStream = file.getInputStream()) {
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new IOException("No se puede guardar"+ fileName);
            }
        }


        return "redirect:/hotel/habitacion/{id}";
    }

    //convertir boolean en true o false
    public void chequearBooleanHabitacion(Habitacion h) {
        h.setBano(h.getBano() == null ? false : true);
        h.setVistas(h.getVistas() == null ? false : true);
    }


}