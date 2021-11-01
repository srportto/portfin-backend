package br.com.srportto.services.validation.atualizacao;

import br.com.srportto.dtos.request.UserUpdateRequestDTO;
import br.com.srportto.exceptions.FieldMessage;
import br.com.srportto.repositories.UserRepository;
import lombok.AllArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class UserUpdateValidator implements ConstraintValidator<UserUpdateValid, UserUpdateRequestDTO> {
	private UserRepository repository;
	
	@Override
	public void initialize(UserUpdateValid ann) {
	}

	@Override
	public boolean isValid(UserUpdateRequestDTO dto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		var emailUser = dto.getEmail();
		var user = repository.findByEmail(emailUser);

		if (user != null) {
			list.add(new FieldMessage("email", "Nao é possível atualizar para este email, está em uso por outro usuário"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}

		//Retorno booleano (true/false) de acordo com a resposta da pergunta se a lista esta preenchida ou nao
		return list.isEmpty();
	}
}
