package com.app.atlasultimate.controller;

import com.app.atlasultimate.controller.DTO.ReviewDTO;
import com.app.atlasultimate.model.*;
import com.app.atlasultimate.repository.*;
import com.app.atlasultimate.service.HabitacionService;
import com.app.atlasultimate.service.HotelService;
import com.app.atlasultimate.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.Transient;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.sql.Time;
import java.util.HashMap;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("hotel")
public class HotelController {

    @ModelAttribute("usuario")
    public Usuario usuario(){
        return new Usuario();
    }

    @Autowired
    private HabitacionService servicio;

    @Autowired
    private HotelService servicioHotel;


    @Autowired
    private TemporadaRepository temporadaRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private PensionRepository pensionRepository;


    @GetMapping("/habitacion/{id_hotel}")
    public String leerHabitaciones(@PathVariable Integer id_hotel, Model model) {
        List<Habitacion> listadeHabitacion = servicio.listarHabitacionbyIdHotel(id_hotel);
        Hotel hotel = servicioHotel.obtenerHotelporId(id_hotel);
        List<Temporada> listaTemporadas = temporadaRepository.listaTemporadas();
        List<Integer> listaIdHavitacionReserva = reservaRepository.listaidHabporRegistro();
        model.addAttribute("habitaciones", listadeHabitacion);
        model.addAttribute("hotel", hotel);
        model.addAttribute("temporadas", listaTemporadas);
        model.addAttribute("listaid", listaIdHavitacionReserva);


        return "/AdminHabitaciones.html";


    }

    @GetMapping("nuevo")
    public String nuevoHotel(Model modelo) {
        Hotel hotel = new Hotel();
        modelo.addAttribute("hotel", hotel);
        return "/crearhotel.html";
    }


    //crear hoteles
    @PostMapping(path="nuevo")
    public String guardarHotel(@ModelAttribute("hotel") Hotel hotel, @RequestParam("file") MultipartFile file) throws IOException {
        chequearBoolean(hotel);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = usuarioRepository.findTopByEmail(auth.getName());
        hotel.setId_usuario(usuario);
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        hotel.setImg(fileName);
        Hotel nuevoHotel = servicioHotel.guardarHotel(hotel);
        String uploadDir = "./src/main/resources/static/img/imgHot/" + nuevoHotel.getId();
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


        return "redirect:/hotel/nuevo?exito";
    }



    //cargar hotel en formulario para editarlo
    @GetMapping("/editar/{id}")
    public String editarHotel(@PathVariable Integer id, Model model) {
        model.addAttribute("hotel", servicioHotel.obtenerHotelporId(id));
        return "/editarHotel.html";
    }

    //Peticion post para enviar la info cambiada del hotel
    @PostMapping("/editar/{id}")
    public String actualizarHotel(@PathVariable Integer id, @ModelAttribute("hotel") Hotel hotel, Model modelo) {
        Hotel hotelexistente = servicioHotel.obtenerHotelporId(id);
        hotelexistente.setId(id);
        hotelexistente.setNombre(hotel.getNombre());
        hotelexistente.setPais(hotel.getPais());
        hotelexistente.setCiudad(hotel.getCiudad());
        hotelexistente.setDireccion((hotel.getDireccion()));
        hotelexistente.setEstrellas(hotel.getEstrellas());
        hotelexistente.setMultilengua(hotel.getMultilengua());
        hotelexistente.setTerraza(hotel.getTerraza());
        hotelexistente.setPiscina(hotel.getPiscina());
        hotelexistente.setPatio(hotel.getPatio());
        hotelexistente.setEspectaculos(hotel.getEspectaculos());
        hotelexistente.setComedor(hotel.getComedor());
        hotelexistente.setTours(hotel.getTours());
        hotelexistente.setParking((hotel.getParking()));
        hotelexistente.setS_transporte(hotel.getS_transporte());
        hotelexistente.setS_habitacion(hotel.getS_habitacion());
        hotelexistente.setMascotas(hotel.getMascotas());
        hotelexistente.setTelefono(hotel.getTelefono());
        hotelexistente.setAccesibilidad(hotel.getAccesibilidad());
        hotelexistente.setHc_recepcion(hotel.getHc_recepcion());
        hotelexistente.setHf_recepcion(hotel.getHf_recepcion());
        hotelexistente.setWifi(hotel.getWifi());
        hotelexistente.setCancelacion_g(hotel.getCancelacion_g());
        hotelexistente.setLatitud(hotel.getLatitud());
        hotelexistente.setLongitud(hotel.getLongitud());
        chequearBoolean(hotelexistente);
        servicioHotel.actualizarHotel(hotelexistente);

        return "redirect:/usuario/inicio";

    }

    private void chequearBoolean(Hotel h) {
        h.setTerraza(h.getTerraza() == null ? false : true);
        h.setPiscina(h.getPiscina() == null ? false : true);
        h.setPatio(h.getPatio() == null ? false : true);
        h.setEspectaculos(h.getEspectaculos() == null ? false : true);
        h.setComedor(h.getComedor() == null ? false : true);
        h.setTours(h.getTours() == null ? false : true);
        h.setParking(h.getParking() == null ? false : true);
        h.setS_transporte(h.getS_transporte() == null ? false : true);
        h.setS_habitacion(h.getS_habitacion() == null ? false : true);
        h.setMascotas(h.getMascotas() == null ? false : true);
        h.setAccesibilidad(h.getAccesibilidad() == null ? false : true);
        h.setWifi(h.getWifi() == null ? false : true);
        h.setCancelacion_g(h.getCancelacion_g() == null ? false : true);
        h.setMultilengua(h.getMultilengua() == null ? false : true);
    }
    //cargar habitacion en formulario para editarla

