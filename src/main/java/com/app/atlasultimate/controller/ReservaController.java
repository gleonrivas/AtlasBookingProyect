package com.app.atlasultimate.controller;
import com.app.atlasultimate.Utilidades.UtilidadesHabitacion;
import com.app.atlasultimate.Utilidades.UtilidadesPrecio;
import com.app.atlasultimate.model.*;
import com.app.atlasultimate.repository.ReservaRepository;
import com.app.atlasultimate.repository.UsuarioRepository;
import com.app.atlasultimate.service.HabitacionService;
import com.app.atlasultimate.service.HotelService;
import com.app.atlasultimate.service.PensionService;
import com.app.atlasultimate.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;



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
                habitacion);

        if (reserva == null){
            return "/reservas.html";
        }else {
            reservaRepository.save(registroFinal);
            modelo.addAttribute("registro", registroFinal);
            return "/HistorialReserva.html";

        }


    }





}
