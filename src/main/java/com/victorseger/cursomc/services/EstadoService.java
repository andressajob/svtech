package com.victorseger.cursomc.services;

import com.victorseger.cursomc.domain.Estado;
import com.victorseger.cursomc.domain.Estado;
import com.victorseger.cursomc.repositories.EstadoRepository;
import com.victorseger.cursomc.services.exceptions.DataIntegrityException;
import com.victorseger.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public Estado find(Integer id) {
        Optional<Estado> obj = estadoRepository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + ", Tipo: " + Estado.class.getName()));
    }

    public List<Estado> findAll() {
        return estadoRepository.findAllByOrderByNome();
    }

    public Estado getOne (Integer id) {return estadoRepository.getOne(id);}

    public Estado insert(Estado estado) {
        estado.setId(null);
        return estadoRepository.save(estado);
    }

    public Estado update(Estado estado) {
        Estado newEstado = find(estado.getId());
        //chama o método auxiliar para apenas atualizar os campos desejados do estado e não remover nenhum valor de outro campo
        updateData(newEstado,estado);
        return estadoRepository.save(newEstado);
    }

    public void delete(Integer id) {
        find(id);
        try {
            estadoRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir estado que possui produtos");
        }

    }

    private void updateData(Estado newEstado, Estado estado) {
        newEstado.setNome(estado.getNome());
    }

}
