package br.com.srportto.dtos.responses;

import br.com.srportto.dtos.general.RoleDTO;
import br.com.srportto.dtos.general.UserDTO;
import br.com.srportto.models.entities.User;
import br.com.srportto.services.validation.UserInsertValid;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@UserInsertValid
public class UserExtendsResponse extends UserDTO {

    Set<RoleDTO> roles = new HashSet<>();

    //Construtor diferenciado
    public UserExtendsResponse(User entity) {
        super(entity);
        entity.getRoles().forEach(role -> this.roles.add(new RoleDTO(role)));
    }
}