package br.com.srportto.dtos.request;

import br.com.srportto.dtos.general.UserDTO;
import br.com.srportto.services.validation.criacao.UserInsertValid;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Data
@UserInsertValid
public class UserInsertRequestDTO extends UserDTO {

    @NotBlank(message = "Campo password é obrigatório")
    private String password;
}