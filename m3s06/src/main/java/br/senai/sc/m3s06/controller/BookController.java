package br.senai.sc.m3s06.controller;

import br.senai.sc.m3s06.exceptions.BookNotFoundException;
import br.senai.sc.m3s06.exceptions.UserNotFoundException;
import br.senai.sc.m3s06.model.dto.BookAvgRatingDTO;
import br.senai.sc.m3s06.model.dto.BookDTO;
import br.senai.sc.m3s06.model.dto.BookRatingCountDTO;
import br.senai.sc.m3s06.model.dto.RatingDTO;
import br.senai.sc.m3s06.model.dto.operations.create.CreateBookDTO;
import br.senai.sc.m3s06.model.dto.operations.create.CreateRatingDTO;
import br.senai.sc.m3s06.service.BookService;
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

    @GetMapping("/{guid}")
    public ResponseEntity<BookRatingCountDTO> findBookByGuid(@PathVariable String guid) throws BookNotFoundException {
        BookRatingCountDTO bookRatingCount = bookService.findBookById(guid);
        return ResponseEntity.ok(bookRatingCount);
    }

    @PostMapping("/{guid}/assessment")
    public ResponseEntity<RatingDTO> createRating(@PathVariable("guid") String guid,
                                                  @Valid @RequestBody CreateRatingDTO rating,
                                                  @AuthenticationPrincipal UserDetails userInSession) throws BookNotFoundException {
        RatingDTO response = this.bookService.createRating(guid, rating, userInSession);
        return ResponseEntity.ok(response);
    }
}
