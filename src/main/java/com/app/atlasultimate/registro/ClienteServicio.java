package com.app.atlasultimate.registro;

import com.app.atlasultimate.model.Cliente;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface ClienteServicio extends UserDetailsService {

    public Cliente GuardarCliente(ClienteRegistroDTO registroDTO);


}
