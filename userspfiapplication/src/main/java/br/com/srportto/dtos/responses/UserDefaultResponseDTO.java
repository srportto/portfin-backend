package br.com.srportto.dtos.responses;

import br.com.srportto.dtos.general.UserDTO;
import br.com.srportto.models.entities.User;
import br.com.srportto.services.validation.criacao.UserInsertValid;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@UserInsertValid
public class UserDefaultResponseDTO extends UserDTO {

    //Construtor diferenciado
    public UserDefaultResponseDTO(User entity) {
        super(entity);
    }
}