    @GetMapping("/editarhabitacion/{id_habitacion}")
    public String editarHabitacion(@PathVariable Integer id_habitacion, Model model) {
        Integer idhotel = servicioHotel.obtenerIdHotel(id_habitacion);
        model.addAttribute("habitacion", servicio.obtenerHabitacionporId(id_habitacion));
        model.addAttribute("idhotel", idhotel);
        model.addAttribute("id_habitacion", id_habitacion);
        return "/editarHabitacion.html";
    }

    //Peticionpost para enviar la info cambiada de la habitacion
    @PostMapping("/editarhabitacion/{id_habitacion}")
    public String actualizarHabitacion(@ModelAttribute("id_habitacion") Integer id_habitacion,
                                       @ModelAttribute("habitacion") Habitacion hab) {
        Habitacion habitacionexistente = servicio.obtenerHabitacionporId(id_habitacion);
        habitacionexistente.setN_max_personas(hab.getN_max_personas());
        habitacionexistente.setC_individual(hab.getC_individual());
        habitacionexistente.setC_doble(hab.getC_doble());
        habitacionexistente.setPrecio_base(hab.getPrecio_base());
        habitacionexistente.setBano(hab.getBano());
        habitacionexistente.setVistas(hab.getVistas());
        chequearBooleanHabitacion(habitacionexistente);
        servicio.actualizarHabitacion(habitacionexistente);

        return "redirect:/hotel/editarhabitacion/"+ id_habitacion;
    }
    public void chequearBooleanHabitacion(Habitacion h) {
        h.setBano(h.getBano() == null ? false : true);
        h.setVistas(h.getVistas() == null ? false : true);
    }
    //Eliminar habitacion
    @PostMapping("/habitacion/{id_hotel}")
    public String eliminarHab(@PathVariable Integer id_hotel,
                              @ModelAttribute("habitacion") Habitacion habitacion) {
        servicio.eliminarHabitacion(habitacion.getId());
        return "redirect:/hotel/habitacion/"+id_hotel;
    }

    @Autowired
    private HabitacionRepository repository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private HabitacionService servicioHab;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewRepository reviewRepository;

    @ModelAttribute("review")
    public ReviewDTO reviewRegistroDTO(){
        return new ReviewDTO();

    }

    @GetMapping("/habitaciones/")
    public String filtrarHabitaciones(@RequestParam(value = "id") Integer id, Model model, @ModelAttribute("hotel") Hotel hot,
                                      @RequestParam(value = "fecha_inicio", required = false) String fechaInicio,
                                      @RequestParam(value = "fecha_fin", required = false) String fechaFin,
                                      @RequestParam(value = "num_personas", required = false) Integer num_personas){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = usuarioRepository.findTopByEmail(auth.getName());
        model.addAttribute("usuario", usuario);
        model.addAttribute("resena" , new ReviewDTO());
        model.addAttribute("fecha_inicio", fechaInicio);
        model.addAttribute("fecha_fin", fechaFin);
        model.addAttribute("num_personas", num_personas);
        Hotel hotel = hotelRepository.findHotelById(id);
        model.addAttribute("hotel", hotel);
        List<Habitacion> habitaciones = repository.findAllByReservasInactivas(id);
        habitaciones.addAll(repository.findAllByReservasInactivas2(id));
        model.addAttribute("habitaciones", habitaciones);
        List<Review> review = reviewRepository.findValues(id);
        model.addAttribute("review", review);
        Map<String, Review> mapa = new HashMap<>();
        try{
            for (int i = 0; i < review.size(); i++){
                mapa.put(review.get(i).getUsuario().getNombre(), review.get(i));
            }
        } catch (Exception e){
            System.out.println(e);
        }
        model.addAttribute("mapa", mapa);
        String fondo1 = hotelRepository.findHotelById(id).getImg();
        String fondo = "/img/imgHot/" + id+ "/" + fondo1;
        model.addAttribute("hotelimagen", fondo);



        return "/hotel.html";
    }



    @PostMapping("/habitaciones/")
    public String guardarReview(@RequestParam(value = "id") Integer id,
                                @ModelAttribute("resena") ReviewDTO reviewDTO,
                                @RequestParam(value = "fecha_inicio", required = false) String fechaInicio,
                                @RequestParam(value = "fecha_fin", required = false) String fechaFin,
                                @RequestParam(value = "num_personas", required = false) Integer num_personas) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = usuarioRepository.findTopByEmail(auth.getName());

        reviewDTO.setId_usuario(usuario);
        reviewDTO.setId_hotel(hotelRepository.findHotelById(id));
        reviewService.guardarReview(reviewDTO);
        return "redirect:/hotel/habitaciones/?id=" + id + "&" + "fecha_inicio=" + fechaInicio + "&" + "fecha_fin=" + fechaFin + "&" + "num_personas=" + num_personas;

    }

    @PostMapping("/")
    public String filtrarHotel(@ModelAttribute(value = "id_hab") Integer id_hab, Model modelo,
                               @ModelAttribute(value = "fecha_inicio") String fechaInicio,
                               @ModelAttribute(value = "fecha_fin") String fechaFin) {
        String fecha1 = fechaInicio;
        String fecha2 = fechaFin;
        Habitacion h = servicioHab.obtenerHabitacionporId(id_hab);
        modelo.addAttribute("h", h);
        modelo.addAttribute("fecha_inicio", fecha1);
        modelo.addAttribute("fecha_fin", fecha2);

        return "/hotel.html";

    }





}
