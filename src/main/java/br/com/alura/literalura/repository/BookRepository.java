package br.com.alura.literalura.repository;

import br.com.alura.literalura.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}