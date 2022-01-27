package ar.com.softtek.academytp.security.services;

import ar.com.softtek.academytp.models.Cliente;
import ar.com.softtek.academytp.models.Empleado;
import ar.com.softtek.academytp.repositories.ClienteRepository;
import ar.com.softtek.academytp.repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Cliente> oc = clienteRepository.findByUsername(username);
        Optional<Empleado> oe = empleadoRepository.findByUsername(username);

        if (oc.isPresent()){
            return UserDetailsImpl.build(oc.get());
        }
        if (oe.isPresent()) {
            return UserDetailsImpl.build(oe.get());
        }

        throw new UsernameNotFoundException("Usuario no encontrado: " + username);
    }
}