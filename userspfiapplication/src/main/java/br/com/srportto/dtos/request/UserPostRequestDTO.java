package br.com.srportto.dtos.request;

import br.com.srportto.dtos.general.UserDTO;
import br.com.srportto.services.validation.criacao.UserInsertValid;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@UserInsertValid
public class UserPostRequestDTO extends UserDTO {
    @NotBlank(message = "Senha precisa ser informada")
    private String password;
}