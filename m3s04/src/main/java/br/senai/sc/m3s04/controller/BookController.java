package br.senai.sc.m3s04.controller;

import br.senai.sc.m3s04.exceptions.UserNotFoundException;
import br.senai.sc.m3s04.model.dto.BookDTO;
import br.senai.sc.m3s04.model.dto.operations.create.CreateBookDTO;
import br.senai.sc.m3s04.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

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
}
