package com.app.atlasultimate.registro;

import com.app.atlasultimate.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClienteServicioImpl implements ClienteServicio{


    private BCryptPasswordEncoder passwordEncoder;

    private ClienteRepository clienteRepository;

    public ClienteServicioImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente GuardarCliente(ClienteRegistroDTO registroDTO) {
        Cliente cliente = new Cliente( registroDTO.getNombre(), registroDTO.getApellidos(), registroDTO.getDni(),registroDTO.getTelefono(),passwordEncoder.encode(registroDTO.getContrasena()),registroDTO.getEmail());
        return clienteRepository.save(cliente);

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Cliente cliente = clienteRepository.findByEmail(username);
        if(cliente == null){
            throw new UsernameNotFoundException("Usuario o contraseña no validos");
        }
        return (UserDetails) new Cliente(cliente.getContrasena(),cliente.getEmail());


    }


}
