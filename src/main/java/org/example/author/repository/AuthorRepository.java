package org.example.author.repository;

import org.apache.ibatis.annotations.*;
import org.example.author.model.entities.AuthorEntities;
import org.example.author.model.request.AuthorRequest;
import org.example.author.model.response.AuthorResponse;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AuthorRepository {
    @Select("SELECT * FROM tb_author")
    public List<AuthorEntities> getAuthor();
    @Insert("INSERT INTO tb_author(id,username,gender)"+
    "VALUES (#{author.id},#{author.username},#{author.gender})")
    public void insertAuthor(@Param("author")AuthorRequest authorRequest);
    @Delete("DELETE FROM tb_author WHERE id = #{id}")
    public void deleteAuthor(@Param("id")int id);
    @Select("SELECT * FROM tb_author WHERE id = #{id}")
    public AuthorEntities getAuthorById(int id);
    @Select("SELECT * FROM tb_author WHERE username LIKE #{authorname}")
    public List<AuthorEntities> getAuthorByName(@Param("authorname") String username);
    @Update("UPDATE tb_author SET id = #{author.id}, username = #{author.username}, gender = #{author.gender} WHERE id = #{authorID}")
    public void updateAuthor(@Param("author") AuthorRequest authorRequest,@Param("authorID")int id);
}
