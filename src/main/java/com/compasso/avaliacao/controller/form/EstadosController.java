package com.compasso.avaliacao.controller.form;

import com.compasso.avaliacao.controller.dto.EstadoDto;
import com.compasso.avaliacao.modelo.Estado;
import com.compasso.avaliacao.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/estados")
public class EstadosController {

    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping
    public Page<EstadoDto> lista(@PageableDefault() Pageable paginacao){
        Page<Estado> estados = estadoRepository.findAll(paginacao);
        return EstadoDto.converter(estados);
    }
    @GetMapping("/{id}")
    public ResponseEntity<EstadoDto> mostrar(@PathVariable Long id){
        Optional<Estado> estado = estadoRepository.findById(id);
        if(estado.isPresent()){
            return ResponseEntity.ok(new EstadoDto(estado.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<EstadoDto> cadastrar(@RequestBody @Valid EstadoForm estadoForm, UriComponentsBuilder uriBuilder){
        Estado estado = estadoForm.converter();
        estadoRepository.save(estado);

        URI uri = uriBuilder.path("/estados/{id}").buildAndExpand(estado.getId()).toUri();
        return ResponseEntity.created(uri).body(new EstadoDto(estado));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<EstadoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizarEstadoForm form){
        Optional<Estado> optional = estadoRepository.findById(id);
        if(optional.isPresent()){
            Estado estado = form.atualizar(id, estadoRepository);
            return ResponseEntity.ok(new EstadoDto(estado));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id) {
        Optional<Estado> optional = estadoRepository.findById(id);
        if(optional.isPresent()){
            estadoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
