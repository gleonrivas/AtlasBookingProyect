package com.app.atlasultimate.service;

import com.app.atlasultimate.controller.DTO.AdministradorRegistroDTO;
import com.app.atlasultimate.model.Administrador;


public interface AdministradorService  {
    public Administrador save(AdministradorRegistroDTO resitroDTO);
}
