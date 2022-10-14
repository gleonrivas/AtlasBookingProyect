package com.app.atlasultimate.service;

import com.app.atlasultimate.controller.DTO.AdministradorRegistroDTO;
import com.app.atlasultimate.model.Administrador;
import com.app.atlasultimate.model.Hotel;
import com.app.atlasultimate.repository.AdministradorRepository;
import com.app.atlasultimate.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministradorServiceImpl implements AdministradorService{

    @Autowired
    private AdministradorRepository usuarioRepositorio;

    @Override
    public Administrador save(AdministradorRegistroDTO resitroDTO) {
        Administrador admin= new Administrador(resitroDTO.getNombre(), resitroDTO.getApellidos(), resitroDTO.getDni(),
                resitroDTO.getNumTelefono(), resitroDTO.getEmail(),resitroDTO.getContrasena());
        return usuarioRepositorio.save(admin);
    }


}
