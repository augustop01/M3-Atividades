package br.senai.sc.m3s04.controller;

import br.senai.sc.m3s04.exceptions.BookNotFoundException;
import br.senai.sc.m3s04.exceptions.UserNotFoundException;
import br.senai.sc.m3s04.model.dto.BookAvgRatingDTO;
import br.senai.sc.m3s04.model.dto.BookDTO;
import br.senai.sc.m3s04.model.dto.BookRatingCountDTO;
import br.senai.sc.m3s04.model.dto.operations.create.CreateBookDTO;
import br.senai.sc.m3s04.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDTO> create(@AuthenticationPrincipal UserDetails userInSession,
                                          @Valid @RequestBody CreateBookDTO createBookDTO,
                                          UriComponentsBuilder uriComponentsBuilder)
            throws UserNotFoundException {
        BookDTO response = this.bookService.create(createBookDTO, userInSession);
        return ResponseEntity.created(uriComponentsBuilder.path("/book/{id}").buildAndExpand(response.guid()).toUri()).body(response);
    }

    @GetMapping
    public ResponseEntity<List<BookAvgRatingDTO>> listAll(){
        List<BookAvgRatingDTO> response = this.bookService.listAllBooks();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookRatingCountDTO> findBookByGuid(@PathVariable String id) throws BookNotFoundException {
        BookRatingCountDTO bookRatingCount = bookService.findBookById(id);
        return ResponseEntity.ok(bookRatingCount);
    }
}
