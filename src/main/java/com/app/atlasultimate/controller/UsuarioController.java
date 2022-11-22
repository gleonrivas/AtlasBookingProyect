package com.app.atlasultimate.controller;

import com.app.atlasultimate.controller.DTO.HabitacionDTO;
import com.app.atlasultimate.controller.DTO.UsuarioRegistroDTO;
import com.app.atlasultimate.model.*;
import com.app.atlasultimate.repository.*;
import com.app.atlasultimate.service.HabitacionService;
import com.app.atlasultimate.service.HotelService;
import com.app.atlasultimate.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("usuario")
public class UsuarioController {

//INYECTA SERVICIO HOTEL:

    @Autowired
    private HotelRepository repository;
    @Autowired
    private HotelService servicio;
    @Autowired
    private HabitacionService servicioHab;
    @Autowired
    private HabitacionRepository habitacionRepository;

    @Autowired
    private PensionRepository pensionRepository;

//PAGINA INICIO ROL ADMIN
    @GetMapping("inicio")
    public String inicio(Model modelo){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = usuarioRepository.findTopByEmail(auth.getName());
        List<Integer> listahabidhotel = habitacionRepository.idHotelPorHabitacion();
        modelo.addAttribute("id_hoteles", pensionRepository.listarPensionporidhotel());
        modelo.addAttribute("listhab",listahabidhotel);
        modelo.addAttribute("hoteles", repository.findHotelById_usuario(usuario.getId()));
        return "/InicioAdministrador.html";
    }

    @GetMapping("servicios")
    public String servs(){
        return "/ofertas_servicios_admin.html";
    }

    @GetMapping("informes")
    public String informes(){
        return "/InformesAdmin.html";
    }

    @GetMapping("facturas")
    public String facturas(){
        return "/FacturasAdmin.html";
    }

    @GetMapping("crearhotel")
    public String crearHotel(){
        return "/crearhotel.html";
    }

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @ModelAttribute("usuario")
    public UsuarioRegistroDTO retornarNuevoUsuario(){
            return new UsuarioRegistroDTO();
    }

    //ENVIAR A FORMULARIO REGISTRO
    @GetMapping("registro")
    public String regis(){
        return "/registroUsuario.html";
    }


    //RECIBIR DATOS USUARIO DE FORMULARIO REGISTRO
    @PostMapping("registro")
    public String registrarCuentaUsuario(@ModelAttribute("usuario") UsuarioRegistroDTO usuarioDTO){


        boolean existe = usuarioRepository.existsByEmail(usuarioDTO.getEmail());
        if(existe){
            return "redirect:/usuario/registro?fallo";
        }else {
            usuarioService.guardar(usuarioDTO);
            return "redirect:/usuario/registro?exito";
        }

    }


    //Eliminar hotel
    @GetMapping("/inicio/{id}")
    public String eliminarHotel (@PathVariable Integer id){

        Pension p = pensionRepository.pensionPorHotel(id);
        if (p != null){
            pensionRepository.delete(p);
        }
        servicio.eliminarHotel(id);
        return "redirect:/usuario/inicio";
    }

    @Autowired
    ReservaRepository reservaRepository;
    @Autowired
    HotelRepository hotelRepository;


    @GetMapping("perfil")
    public String perfil(Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = usuarioRepository.findTopByEmail(auth.getName());
        List<Registro> registros = reservaRepository.findAllByUsuario(usuario);
        model.addAttribute("usuario", usuario);
        model.addAttribute("reservas", registros);

        Map<HabitacionDTO, Registro> mapa = new HashMap<>();
        try{
            for (Registro registro: registros){
                Habitacion habitacion = habitacionRepository.findTopByRegistro(registro);
                HabitacionDTO habitacionDTO = new HabitacionDTO();
                habitacionDTO.setId(habitacion.getId());
                habitacionDTO.setImg(habitacion.getImg());
                Hotel hotel = hotelRepository.findTopByidHabitacion(habitacion.getId());
                habitacionDTO.setNombreHotel(hotel.getNombre());
                mapa.put(habitacionDTO, registro);
            }
        } catch (Exception e){
            System.out.println(e);
        }
        model.addAttribute("mapa", mapa);

        return "/PerfilUsuario.html";
    }


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    //RECIBIR DATOS USUARIO DE FORMULARIO REGISTRO
    @PostMapping("perfil")
    public String modificarUsuario(@ModelAttribute("usuario") UsuarioRegistroDTO usuarioDTO){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = usuarioRepository.findTopByEmail(auth.getName());

            usuarioRepository.updateByID(usuarioDTO.getNombre(),
                    usuarioDTO.getApellido(),
                    usuarioDTO.getDni(),
                    usuarioDTO.getEmail(),
                    usuarioDTO.getTelefono(),
                    passwordEncoder.encode(usuarioDTO.getContrasena()),
                    usuario.getId());

            return "redirect:/usuario/perfil?exito";
    }

    //Eliminar habitacion
    @DeleteMapping("/perfil/")
    public String eliminarReserva(@PathVariable String reserva) {
        Registro r = reservaRepository.registroporCodigo(reserva);
        r.setActiva(false);
        reservaRepository.save(r);
        return "redirect:/perfil/";
    }
}

