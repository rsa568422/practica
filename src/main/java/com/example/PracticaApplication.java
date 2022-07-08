package com.example;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.domains.contracts.repositories.ActorRepository;
import com.example.domains.contracts.repositories.CategoryRepository;
import com.example.domains.contracts.repositories.FilmRepository;
import com.example.domains.contracts.repositories.LanguageRepository;
import com.example.domains.entities.dtos.ActorDTO;
import com.example.domains.entities.dtos.CategoryDTO;
import com.example.domains.entities.dtos.FilmDTO;
import com.example.domains.entities.dtos.LanguageDTO;

import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication
public class PracticaApplication implements CommandLineRunner {
	
	@Autowired
	private ActorRepository actorRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private FilmRepository filmRepository;
	
	@Autowired
	private LanguageRepository languageRepository;

	public static void main(String[] args) {
		SpringApplication.run(PracticaApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		System.out.println("Aplicación arrancada");
		//consultas();
	}
	
	@Transactional
	private void consultas() {
		var actores = actorRepository.findAll();
		System.out.println(actores.size());
		
		var actor = actorRepository.findById(1);
		System.out.println(actor.isPresent() ? ActorDTO.from(actor.get()) : "vacío");
		
		var peliculas = filmRepository.findAll();
		System.out.println(peliculas.size());
		
		var pelicula = filmRepository.findById(1);
		System.out.println(pelicula.isPresent() ? FilmDTO.from(pelicula.get()) : "vacío");
		
		var categorias = categoryRepository.findAll();
		System.out.println(categorias.size());
		
		var categoria = categoryRepository.findById(1);
		System.out.println(categoria.isPresent() ? CategoryDTO.from(categoria.get()) : "vacío");
		
		var idiomas = languageRepository.findAll();
		System.out.println(idiomas.size());
		
		var idioma = languageRepository.findById(1);
		System.out.println(idioma.isPresent() ? LanguageDTO.from(idioma.get()) : "vacío");
	}

}
