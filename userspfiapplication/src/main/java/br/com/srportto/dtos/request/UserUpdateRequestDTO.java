package br.com.srportto.dtos.request;

import br.com.srportto.dtos.general.RoleDTO;
import br.com.srportto.dtos.general.UserDTO;
import br.com.srportto.services.validation.atualizacao.UserUpdateValid;
import br.com.srportto.services.validation.criacao.UserInsertValid;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
@UserUpdateValid
public class UserUpdateRequestDTO extends UserDTO {
    private String password;
    private Set<RoleDTO> roles;
}