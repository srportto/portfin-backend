package br.com.srportto.dtos.request;

import br.com.srportto.dtos.general.UserDTO;
import br.com.srportto.services.validation.UserInsertValid;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@UserInsertValid
public class UserPostRequest  extends UserDTO {
    private static final long serialVersionUID = 1L;

    private String password;
}