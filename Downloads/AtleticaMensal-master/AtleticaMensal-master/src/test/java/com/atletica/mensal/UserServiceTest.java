package com.atletica.mensal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.atletica.mensal.Entities.AtleticaEntity;
import com.atletica.mensal.Entities.RankingEntity;
import com.atletica.mensal.Entities.UserEntity;
import com.atletica.mensal.Repository.UserRepository;
import com.atletica.mensal.Service.UserService;

@SpringBootTest
public class UserServiceTest {

	@Mock
	UserRepository userRepository;

	@InjectMocks
	UserService userService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this); // Inicializa os mocks
	}


	   @Test
	    @DisplayName("Teste findById com sucesso")
	    void testeFindById() {
	        UserEntity usuario = new UserEntity();
	        usuario.setId(4L);
	        usuario.setEmail("testezin@gamil");

	        // Mockando o comportamento do repositório
	        when(userRepository.findById(4L)).thenReturn(Optional.of(usuario));

	        // Chamando o método de serviço
	        Optional<UserEntity> usuarioEncontrado = userService.buscarUserPorId(4L);

	        // Verificar se o usuário foi encontrado corretamente
	        assert(usuarioEncontrado.isPresent());
	        assert(usuarioEncontrado.get().getEmail().equals("testezin@gamil"));
	    }

	    @Test
	    @DisplayName("Teste findByEmail com email vazio")
	    void testeFindByEmailEmailVazio() {
	        // Testar se a exceção é lançada ao passar um email vazio
	        assertThrows(IllegalArgumentException.class, () -> {
	            userService.findByEmail("");
	        });
	    }

	    @Test
	    @DisplayName("Teste findByEmail com email nulo")
	    void testeFindByEmailEmailNulo() {
	        // Testar se a exceção é lançada ao passar um email nulo
	        assertThrows(IllegalArgumentException.class, () -> {
	            userService.findByEmail(null);
	        });
	    }


	    @Test
	    @DisplayName("Teste findByEmail - usuário não encontrado")
	    void testeFindByEmailUsuarioNaoEncontrado() {
	        // Mockando a ausência de um usuário
	        when(userRepository.findByEmail("inexistente@gmail.com")).thenReturn(null);

	        // Verificar se a exceção correta é lançada quando o usuário não é encontrado
	        assertThrows(RuntimeException.class, () -> {
	            userService.findByEmail("inexistente@gmail.com");
	        });
	    }
	    
	    @Test
	    public void testDeletarUser() {
	        // Criar um mock para UserEntity
	        UserEntity user = new UserEntity();
	        user.setId(1L);

	        // Simular o comportamento de findById no UserRepository
	        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

	        userService.deletarUser(1L);

	        verify(userRepository, times(1)).delete(user);
	    }

	    @Test
	    public void testDeletarUserUsuarioNaoEncontrado() {
	        // Simular o comportamento de findById no UserRepository, retornando Optional vazio
	        when(userRepository.findById(1L)).thenReturn(Optional.empty());

	        // Verificar se a exceção RuntimeException é lançada
	        RuntimeException thrown = assertThrows(
	            RuntimeException.class,
	            () -> userService.deletarUser(1L),
	            "Usuário não encontrado"
	        );

	        // Verificar a mensagem da exceção
	        assertEquals("Usuário não encontrado", thrown.getMessage());
	    }
	}
	