package com.app.atlasultimate.controller;

import com.app.atlasultimate.Utilidades.UtilidadesPrecio;
import com.app.atlasultimate.model.*;
import com.app.atlasultimate.repository.*;
import com.app.atlasultimate.service.HabitacionService;
import com.app.atlasultimate.service.HotelService;
import com.app.atlasultimate.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("graph")
public class graphqlController {
    @Autowired
    private HabitacionService servicio;
    @Autowired
    private HotelService hotelServicio;
    @Autowired
    private HabitacionRepository habitacionRepository;
    @Autowired
    private ReservaRepository reservaRepository;

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
    @Autowired
    private HotelService servicioHotel;


    @Autowired
    private TemporadaRepository temporadaRepository;


    @Autowired
    private PensionRepository pensionRepository;

    //GRAPHQLHABITACION

    //Crear y editar en graphql
    @PostMapping("/habitacion/crear/graphiql/")
    @SchemaMapping(typeName = "Mutation", value = "crearEditarHabitacion")
    public String crearEditarHabitacion(@RequestParam(required = false) @Argument Integer id_habitacion,
                                   @RequestParam(required = false) @Argument Integer id_hotel,
                                   @RequestParam @Argument Integer cama_individual,
                                   @RequestParam @Argument Integer cama_doble,
                                   @RequestParam @Argument Double precio_base,
                                   @RequestParam @Argument Integer num_maximo_personas,
                                   @RequestParam(required = false) @Argument Boolean bano,
                                   @RequestParam(required = false) @Argument Boolean vistas) {
        Habitacion habitacion = new Habitacion();
        Hotel hot = new Hotel();
        hot.setId(id_hotel);
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
                habitacion.setHotel(hot);
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
            habitacion.setHotel(hot);
            chequearBooleanHabitacion(habitacion);
            habitacionRepository.save(habitacion);
            return "Su habitacion se ha creado correctamente";
        }
    }

    public void chequearBooleanHabitacion(Habitacion h) {
        h.setBano(h.getBano() == null ? false : true);
        h.setVistas(h.getVistas() == null ? false : true);
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


    //GRAPHQL HOTEL

    //GRAPHQL

    //Listar habitacion
    @GetMapping("/habitacion/listar/")
    @SchemaMapping(typeName = "Query", value = "listarHabitacion")
    public List<Habitacion> listarHabitacion(@RequestParam @Argument Integer id_hotel) {
        return servicioHab.listarHabitacionbyIdHotel(id_hotel);
    }

    //Listar hotel

    @GetMapping("/hotel/listar/")
    @SchemaMapping(typeName = "Query", value = "listarHotel")
    public List<Hotel> listarHotel(@RequestParam @Argument Integer id_usuario) {
        List<Hotel> listarHotel = servicioHotel.listarHotel(id_usuario);
        return listarHotel;
    }

    //crear y editar Hotel
    @PostMapping("/hotel/crear/graphiql/")
    @SchemaMapping(typeName = "Mutation", value = "crearEditarHotel")
    public String crearEditarHotel(@RequestParam @Argument Integer id_hotel, @RequestParam @Argument String nombre, @RequestParam @Argument String ciudad,
                                   @RequestParam @Argument String pais, @RequestParam @Argument String direccion,
                                   @RequestParam @Argument Integer estrellas, @RequestParam @Argument Integer telefono,
                                   @RequestParam @Argument String email, @RequestParam @Argument Boolean cancelacion_g,
                                   @RequestParam(required = false) @Argument String img_url, @RequestParam(required = false) @Argument Boolean wifi,
                                   @RequestParam(required = false) @Argument Boolean mascotas, @RequestParam(required = false) @Argument Boolean multilengua,
                                   @RequestParam(required = false) @Argument Boolean accesibilidad, @RequestParam(required = false) @Argument Boolean servicio_habitacion,
                                   @RequestParam(required = false) @Argument OffsetTime horacomienzo_recepcion,
                                   @RequestParam(required = false) @Argument OffsetTime horafin_recepcion,
                                   @RequestParam(required = false) @Argument Boolean servicio_transporte, @RequestParam(required = false) @Argument Boolean tours,
                                   @RequestParam(required = false) @Argument Boolean comedor, @RequestParam(required = false) @Argument Boolean espectaculos,
                                   @RequestParam(required = false) @Argument Boolean patio, @RequestParam(required = false) @Argument Boolean piscina,
                                   @RequestParam(required = false) @Argument Boolean terraza, @RequestParam(required = false) @Argument Boolean parking
    ) {
        Time time1 = Time.valueOf(horacomienzo_recepcion.toLocalTime());
        Time time2 = Time.valueOf(horafin_recepcion.toLocalTime());

        Hotel hotel = new Hotel();
        if (id_hotel != null) {
            hotel = hotelRepository.findHotelById(id_hotel);
            if (hotel == null) {
                return "este hotel no existe";
            } else {
                hotel.setId(id_hotel);
                hotel.setNombre(nombre);
                hotel.setCiudad(ciudad);
                hotel.setPais(pais);
                hotel.setDireccion(direccion);
                hotel.setEstrellas(estrellas);
                hotel.setTelefono(telefono);
                hotel.setEmail(email);
                hotel.setCancelacion_g(cancelacion_g);
                hotel.setImg(img_url);
                hotel.setWifi(wifi);
                hotel.setMascotas(mascotas);
                hotel.setMultilengua(multilengua);
                hotel.setAccesibilidad(accesibilidad);
                hotel.setS_habitacion(servicio_habitacion);
                hotel.setHc_recepcion(time1);
                hotel.setHf_recepcion(time2);
                hotel.setS_transporte(servicio_transporte);
                hotel.setTours(tours);
                hotel.setComedor(comedor);
                hotel.setEspectaculos(espectaculos);
                hotel.setPatio(patio);
                hotel.setPiscina(piscina);
                hotel.setTerraza(terraza);
                hotel.setParking(parking);
                chequearBoolean(hotel);
                hotelRepository.save(hotel);
                return "Su hotel se ha editado correctamente";
            }
        } else {
            hotel.setId(id_hotel);
            hotel.setNombre(nombre);
            hotel.setCiudad(ciudad);
            hotel.setPais(pais);
            hotel.setDireccion(direccion);
            hotel.setEstrellas(estrellas);
            hotel.setTelefono(telefono);
            hotel.setEmail(email);
            hotel.setCancelacion_g(cancelacion_g);
            hotel.setImg(img_url);
            hotel.setWifi(wifi);
            hotel.setMascotas(mascotas);
            hotel.setMultilengua(multilengua);
            hotel.setAccesibilidad(accesibilidad);
            hotel.setS_habitacion(servicio_habitacion);
            hotel.setHc_recepcion(time1);
            hotel.setHf_recepcion(time2);
            hotel.setS_transporte(servicio_transporte);
            hotel.setTours(tours);
            hotel.setComedor(comedor);
            hotel.setEspectaculos(espectaculos);
            hotel.setPatio(patio);
            hotel.setPiscina(piscina);
            hotel.setTerraza(terraza);
            hotel.setParking(parking);
            chequearBoolean(hotel);
            hotelRepository.save(hotel);
            return "Su hotel se ha creado correctamente";
        }

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

    @DeleteMapping("/deleteHotel/graphiql/")
    @SchemaMapping(typeName = "Mutation", value = "eliminarHotel")
    public String eliminarHotel(@RequestParam(required = true) @Argument Integer id_hotel) {

        Hotel hotel = hotelRepository.findHotelById(id_hotel);

        if (hotel == null) {
            return "El hotel no existe";
        } else {
            List<Habitacion> listaPorIdHotel = servicioHab.listarHabitacionbyIdHotel(id_hotel);
            List<Review> listarReviewsPorIdHotel = reviewRepository.findReviewsHotel(id_hotel);
            Pension pensionporIdHotel = pensionRepository.pensionPorHotel(id_hotel);

            if (listaPorIdHotel.size() > 0) {
                return "Debe eliminar primero las habitaciones del hotel";
            }
            if (listarReviewsPorIdHotel.size() > 0) {
                for (Review r : listarReviewsPorIdHotel) {
                    r.setHotel(null);
                    reviewRepository.save(r);
                }
            }
            if (pensionporIdHotel != null) {
                pensionRepository.delete(pensionporIdHotel);
            }
            hotelRepository.deleteById(id_hotel);
            return "El hotel se ha borrado correctamente";
        }

    }

    //RESERVA

    @GetMapping("/crear/graphiql/")
    @SchemaMapping(typeName = "Mutation", value = "crearReserva")
    public String crearReserva(@RequestParam(required = true) @Argument LocalDate fecha_entrada,
                               @RequestParam(required = true) @Argument LocalDate fecha_salida,
                               @RequestParam(required = true) @Argument Integer num_personas,
                               @RequestParam(required = true) @Argument tipo_pago tipo_pago,
                               @RequestParam(required = true) @Argument tipo_pension tipo_pension,
                               @RequestParam(required = true) @Argument Integer id_habitacion,
                               @RequestParam(required = true) @Argument String email) {

        Registro registro = new Registro();
        registro.setF_entrada(fecha_entrada.toString());
        registro.setF_salida(fecha_salida.toString());
        registro.setN_personas(num_personas);
        registro.setT_pago(tipo_pago);
        registro.setT_pension(tipo_pension);
        registro.setHabitacion(habitacionRepository.findTopById(id_habitacion));
        registro.setUsuario(usuarioRepository.findTopByEmail(email));
        registro.setActiva(true);
        Usuario user = usuarioRepository.findTopByEmail(email);
        Integer id_hotel = habitacionRepository.idHotel(id_habitacion);
        Pension pension = pensionRepository.pensionPorHotel(id_hotel);
        Double precioPension = UtilidadesPrecio.booleanPrecioPension(pension, registro.getT_pension());
        Integer id_temporada = habitacionRepository.idTemporada(id_habitacion);
        Temporada temp = temporadaRepository.temporadaPorId(id_temporada);
        Double temp2 = UtilidadesPrecio.temporadaDouble(fecha_entrada, fecha_salida, temp);
        Habitacion habitacion = habitacionRepository.findTopById(id_habitacion);

        if (habitacion.equals(null)) {
            return "Esta habitación no existe";
        }
        if (registro.getUsuario().equals(null)) {
            return "Este usuario no existe";
        }
        if (user.getRol().equals(Rol.administrador)) {
            return "Este usuario no puede crear una reserva";
        }
        //calcular duracion
        Integer duracion = UtilidadesPrecio.duracionReserva(fecha_entrada, fecha_salida);
        registro.setN_dias(duracion);
        //calcular precio
        Double precio = UtilidadesPrecio.preciototal(num_personas, duracion, precioPension, temp2, habitacion.getPrecio_base());
        registro.setPrecio_total_dias(precio);
        reservaRepository.save(registro);
        return "Reserva realizada con éxito";

    }

    //BUSCADOR

    //GRAPHIQL buscador hotel
    @GetMapping("inicio/busqueda")
    @SchemaMapping(typeName = "Query", value = "buscadorHotel")
    public List<Hotel> buscadorHotel(@Argument LocalDate fecha_entrada,
                                     @Argument LocalDate fecha_salida,
                                     @Argument Integer num_personas,
                                     @Argument String ciudad) {

        String fechaEntrada = fecha_entrada.toString();
        String fechaSalida = fecha_salida.toString();
        List<Hotel> hoteles = servicioHotel.buscadorcompleto(fechaEntrada, fechaSalida, ciudad, num_personas);

        return hoteles;
    }

    //CREAR USUARIO
    //Crear y editar en graphql
    @PostMapping("/usuario/crear/graphiql/")
    @SchemaMapping(typeName = "Mutation", value = "crearEditarUsuario")
    public String crearEditarUsuario(@RequestParam(required = false) @Argument Integer id_usuario,
                                     @RequestParam(required = false) @Argument String nombre,
                                     @RequestParam(required = false) @Argument String apellido,
                                     @RequestParam(required = false) @Argument String dni,
                                     @RequestParam @Argument Rol rol,
                                     @RequestParam(required = false) @Argument String telefono,
                                     @RequestParam @Argument String email,
                                     @RequestParam @Argument String contrasena) {
        Usuario user = new Usuario();
        List<String> emails = usuarioRepository.emailUser();

        if (id_usuario != null) {
            user = usuarioRepository.usuarioporId(id_usuario);
            user.setNombre(nombre);
            user.setApellido(apellido);
            user.setDni(dni);
            user.setRol(rol);
            user.setTelefono(telefono);
            user.setEmail(email);
            user.setContrasena(contrasena);
            usuarioRepository.save(user);
            return "El usuario se ha editado correctamente";
        } else {
            for(String s :emails){
                if(s.equals(email)){
                    return "este usuario ya tiene una cuenta";
                }
            }
            user.setNombre(nombre);
            user.setApellido(apellido);
            user.setDni(dni);
            user.setRol(rol);
            user.setTelefono(telefono);
            user.setEmail(email);
            user.setContrasena(contrasena);
            usuarioRepository.save(user);
            return "El usuario se ha creado correctamente";
        }
    }

    //Mostrar Usuario
    @GetMapping("/Usuario/listar/")
    @SchemaMapping(typeName = "Query", value = "mostrarUsuario")
    public Usuario mostrarUsuario(@RequestParam @Argument String email) {
        return usuarioRepository.findTopByEmail(email);
    }

    //Delete user
    @DeleteMapping("/deleteUsuario/graphiql/")
    @SchemaMapping(typeName = "Mutation", value = "eliminarUsuario")
    public String eliminarUsuario(@RequestParam(required = true) @Argument String email) {

        Usuario user = usuarioRepository.findTopByEmail(email);

        if (user == null) {
            return "El usuario no existe";
        } else {
            List<Hotel> listadeHoteles = hotelRepository.findHotelById_usuario(user.getId());
            List<Review> listaReviewPorUser = reviewRepository.findReviewsUsuario(user.getId());
            List<Registro> listaRegistro = reservaRepository.listaregistroPorUsuario(user.getId());
            if(listadeHoteles.size()!=0){
                return "primero debe borrar los hoteles asociados al usuario";
            }else {
                if (listaReviewPorUser.size() != 0) {
                    for (Review r : listaReviewPorUser) {
                        r.setUsuario(null);
                        reviewRepository.save(r);
                    }
                }
                if(listaRegistro.size()!=0){
                    for(Registro registro: listaRegistro){
                        if(registro.getActiva().equals(true)){
                            return "el usuario debe eliminar las reservas activas que tiene";
                        }
                    }
                }
                usuarioRepository.delete(user);
                return "El usuario se ha eliminado correctamente";
            }
        }

    }

    //CancelarReserva
    @DeleteMapping("/deleteReserva/graphiql/")
    @SchemaMapping(typeName = "Mutation", value = "cancelarReserva")
    public String cancelarReserva(@RequestParam(required = true) @Argument String codigo_reserva
                                  ) {
        Registro reg = reservaRepository.registroporCodigo(codigo_reserva);
        if(reg == null){
            return "La reserva no existe";
        }
        if(reg.getActiva().equals(false)){
            return "reserva pasada";
        }
        reservaRepository.delete(reg);
        return "su reserva se ha cancelado correctamente";

    }
}
