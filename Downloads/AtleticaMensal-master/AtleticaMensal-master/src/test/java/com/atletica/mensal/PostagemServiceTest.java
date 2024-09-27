package com.atletica.mensal;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

import com.atletica.mensal.Entities.AtleticaEntity;
import com.atletica.mensal.Entities.PostagemEntity;
import com.atletica.mensal.Entities.UserEntity;
import com.atletica.mensal.Repository.AtleticaRepository;
import com.atletica.mensal.Repository.PostagemRepository;
import com.atletica.mensal.Service.PostagemService;

public class PostagemServiceTest {
    
    @Mock
    private PostagemRepository postagemRepository;
    
    @Mock
    private AtleticaRepository atleticaRepository;
    
    @InjectMocks
    private PostagemService postagemService;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  
    }
    
    @Test
    @DisplayName("Teste criarPostagem - Usuário autorizado")
    void testeCriarPostagemUsuarioAutorizado() {
        // Configurando mocks
        AtleticaEntity atletica = new AtleticaEntity();
        atletica.setDonoId(1L);  
        
        PostagemEntity postagem = new PostagemEntity();
        
        // Simulando que a atlética foi encontrada
        when(atleticaRepository.findById(1L)).thenReturn(Optional.of(atletica));
        when(postagemRepository.save(postagem)).thenReturn(postagem);
        
        PostagemEntity postagemCriada = postagemService.criarPostagem(1L, postagem,1L);
        
        // Verifica se a postagem foi associada corretamente e salva
        assertEquals(atletica, postagemCriada.getAtletica());
    }
    
    @Test
    @DisplayName("Teste criarPostagem - Usuário não autorizado")
    void testeCriarPostagemUsuarioNaoAutorizado() {
       
        AtleticaEntity atletica = new AtleticaEntity();
        atletica.setDonoId(2L); 
        
        PostagemEntity postagem = new PostagemEntity();
        
        // Simulando que a atlética foi encontrada
        when(atleticaRepository.findById(1L)).thenReturn(Optional.of(atletica));
        
        
        assertThrows(RuntimeException.class, () -> {
            postagemService.criarPostagem(1L, postagem, 1L);  
        });
    }
    @Test
    @DisplayName("Teste salvar postagem")
    void testSalvarPostagem() {
        // Criar um usuário e uma postagem para o teste
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setEmail("user@test.com");

        PostagemEntity postagem = new PostagemEntity();
        postagem.setId(1L);
        postagem.setTexto(" postagem de teste");

        // Definir comportamento do repositório para simular o salvamento da postagem
        when(postagemRepository.save(postagem)).thenReturn(postagem);

        // Chamar o método salvarPostagem
        PostagemEntity resultado = postagemService.salvarPostagem(postagem, user);

        // Verificar se o método save do repositório foi chamado corretamente
        verify(postagemRepository, times(1)).save(postagem);

        // Verificar se o usuário foi associado corretamente à postagem
        assertEquals(user, postagem.getUser());

        // Verificar se o resultado retornado é a postagem salva
        assertNotNull(resultado);
        assertEquals(postagem.getId(), resultado.getId());
    }
}

