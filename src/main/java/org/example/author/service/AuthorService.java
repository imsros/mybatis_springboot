package org.example.author.service;

import lombok.AllArgsConstructor;
import org.example.author.model.entities.AuthorEntities;
import org.example.author.model.request.AuthorRequest;
import org.example.author.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;
    public List<AuthorEntities> getAllAuthor(){
        return authorRepository.getAuthor();
    }
    public void addAuthor(AuthorRequest authorRequest){
        authorRepository.insertAuthor(authorRequest);
    }
    public void deleteAuthor(int id){
        authorRepository.deleteAuthor(id);
    }
    public  AuthorEntities getAuthorById(int id){
        return authorRepository.getAuthorById(id);
    }
    public List<AuthorEntities> getAuthorByName(String username){
        return authorRepository.getAuthorByName("%"+username+"%");
    }
    public AuthorEntities updateAuthor(AuthorRequest authorRequest,int id){
        authorRepository.updateAuthor(authorRequest,id);
        return authorRepository.getAuthorById(id);
    }
}
