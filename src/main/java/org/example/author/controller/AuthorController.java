package org.example.author.controller;

import lombok.AllArgsConstructor;
import org.example.author.model.entities.AuthorEntities;
import org.example.author.model.request.AuthorRequest;
import org.example.author.model.response.AuthorResponse;
import org.example.author.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@RestController
public class AuthorController {
    private AuthorService authorService;
    @GetMapping("api/author")
    public ResponseEntity<AuthorResponse> getAllAuthors(){
        List<AuthorEntities> authors = authorService.getAllAuthor();
        AuthorResponse authorResponse = AuthorResponse.builder()
                .message("Get All Books Successfully")
                .data(authors)
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(authorResponse,HttpStatus.valueOf(200));
    }
    @PostMapping("add/author")
    public ResponseEntity<AuthorResponse> addAuthor(@RequestBody AuthorRequest authorRequest){
        System.out.println("ID : " + authorRequest.getId());
        System.out.println("Name : " + authorRequest.getUsername());
        System.out.println("Gender : " + authorRequest.getGender());
        authorService.addAuthor(authorRequest);
        return null;
    }
    @DeleteMapping("/api/delete/{id}")
    public ResponseEntity<AuthorResponse> deleteBook(@PathVariable int id){
        authorService.deleteAuthor(id);
        AuthorResponse authorResponse =AuthorResponse.builder()
                .message("Delete Book Successfully")
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(authorResponse,HttpStatus.valueOf(200));
    }
    @PutMapping("/api/update/{id}")
    public ResponseEntity<AuthorResponse<AuthorEntities>> updateAuthor(@PathVariable int id, @RequestBody AuthorRequest authorRequest){
        AuthorEntities checkAuthor = authorService.getAuthorById(id);
        if(checkAuthor == null){
            AuthorResponse authorResponse = AuthorResponse.builder()
                    .message("NO Data Found")
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .status(HttpStatus.NOT_FOUND)
                    .build();
            return new ResponseEntity<>(authorResponse,HttpStatus.valueOf(404));
        }
        if(
                authorRequest.getUsername() ==null || authorRequest.getUsername() ==null
        ){
            AuthorResponse authorResponse = AuthorResponse.builder()
                    .message("Add Author not successfully")
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
            return new ResponseEntity<>(authorResponse,HttpStatus.BAD_REQUEST);
        }
        AuthorEntities authorEntities = authorService.updateAuthor(authorRequest,id);
        AuthorResponse authorResponse = AuthorResponse.builder()
                .message("Update Author Successfully")
                .data(authorEntities)
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .status(HttpStatus.valueOf(200))
                .build();
        return new ResponseEntity<>(authorResponse,HttpStatus.OK);
    }
    @GetMapping("/author/name")
    public ResponseEntity<AuthorResponse<List<AuthorEntities>>> getAuthorByName(@RequestParam String username){
        List<AuthorEntities> authors = authorService.getAuthorByName(username);
        if(authors.isEmpty()){
            AuthorResponse authorResponse = AuthorResponse.builder()
                    .message("No data found")
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .status(HttpStatus.NOT_FOUND)
                    .build();
            return new ResponseEntity<>(authorResponse,HttpStatus.valueOf(404));
        }
        AuthorResponse authorResponse = AuthorResponse.builder()
                .message("Get All author Successfully")
                .data(authors)
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(authorResponse,HttpStatus.valueOf(200));
    }
    @GetMapping("/author/{id}")
        public ResponseEntity<AuthorResponse<AuthorEntities>> getAuthorById(@PathVariable int id){
            AuthorEntities authorEntities = authorService.getAuthorById(id);
            if(authorEntities == null){
                AuthorResponse authorResponse = AuthorResponse.builder()
                        .message("No data found")
                        .timestamp(new Timestamp(System.currentTimeMillis()))
                        .status(HttpStatus.NOT_FOUND)
                        .build();
                return new ResponseEntity<>(authorResponse,HttpStatus.valueOf(404));
            }
            AuthorResponse authorResponse = AuthorResponse.builder()
                    .data(authorEntities)
                    .message("Get Author Successfully")
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .status(HttpStatus.OK)

                    .build();
            return new ResponseEntity<>(authorResponse,HttpStatus.valueOf(200));
        }


}
