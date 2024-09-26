package com.atletica.mensal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.atletica.mensal.Controller.PostagemController;
import com.atletica.mensal.Entities.PostagemEntity;
import com.atletica.mensal.Entities.UserEntity;
import com.atletica.mensal.Service.PostagemService;
import com.atletica.mensal.Service.UserService;


@AutoConfigureMockMvc
public class PostagemControllerTest {

    @Mock
    private PostagemService postagemService;

    @InjectMocks
    private PostagemController postagemController;

    @Mock
    private UserService userService; 
    
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // Inicializa mocks
    }

    @Test
    void testCriarPostagem() throws Exception {
        UserEntity mockUser = new UserEntity();
        mockUser.setEmail("Atletica Ursao");
        
        // Simular o retorno do método userService.findByEmail com o email correto
        when(userService.findByEmail("Atletica Ursao")).thenReturn(mockUser);
        
        // Criar um mock de postagem
        PostagemEntity post = new PostagemEntity();
        post.setImagem("Nova Festa");

        when(postagemService.criarPostagem(anyLong(), any(PostagemEntity.class), anyLong())).thenReturn(post);
        
        when(postagemService.salvarPostagem(any(PostagemEntity.class), any(UserEntity.class))).thenReturn(post);
        
        // Nova postagem a ser criada
        PostagemEntity novaPostagem = new PostagemEntity();
        novaPostagem.setImagem("Nova Festa");

        // Chamar o método do controller
        PostagemEntity resultado = postagemController.criarPostagem(novaPostagem, "Atletica Ursao");

        // Verificar se o resultado não é nulo
        assertNotNull(resultado, "O resultado não deveria ser nulo");

        // Verificar se a imagem da postagem retornada é a esperada
        assertEquals("Nova Festa", resultado.getImagem());
    }
    @Test
    public void testListarPostagensPorAtletica() throws Exception {
        // Criação das entidades de postagens com imagem
        PostagemEntity postagem1 = new PostagemEntity();
        postagem1.setImagem("Festa 1");

        PostagemEntity postagem2 = new PostagemEntity();
        postagem2.setImagem("Festa 2");

        when(postagemService.listarPostagensPorAtletica(1L)).thenReturn(List.of(postagem1, postagem2));

        List<PostagemEntity> resultado = postagemController.listarPostagensPorAtletica(1L);

        // Verifica o conteúdo da imagem nas postagens retornadas
        assertEquals(2, resultado.size());
        assertEquals("Festa 1", resultado.get(0).getImagem());
        assertEquals("Festa 2", resultado.get(1).getImagem());
    }
}
