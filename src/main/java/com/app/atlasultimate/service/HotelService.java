package com.app.atlasultimate.service;

import com.app.atlasultimate.model.Hotel;
import com.app.atlasultimate.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class HotelService {

    @Autowired
    private HotelRepository repositorio;

    

    public Hotel buscarHotel(Integer id){
        return repositorio.findHotelById(id);

    }

    public Hotel guardarHotel (Hotel hotel){
        return repositorio.save(hotel);
    }
    public Hotel obtenerHotelporId (Integer id){
        return repositorio.findTopById(id);
    }
    public Hotel actualizarHotel(Hotel hotel){

        return repositorio.save(hotel);
    }

    public void eliminarHotel(Integer id){
        repositorio.deleteById(id);
    }

    public Integer obtenerIdHotel(Integer id){
        return repositorio.findHotelByIdHab(id);
    }
    public List<Hotel> listarHotel(Integer id_usuario){
        return repositorio.findHotelById_usuario(id_usuario);
    }

    public List<Hotel> buscador( Date fecha_inicio,
                                 Date fecha_fin,
                                 String ciudad,
                                 Integer n_max_personas){
        return repositorio.buscadorgraphiql(fecha_inicio, fecha_fin, ciudad, n_max_personas);
    }
    //nuevo buscador
    public List<Hotel> buscadorcompleto (String fecha_inicio,
                                         String fecha_fin,
                                         String ciudad,
                                         Integer n_max_personas){

        List<Hotel> listadefinitiva= new ArrayList<>();

        List<Hotel> listabuscador1= repositorio.primerBuscador(fecha_fin,fecha_fin,ciudad,n_max_personas);
        List<Hotel> listabuscador2= repositorio.segundoBuscador(ciudad,n_max_personas);

        listadefinitiva.addAll(listabuscador1);
        listadefinitiva.addAll(listabuscador2);

        return listadefinitiva;
    }

}
