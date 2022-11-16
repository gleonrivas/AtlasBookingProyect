package com.app.atlasultimate.controller;
import com.app.atlasultimate.Utilidades.UtilidadesHabitacion;
import com.app.atlasultimate.Utilidades.UtilidadesPrecio;
import com.app.atlasultimate.controller.DTO.RegistroDTO;
import com.app.atlasultimate.model.*;
import com.app.atlasultimate.repository.*;
import com.app.atlasultimate.service.HabitacionService;
import com.app.atlasultimate.service.HotelService;
import com.app.atlasultimate.service.PensionService;
import com.app.atlasultimate.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


@Controller
@RequestMapping("reserva")
public class ReservaController {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private HotelService servicioHotel;
    @Autowired
    private HabitacionService servicioHab;
    @Autowired
    private PensionService servicioPension;
    @Autowired
    private ReservaService servicioReserva;
    @Autowired
    ReservaRepository reservaRepository;
    @Autowired
    private HabitacionRepository habitacionRepository;
    @Autowired
    private PensionRepository pensionRepository;
    @Autowired
    private TemporadaRepository temporadaRepository;


    @GetMapping("datos")
    public String registroreserva(@ModelAttribute(value = "id_hab") Integer idHab,
                                  @RequestParam (value = "fecha_inicio", required = false) String fechaInicio,
                                  @RequestParam(value = "fecha_fin", required = false) String fechaFin,
                                  @RequestParam(value = "num_personas", required=false) Integer num_personas,
                                  Model modelo) {

        RegistroDTO registroDTO = new RegistroDTO();
        modelo.addAttribute("registroDTO" , registroDTO);

        String fecha1 = fechaInicio;
        String fecha2 = fechaFin;
        Integer idHotel = servicioHotel.obtenerIdHotel(idHab);
        Hotel hotel = servicioHotel.obtenerHotelporId(idHotel);
        Integer id_pension = servicioPension.pensionporIdHotel(hotel.getId());
        Pension pension = servicioPension.pensionporId(id_pension);
        Habitacion habitacion = servicioHab.obtenerHabitacionporId(idHab);
        Integer duracion = UtilidadesPrecio.duracionReserva(LocalDate.parse(fecha1), LocalDate.parse(fecha2));
        Temporada temporada = servicioReserva.temporadaporId(idHab);
        Double precio = UtilidadesPrecio.precioSemiFinal(LocalDate.parse(fecha1), LocalDate.parse(fecha2),
                temporada, habitacion.getPrecio_base());
        String tipoHabitacion = UtilidadesHabitacion.tipoHabitacion(habitacion.getC_individual(), habitacion.getC_doble());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = usuarioRepository.findTopByEmail(auth.getName());



        modelo.addAttribute("hotel", hotel);
        modelo.addAttribute("habitacion", habitacion);
        modelo.addAttribute("pensiondto", pension);
        modelo.addAttribute("fecha_inicio", fecha1);
        modelo.addAttribute("fecha_fin", fecha2);
        modelo.addAttribute("duracion", duracion);
        modelo.addAttribute("precio", precio);
        modelo.addAttribute("num_personas", num_personas);
        modelo.addAttribute("tipoHabitacion", tipoHabitacion);
        modelo.addAttribute("usuario", usuario);
        modelo.addAttribute("tarjeta" ,tipo_pago.tarjeta);
        modelo.addAttribute("efectivo" ,tipo_pago.efectivo);

        return "/reservas.html";



    }

    @PostMapping("datos")
    public String GuardarReserva(@ModelAttribute("registroDTO") RegistroDTO registroDTO,
                                 @RequestParam(value = "id_hab") Integer idHab,
                                 @RequestParam (value = "fecha_inicio", required = false) String fechaInicio,
                                 @RequestParam(value = "fecha_fin", required = false) String fechaFin,
                                 @RequestParam(value = "num_personas", required=false) Integer num_personas) {
        String fecha1= fechaInicio;
        String fecha2= fechaFin;
        Integer idHotel = servicioHotel.obtenerIdHotel(idHab);
        Hotel hotel = servicioHotel.obtenerHotelporId(idHotel);
        Integer id_pension = servicioPension.pensionporIdHotel(hotel.getId());
        Pension pension = servicioPension.pensionporId(id_pension);
        Habitacion habitacion = servicioHab.obtenerHabitacionporId(idHab);
        Integer duracion = UtilidadesPrecio.duracionReserva(LocalDate.parse(fecha1), LocalDate.parse(fecha2));
        Temporada temporada = servicioReserva.temporadaporId(idHab);
        Double precio = UtilidadesPrecio.precioSemiFinal(LocalDate.parse(fecha1), LocalDate.parse(fecha2),
                temporada, habitacion.getPrecio_base());
        String tipoHabitacion = UtilidadesHabitacion.tipoHabitacion(habitacion.getC_individual(), habitacion.getC_doble());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = usuarioRepository.findTopByEmail(auth.getName());


        Double precioFinal = 0.0;
        if (registroDTO.getT_pension() == tipo_pension.ad) {
            precioFinal = precio + (pension.getAd() * duracion);
        }
        if (registroDTO.getT_pension() == tipo_pension.sa) {
            precioFinal = precio + (pension.getSa() * duracion);
        }
        if (registroDTO.getT_pension() == tipo_pension.pc) {
            precioFinal = precio + (pension.getPc() * duracion);
        }
        if (registroDTO.getT_pension() == tipo_pension.mp) {
            precioFinal = precio + (pension.getMp() * duracion);
        }
        precioFinal = precioFinal * num_personas;

        Registro registroFinal = new Registro(fecha1,
                fecha2,
                num_personas,
                registroDTO.getT_pago(),
                registroDTO.getT_pension(),
                precioFinal,
                duracion,
                usuario,
                habitacion,
                true);


        reservaRepository.save(registroFinal);
        return "redirect:/historial?id_hab=" + idHab + "&" + "fecha_inicio=" + fechaInicio + "&" + "fecha_fin=" + fechaFin + "&" + "num_personas=" + num_personas;


        }



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
        Double precioPension = UtilidadesPrecio.booleanPrecioPension(pension,registro.getT_pension());
        Integer id_temporada = habitacionRepository.idTemporada(id_habitacion);
        Temporada temp = temporadaRepository.temporadaPorId(id_temporada);
        Double temp2 = UtilidadesPrecio.temporadaDouble(fecha_entrada, fecha_salida, temp);
        Habitacion habitacion = habitacionRepository.findTopById(id_habitacion);

        if(habitacion.equals(null)){
            return "Esta habitación no existe";
        }
        if(registro.getUsuario().equals(null)){
            return "Este usuario no existe";
        }
        if(user.getRol().equals(Rol.administrador)){
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

}
