package br.com.srportto.services;

import br.com.srportto.dtos.general.RoleDTO;
import br.com.srportto.dtos.general.UserDTO;
import br.com.srportto.dtos.request.UserInsertRequestDTO;
import br.com.srportto.dtos.request.UserUpdateRequestDTO;
import br.com.srportto.dtos.responses.UserDefaultResponseDTO;
import br.com.srportto.dtos.responses.UserExtendsResponseDTO;
import br.com.srportto.exceptions.DatabaseException;
import br.com.srportto.exceptions.ResourceNotFoundException;
import br.com.srportto.models.entities.Role;
import br.com.srportto.models.entities.User;
import br.com.srportto.models.enums.NivelPermissaoEnum;
import br.com.srportto.repositories.RoleRepository;
import br.com.srportto.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
	private static Logger logger = LoggerFactory.getLogger(UserService.class);
	
	private BCryptPasswordEncoder passwordEncoder;
	private UserRepository repository;
	private RoleRepository roleRepository;
	
	@Transactional(readOnly = true)
	public Page<UserDefaultResponseDTO> findAllPaged(Pageable pageable) {
		Page<User> list = repository.findAll(pageable);
		return list.map(x -> new UserDefaultResponseDTO(x));
	}

	@Transactional(readOnly = true)
	public UserExtendsResponseDTO findById(Long id) {
		Optional<User> obj = repository.findById(id);
		User entity = obj.orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrado"));
		return new UserExtendsResponseDTO(entity);
	}

	@Transactional
	public UserDefaultResponseDTO insert(UserInsertRequestDTO dto) {
		User entity = new User();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new UserDefaultResponseDTO(entity);
	}

	@Transactional
	public UserExtendsResponseDTO update(Long id, UserUpdateRequestDTO dto) {
		try {
			User entity = repository.getOne(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new UserExtendsResponseDTO(entity);
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}		
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}
	
	private void copyDtoToEntity(UserDTO dto, User entity) {
		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
		entity.setEmail(dto.getEmail());

		entity.getRoles().clear();

		if(dto instanceof UserInsertRequestDTO){
			var userPostRequest = (UserInsertRequestDTO) dto;
			Role role = roleRepository.getOne(NivelPermissaoEnum.ROLE_CLIENTE.getNivelPermissao());
			entity.getRoles().add(role);
			entity.setPassword(passwordEncoder.encode(userPostRequest.getPassword()));
		}

		if(dto instanceof UserUpdateRequestDTO){
			var userUpdateRequest = (UserUpdateRequestDTO) dto;
			var rolesUsers = userUpdateRequest.getRoles();

			for(RoleDTO roleUser :rolesUsers){
				var nivelPermissao = NivelPermissaoEnum.obterNomePermissao(roleUser.getId());
				var roleEntity = roleRepository.getOne(nivelPermissao.getNivelPermissao());
				entity.getRoles().add(roleEntity);
			}

			//encode/criptografia de senha do usuario
			var passwordCripto = passwordEncoder.encode(userUpdateRequest.getPassword());
			entity.setPassword(passwordCripto);
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByEmail(username);
		if (user == null) {
			logger.error("User not found: " + username);
			throw new UsernameNotFoundException("Email not found");
		}
		logger.info("User found: " + username);
		return user;
	}
}
