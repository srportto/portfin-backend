package br.com.srportto.dtos.responses;

import br.com.srportto.dtos.general.UserDTO;
import br.com.srportto.models.entities.User;
import br.com.srportto.services.validation.UserInsertValid;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@UserInsertValid
public class UserDefaultResponse  extends UserDTO {

    //Construtor diferenciado
    public UserDefaultResponse(User entity) {
        super(entity);
    }
}
