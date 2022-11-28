package com.app.atlasultimate.Utilidades;

import com.app.atlasultimate.model.Habitacion;
import com.app.atlasultimate.model.Hotel;
import com.app.atlasultimate.model.Rol;
import com.app.atlasultimate.model.Usuario;
import com.app.atlasultimate.repository.HotelRepository;
import com.app.atlasultimate.service.HotelService;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Time;
import java.util.ArrayList;
import java.util.*;

public class UtilidadesFaker {

    private static Faker faker = new Faker();

    @Autowired
    HotelService hotelService;


    public static Hotel crearHotel(){
        Random random = new Random();
        int millisInDay = 24*60*60*1000;
        Time time = new Time((long)random.nextInt(millisInDay));
        Time time2 = new Time((long)random.nextInt(millisInDay));
        Integer estrellas = (int)(Math.random()*10+1);
        if (estrellas > 6){
            estrellas = estrellas - 5;
        }
        String nombre = faker.name().name();
        String email = nombre + "gmail.com";


        Hotel hotel = new Hotel(nombre,faker.country().name(),faker.address().country(),faker.address().streetName(),
                estrellas,faker.number().numberBetween(1,9),email,faker.bool().bool(),faker.bool().bool(),
                faker.bool().bool(), faker.bool().bool(), faker.bool().bool(),faker.bool().bool(),time,time2,
                faker.bool().bool(),faker.bool().bool(),faker.bool().bool(),faker.bool().bool(),faker.bool().bool(),
                faker.bool().bool(),faker.bool().bool(),faker.bool().bool());
        return hotel;
    }
    public static Hotel crearHotelconId(){

        Random random = new Random();
        Integer id = (int)(Math.random()*10+1);
        int millisInDay = 24*60*60*1000;
        Time time = new Time((long)random.nextInt(millisInDay));
        Time time2 = new Time((long)random.nextInt(millisInDay));
        Integer estrellas = (int)(Math.random()*10+1);
        if (estrellas > 6){
            estrellas = estrellas - 5;
        }
        String nombre = faker.name().name();
        String email = nombre + "gmail.com";


        Hotel hotel = new Hotel(id,nombre,faker.country().name(),faker.address().country(),faker.address().streetName(),
                estrellas,faker.number().numberBetween(1,9),email,faker.bool().bool(),faker.bool().bool(),
                faker.bool().bool(), faker.bool().bool(), faker.bool().bool(),faker.bool().bool(),time,time2,
                faker.bool().bool(),faker.bool().bool(),faker.bool().bool(),faker.bool().bool(),faker.bool().bool(),
                faker.bool().bool(),faker.bool().bool(),faker.bool().bool());


        return hotel;
    }
    public static Usuario crearUsuario(Integer id_usuario) {
        Usuario user = crearUsuario();
        user.setId(id_usuario);
        return user;
    }

    public static List<Hotel> litarHoteles(Integer num_hoteles, Integer id_usuario){
        List<Hotel> lista = new ArrayList<>();
        Usuario user = crearUsuario();
        user.setId(id_usuario);
        for(int i=0; i<=num_hoteles; i++){
            Hotel hotel = crearHotel();
            hotel.setId_usuario(user);

            lista.add(hotel);
        }
        Set<Hotel> conjuntoHoteles = new HashSet<>(lista);
        user.setHotel(conjuntoHoteles);
        return lista;
    }

    public static List<Hotel> crearListaDeHotelesRandom(){
        int i = 0;
        List<Hotel> listaHoteles = new ArrayList<>();

        /*for (int i = 0; i>=10; i++){
            listaHoteles.add(crearHotel());
        }*/

        while (i >= 10){
            listaHoteles.add(crearHotel());
            i++;
        }

        return listaHoteles;
    }

    public static Hotel editarHotel(Hotel hotel){
        Random random = new Random();
        int millisInDay = 24*60*60*1000;
        Time time = new Time((long)random.nextInt(millisInDay));
        Time time2 = new Time((long)random.nextInt(millisInDay));
        Integer estrellas = (int)(Math.random()*10+1);
        if (estrellas > 6){
            estrellas = estrellas - 5;
        }
        String nombre = faker.name().name();
        String email = nombre + "@gmail.com";

        hotel = new Hotel(nombre,faker.country().name(),faker.address().country(),faker.address().streetName(),
                estrellas,faker.number().numberBetween(1,9),email,faker.bool().bool(),faker.bool().bool(),
                faker.bool().bool(), faker.bool().bool(), faker.bool().bool(),faker.bool().bool(),time,time2,
                faker.bool().bool(),faker.bool().bool(),faker.bool().bool(),faker.bool().bool(),faker.bool().bool(),
                faker.bool().bool(),faker.bool().bool(),faker.bool().bool());
        return hotel;
    }

    public static Usuario crearUsuario(){
        String nombre = faker.name().firstName();
        String email = nombre + "@gmail.com";
        Integer id = (int)(Math.random()*10+1);
        String dni = String.valueOf(faker.number().numberBetween(1,9)+ faker.name().name().charAt(0));
        String tel = String.valueOf(faker.number().numberBetween(1,9));
        Usuario user= new Usuario(id,faker.name().name(),faker.name().firstName(), dni, Rol.administrador,
               tel ,email,dni);

        return user;
    }

    public static Habitacion crearHabitacion(){
        Hotel hotel = crearHotel();

        Habitacion habitacion = new Habitacion(faker.number().numberBetween(0,10),faker.number().numberBetween(0,10),
                faker.number().randomDouble(2,1,3),null,faker.bool().bool(),faker.bool().bool(),
                faker.number().numberBetween(1,10),faker.number().numberBetween(1,10),hotel,null,null);
        return habitacion;

    }
    public static Habitacion crearHabitacionconId(){
        Hotel hotel = crearHotel();
        Integer id = faker.number().numberBetween(1,10);

        Habitacion habitacion = new Habitacion(id, faker.number().numberBetween(0,10),faker.number().numberBetween(0,10),
                faker.number().randomDouble(2,1,3),null,faker.bool().bool(),faker.bool().bool(),
                faker.number().numberBetween(1,10),faker.number().numberBetween(1,10),hotel,null,null);
        return habitacion;

    }
    public static Habitacion editarHabitacion(Habitacion habitacion){
        Hotel hotel = crearHotel();
        habitacion = new Habitacion(faker.number().numberBetween(0,10),faker.number().numberBetween(0,10),
                faker.number().randomDouble(2,1,3),null,faker.bool().bool(),faker.bool().bool(),
                faker.number().numberBetween(1,10),faker.number().numberBetween(1,10),hotel,null,null);
        return habitacion;
    }

    public static List<Habitacion> litarHabitacion(Integer num_hab){
        List<Habitacion> lista = new ArrayList<>();

        for(int i=0; i==num_hab; i++){
            Habitacion hab = crearHabitacion();
            lista.add(hab);
        }
        return lista;
    }
}
