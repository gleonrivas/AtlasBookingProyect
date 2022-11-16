package com.app.atlasultimate.controller;
import com.app.atlasultimate.Utilidades.UtilidadesHabitacion;
import com.app.atlasultimate.Utilidades.UtilidadesPrecio;
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


    @GetMapping("datos/")
    public String registroreserva(@ModelAttribute(value = "id_hab") Integer idHab,
                                  @RequestParam (value = "fecha_inicio", required = false) String fechaInicio,
                                  @RequestParam(value = "fecha_fin", required = false) String fechaFin,
                                  @RequestParam(value = "num_personas", required=false) Integer num_personas,
                                  @RequestParam(value = "reserva", required=false) Integer reserva ,
                                  @RequestParam(value = "ad", required=false) Integer ad ,
                                  @RequestParam(value = "mp", required=false) Integer mp ,
                                  @RequestParam(value = "pc", required=false) Integer pc ,
                                  @RequestParam(value = "sa", required=false) Integer sa ,
                                  @RequestParam(value = "tarjeta", required=false) Integer tarjeta ,
                                  @RequestParam(value = "efectivo", required=false) Integer efectivo ,
                                  Model modelo) {
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



        modelo.addAttribute("hotel", hotel);
        modelo.addAttribute("habitacion", habitacion);
        modelo.addAttribute("pension", pension);
        modelo.addAttribute("fecha_inicio",fecha1);
        modelo.addAttribute("fecha_fin", fecha2);
        modelo.addAttribute("duracion", duracion);
        modelo.addAttribute("precio", precio);
        modelo.addAttribute("num_personas", num_personas);
        modelo.addAttribute("tipoHabitacion", tipoHabitacion);
        modelo.addAttribute("usuario", usuario);
        modelo.addAttribute("tarjeta" ,tipo_pago.tarjeta);
        modelo.addAttribute("efectivo" ,tipo_pago.efectivo);


        Registro registro = new Registro();

        if (pc!=null){
            registro.setT_pension(tipo_pension.pc);
        }else if (ad!=null){
            registro.setT_pension(tipo_pension.ad);
        }else if (mp!=null){
            registro.setT_pension(tipo_pension.mp);
        }else if (sa!=null){
            registro.setT_pension(tipo_pension.sa);
        }else {
            reserva = null;
        }

        if (tarjeta != null){
            registro.setT_pago(tipo_pago.tarjeta);
        }else if (efectivo != null){
            registro.setT_pago(tipo_pago.efectivo);
        }else {
            reserva = null;
        }


        Double precioFinal = 0.0;
        if (registro.getT_pension() == tipo_pension.ad) {
            precioFinal = precio + (pension.getAd() * duracion);
        }
        if (registro.getT_pension() == tipo_pension.sa) {
            precioFinal = precio + (pension.getSa() * duracion);
        }
        if (registro.getT_pension() == tipo_pension.pc) {
            precioFinal = precio + (pension.getPc() * duracion);
        }
        if (registro.getT_pension() == tipo_pension.mp) {
            precioFinal = precio + (pension.getMp() * duracion);
        }
        precioFinal = precioFinal * num_personas;

        Registro registroFinal = new Registro(fecha1,
                fecha2,
                num_personas,
                registro.getT_pago(),
                registro.getT_pension(),
                precioFinal,
                duracion,
                usuario,
                habitacion,
                true);

        if (reserva == null){
            return "/reservas.html";
        }else {
            reservaRepository.save(registroFinal);
            modelo.addAttribute("registro", registroFinal);
            return "/HistorialReserva.html";

        }


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
