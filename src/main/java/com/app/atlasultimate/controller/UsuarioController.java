package com.app.atlasultimate.controller;

import com.app.atlasultimate.controller.DTO.HabitacionDTO;
import com.app.atlasultimate.controller.DTO.UsuarioRegistroDTO;
import com.app.atlasultimate.model.*;
import com.app.atlasultimate.repository.*;
import com.app.atlasultimate.security.Oauth2User;
import com.app.atlasultimate.security.Oauth2UserService;
import com.app.atlasultimate.service.HabitacionService;
import com.app.atlasultimate.service.HotelService;
import com.app.atlasultimate.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.user.OAuth2User;
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
    public Usuario retornarNuevoUsuario(){
            return new Usuario();
    }

    //ENVIAR A FORMULARIO REGISTRO
    @GetMapping("registro")
    public String regis(){
        return "/registroUsuario.html";
    }


    //RECIBIR DATOS USUARIO DE FORMULARIO REGISTRO
    @PostMapping("registro")
    public String registrarCuentaUsuario(@ModelAttribute("usuario") Usuario usuario){


        boolean existe = usuarioRepository.existsByEmail(usuario.getEmail());
        if(existe){
            return "redirect:/usuario/registro?fallo";
        }else {
            usuarioService.guardar(usuario);
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
    @Autowired
    Oauth2UserService oauth2UserService;


    @GetMapping("perfil")
    public String perfil(Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = usuarioRepository.findTopByEmail(auth.getName());
        Rol rol = Rol.usuario;

        Oauth2User oauth2User = null;
        if (usuario == null) {
            oauth2User = (Oauth2User) auth.getPrincipal();
            if (oauth2User != null) {
                if (usuarioRepository.findTopByEmail(oauth2User.getEmail()) == null) {
                    Usuario insertUser = new Usuario();
                    insertUser.setNombre(oauth2User.getFullName());
                    insertUser.setEmail(oauth2User.getEmail());
                    insertUser.setRol(Rol.usuario);
                    usuario = usuarioRepository.save(insertUser);
                    rol = usuario.getRol();

                } else {
                    usuario = usuarioRepository.findTopByEmail(oauth2User.getEmail());

                }
            }


        }else {
            rol = usuario.getRol();
        }

        model.addAttribute("rol", rol.toString());


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
    public String modificarUsuario(@ModelAttribute("usuario") Usuario usuarioDTO){

        usuarioService.editarUsuario(usuarioDTO);

            return "redirect:/usuario/perfil?exito";
    }

    //Eliminar reserva

    @Autowired
    RegistroPasadoRepository registroPasadoRepository;

    @PostMapping("cancelar_reserva")
    public String eliminarReserva(@RequestParam (value = "reserva") String reserva) {
        Registro r = reservaRepository.registroporCodigo(reserva);
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
        return "redirect:/usuario/perfil";
    }
}

