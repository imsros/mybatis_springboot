package org.example.author.model.response;

import ch.qos.logback.core.joran.action.TimestampAction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AuthorResponse <T>{
    private String message;
    private T data;
    private Timestamp timestamp;
    private HttpStatus status;
}
