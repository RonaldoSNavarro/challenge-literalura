package br.com.alura.literalura.repository;

import br.com.alura.literalura.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}