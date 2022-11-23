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
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.UUID;


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
        if (pension==null){
            pension = new Pension(0.0,0.0,0.0,0.0);
        }
        if (temporada==null){
            temporada = new Temporada(0.0,0.0,0.0,0.0);
        }
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
        Integer num_codigo = reservaRepository.ultimoRegistro() + 1;
        String codigo = fecha1 + fecha2 + idHotel + idHab + duracion + "-" + num_codigo;
        Registro registroFinal = new Registro(fecha1,
                fecha2,
                num_personas,
                registroDTO.getT_pago(),
                registroDTO.getT_pension(),
                precioFinal,
                duracion,
                true,
                codigo,
                usuario,
                habitacion
                );

        CambiarReservasInactivas();
        reservaRepository.save(registroFinal);
        return "redirect:/historial?id_hab=" + idHab + "&" + "fecha_inicio=" + fechaInicio + "&" + "fecha_fin=" + fechaFin + "&" + "num_personas=" + num_personas;
        }



        @Autowired
        RegistroPasadoRepository registroPasadoRepository;


        public void CambiarReservasInactivas(){

            List<Registro> reservas = reservaRepository.findAll();
            for (Registro r : reservas) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(r.getF_entrada(), formatter);
                if (date.equals(LocalDate.now()) || r.getActiva().equals(false)) {
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
                    registroPasadoRepository.save(registroPasado);
                    reservaRepository.delete(r);
                }
            }
        }


}





