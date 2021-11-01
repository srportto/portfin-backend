package br.com.srportto.dtos.request;

import br.com.srportto.dtos.general.RoleDTO;
import br.com.srportto.dtos.general.UserDTO;
import br.com.srportto.services.validation.atualizacao.UserUpdateValid;
import br.com.srportto.services.validation.criacao.UserInsertValid;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@AllArgsConstructor
@UserUpdateValid
public class UserUpdateRequestDTO extends UserDTO {
    private String password;

    @NotNull(message = "Campo permissões é obrigatório")
    private Set<RoleDTO> roles;
}