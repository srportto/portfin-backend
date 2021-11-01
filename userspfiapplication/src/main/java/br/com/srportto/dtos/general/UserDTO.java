package br.com.srportto.dtos.general;

import br.com.srportto.models.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotBlank(message = "Campo de primeiro nome é obrigatório")
	private String firstName;

	@NotBlank(message = "Campo de ultimo nome é obrigatório")
	private String lastName;

	@NotBlank(message = "Campo email é obrigatório")
	@Email(message = "Por favor, informe um email válido")
	private String email;

	//Construtor diferenciado
	public UserDTO(User entity) {
		id = entity.getId();
		firstName = entity.getFirstName();
		lastName = entity.getLastName();
		email = entity.getEmail();
	}
}
