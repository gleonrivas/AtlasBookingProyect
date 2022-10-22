package com.app.atlasultimate.service;

import com.app.atlasultimate.model.Hotel;
import com.app.atlasultimate.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImp {

    @Autowired
    private HotelRepository repositorio;

    public List<Hotel> listarHoteles (){
        return repositorio.findAll();
    }

    public Hotel buscarHotel(Integer id){
        return repositorio.findHotelById(id);

    }
    public Hotel guardarHotel (Hotel hotel){
        return repositorio.save(hotel);
    }
    public Hotel obtenerHotelporId (Integer id){
        return repositorio.findById(id).get();
    }
    public Hotel actualizarHotel(Hotel hotel){

        return repositorio.save(hotel);
    }
    public void eliminarHotel(Integer id){
        repositorio.deleteById(id);
    }


}
