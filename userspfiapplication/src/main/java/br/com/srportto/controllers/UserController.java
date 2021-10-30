package br.com.srportto.controllers;

import br.com.srportto.dtos.general.UserDTO;
import br.com.srportto.dtos.request.UserPostRequestDTO;
import br.com.srportto.dtos.request.UserUpdateRequestDTO;
import br.com.srportto.dtos.responses.UserDefaultResponseDTO;
import br.com.srportto.dtos.responses.UserExtendsResponseDTO;
import br.com.srportto.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/users")
@AllArgsConstructor
public class UserController {
    private UserService service;

    @GetMapping
    public ResponseEntity<Page<UserDefaultResponseDTO>> findAll(Pageable pageable) {
        Page<UserDefaultResponseDTO> list = service.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserExtendsResponseDTO> findById(@PathVariable Long id) {
        UserExtendsResponseDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<UserDefaultResponseDTO> insert(@RequestBody @Valid UserPostRequestDTO dto) {
        var newDto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newDto.getId()).toUri();
        return ResponseEntity.created(uri).body(newDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody @Valid UserUpdateRequestDTO dto) {
        UserDTO newDto = service.update(id, dto);
        return ResponseEntity.ok().body(newDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
