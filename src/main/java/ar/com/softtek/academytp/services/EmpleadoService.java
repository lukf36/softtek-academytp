package ar.com.softtek.academytp.services;

import ar.com.softtek.academytp.models.Empleado;
import ar.com.softtek.academytp.repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {
    @Autowired
    private EmpleadoRepository empleadoRepository;

    public List<Empleado> getAllEmpleados(){
        return (List<Empleado>) this.empleadoRepository.findAll();
    }

    public Optional<Empleado> getEmpleadoById(Integer id){
        return this.empleadoRepository.findById(id);
    }

    public Empleado saveEmpleado(Empleado empleado){
        return this.empleadoRepository.save(empleado);
    }

    public boolean deleteEmpleado(Integer id){
        try {
            this.empleadoRepository.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
