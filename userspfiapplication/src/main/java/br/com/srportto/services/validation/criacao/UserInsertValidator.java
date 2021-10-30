package br.com.srportto.services.validation.criacao;

import br.com.srportto.dtos.request.UserPostRequestDTO;
import br.com.srportto.exceptions.FieldMessage;
import br.com.srportto.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Log4j2
public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserPostRequestDTO> {

	private UserRepository repository;
	
	@Override
	public void initialize(UserInsertValid ann) {
	}

	@Override
	public boolean isValid(UserPostRequestDTO dto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		var user = repository.findByEmail(dto.getEmail());

		if (user != null) {
			log.info(String.format("Não é possivel cadastrar este email, está em uso por outro usuario: %s",user.getEmail()));
			list.add(new FieldMessage("email", "Não é possivel cadastrar este email, está em uso por outro usuario"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
