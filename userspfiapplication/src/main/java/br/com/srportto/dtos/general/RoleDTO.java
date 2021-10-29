package br.com.srportto.dtos.general;

import br.com.srportto.models.entities.Role;
import br.com.srportto.models.enums.NivelPermissaoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String authority;

	//? construtor diferenciado
	public RoleDTO(Role role) {
		super();
		id = role.getId();
		authority = role.getAuthority();
	}

	public RoleDTO(NivelPermissaoEnum permissaoEnum) {
		id = permissaoEnum.getNivelPermissao();
		authority = permissaoEnum.name();
	}

	public boolean isValid(){
		var nivelPermissao =	NivelPermissaoEnum.obterNomePermissao(id);

		if(!nivelPermissao.name().equalsIgnoreCase(authority)){
			return false;
		} else {
			return true;
		}
	}
}
