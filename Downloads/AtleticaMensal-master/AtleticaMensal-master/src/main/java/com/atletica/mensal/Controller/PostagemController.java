package com.atletica.mensal.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atletica.mensal.Entities.AtleticaEntity;
import com.atletica.mensal.Entities.PostagemEntity;
import com.atletica.mensal.Entities.UserEntity;
import com.atletica.mensal.Repository.AtleticaRepository;
import com.atletica.mensal.Repository.PostagemRepository;
import com.atletica.mensal.Service.PostagemService;
import com.atletica.mensal.Service.UserService;

@RestController
@RequestMapping("/postagens")
public class PostagemController {

	@Autowired
	private PostagemService postagemService;
	
	@Autowired
	private AtleticaRepository atleticaRepository;
	
	@Autowired
	private PostagemRepository postagemRepository;

	@Autowired
	private UserService userService;

	@PostMapping("/criar")
	public PostagemEntity criarPostagem(@RequestBody PostagemEntity postagem, @RequestParam String email) {
		UserEntity user = userService.findByEmail(email);
		return postagemService.salvarPostagem(postagem, user);
	}
	
/*	public PostagemEntity criarPostagem(long atletica_id, PostagemEntity postagem, long user_id)   {
        AtleticaEntity atletica = atleticaRepository.findById(atletica_id)
                .orElseThrow(() -> new RuntimeException("Atlética não encontrada"));

        // Verificar se o usuário tem permissão para postar 
        if (!atletica.getDonoId().equals(user_id)) {
            throw new RuntimeException("Usuário não autorizado a postar");
        }

        // Associar a postagem à atlética
 
		postagem.setAtletica(atletica);
		return postagemRepository.save(postagem);
           }
*/
	@GetMapping(value = "/listarPostagemPorAtleticas")
	public List<PostagemEntity> listarPostagensPorAtletica(@PathVariable long atletica_id) {
		return postagemService.listarPostagensPorAtletica(atletica_id);
	}
}
