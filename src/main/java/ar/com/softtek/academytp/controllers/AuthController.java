package ar.com.softtek.academytp.controllers;

import ar.com.softtek.academytp.dto.JwtResponse;
import ar.com.softtek.academytp.dto.LoginRequest;
import ar.com.softtek.academytp.dto.MessageResponse;
import ar.com.softtek.academytp.dto.SignupRequest;
import ar.com.softtek.academytp.models.Cliente;
import ar.com.softtek.academytp.models.Empleado;
import ar.com.softtek.academytp.models.TipoCliente;
import ar.com.softtek.academytp.repositories.ClienteRepository;
import ar.com.softtek.academytp.repositories.EmpleadoRepository;
import ar.com.softtek.academytp.security.jwt.JwtUtils;
import ar.com.softtek.academytp.security.services.UserDetailsImpl;
import ar.com.softtek.academytp.dto.SignupEmpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    EmpleadoRepository empleadoRepository;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                new JwtResponse(
                        jwt,
                        userDetails.getId(),
                        userDetails.getUsername(),
                        roles
                )
        );
    }

    @PostMapping("/signupcli")
    @Transactional
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (clienteRepository.existsByUsername(signUpRequest.getUsername())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Ya existe el usuario!"));
        }

        Cliente cliente = new Cliente();
        cliente.setUsername(signUpRequest.getUsername());
        cliente.setPassword(encoder.encode(signUpRequest.getPassword()));
        cliente.setNombre(signUpRequest.getNombre());
        cliente.setIsAdmin(false);
        cliente.setIsEmpleado(false);
        if (signUpRequest.getTipoCliente().equals("PARTICULAR")){
            cliente.setTipoCliente(TipoCliente.Particular);
            cliente.setDni(signUpRequest.getDni());
            cliente.setApellido(signUpRequest.getApellido());
        } else if (signUpRequest.getTipoCliente().equals("EMPRESA")) {
            cliente.setTipoCliente(TipoCliente.Empresa);
            cliente.setRazonSocial(signUpRequest.getRazonSocial());
            cliente.setCuit(signUpRequest.getCuit());
        }

        clienteRepository.save(cliente);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    /*@PostMapping("/datedealtaunadminpapaa")
    @Transactional
    public boolean registerFirstAdmin() {
        Empleado empleado = new Empleado();
        empleado.setUsername("admin");
        empleado.setPassword(encoder.encode("admin123456"));
        empleado.setNombre("Luke");
        empleado.setApellido("Ff");
        empleado.setIsEmpleado(true);
        empleado.setIsAdmin(true);
        empleadoRepository.save(empleado);
        Optional<Empleado> e = this.empleadoRepository.findByUsername("admin");
        if (e.isPresent()){
            return true;
        } else {
            return false;
        }
    }*/

    @PostMapping("/signupemp")
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public ResponseEntity<?> registerEmpleado(@Valid @RequestBody SignupEmpRequest signupEmpRequest) {
        if (empleadoRepository.existsByUsername(signupEmpRequest.getUsername())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Ya existe el usuario!"));
        }

        Empleado empleado = new Empleado();
        empleado.setUsername(signupEmpRequest.getUsername());
        empleado.setPassword(encoder.encode(signupEmpRequest.getPassword()));
        empleado.setNombre(signupEmpRequest.getNombre());
        empleado.setApellido(signupEmpRequest.getApellido());
        empleado.setIsEmpleado(true);
        empleado.setIsAdmin(false);
        Integer supervisorId = signupEmpRequest.getSupervisorId();
        if (supervisorId != null) {
            Optional<Empleado> supervisor = empleadoRepository.findById(supervisorId);
            if (supervisor.isPresent()) {
                empleado.setSupervisor(supervisor.get());
            }
        }
        empleadoRepository.save(empleado);

        return ResponseEntity.ok(new MessageResponse("Empleado registered successfully!"));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
