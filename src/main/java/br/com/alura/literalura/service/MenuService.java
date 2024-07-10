package br.com.alura.literalura.service;

import br.com.alura.literalura.service.GutendexService;
import br.com.alura.literalura.entity.Author;
import br.com.alura.literalura.entity.Book;
import br.com.alura.literalura.repository.AuthorRepository;
import br.com.alura.literalura.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class MenuService implements CommandLineRunner {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    private final GutendexService gutendexClient = new GutendexService();
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void run(String... args) throws Exception {
        boolean exit = false;

        while (!exit) {
            printMenu();
            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (option) {
                case 1:
                    searchBookByTitle();
                    break;
                case 2:
                    listRegisteredBooks();
                    break;
                case 3:
                    listAuthors();
                    break;
                case 4:
                    listAuthorsByYear();
                    break;
                case 5:
                    listBooksByLanguage();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }

    private void printMenu() {
        System.out.println("\n--- Menu LiterAlura ---");
        System.out.println("1. Buscar livro pelo título");
        System.out.println("2. Listar livros registrados");
        System.out.println("3. Listar nossos autores");
        System.out.println("4. Listar autores em determinado ano");
        System.out.println("5. Listar livros em determinado idioma");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private void searchBookByTitle() {
        System.out.print("Digite o título do livro: ");
        String title = scanner.nextLine();
        Optional<Book> bookOpt = gutendexClient.searchBookByTitle(title);
        if (bookOpt.isPresent()) {
            bookRepository.save(bookOpt.get());
            System.out.println("Livro salvo com sucesso!");
        } else {
            System.out.println("Livro não encontrado.");
        }
    }

    private void listRegisteredBooks() {
        List<Book> books = bookRepository.findAll();
        if (books.isEmpty()) {
            System.out.println("Nenhum livro registrado.");
        } else {
            books.forEach(book -> System.out.println(book.getTitle() + " - " + book.getAuthor()));
        }
    }

    private void listAuthors() {
        List<Author> authors = authorRepository.findAll();
        if (authors.isEmpty()) {
            System.out.println("Nenhum autor registrado.");
        } else {
            authors.forEach(author -> System.out.println(author.getName() + " - " + author.getBirthYear() + " - " + author.getDeathYear()));
        }
    }

    private void listAuthorsByYear() {
        System.out.print("Digite o ano: ");
        int year = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        List<Author> authors = authorRepository.findAll();
        authors.stream()
                .filter(author -> author.getBirthYear() <= year && author.getDeathYear() >= year)
                .forEach(author -> System.out.println(author.getName()));
    }

    private void listBooksByLanguage() {
        System.out.print("Digite o idioma (es, en, fr, pt): ");
        String language = scanner.nextLine();
        List<Book> books = bookRepository.findAll();
        books.stream()
                .filter(book -> book.getLanguage().equalsIgnoreCase(language))
                .forEach(book -> System.out.println(book.getTitle() + " - " + book.getAuthor()));
    }
}