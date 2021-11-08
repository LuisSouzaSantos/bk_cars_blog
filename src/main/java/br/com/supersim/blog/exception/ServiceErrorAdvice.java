package br.com.supersim.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ServiceErrorAdvice {
	
	@ExceptionHandler
	public ResponseEntity<String> handleException(Exception e){
		return error(HttpStatus.INTERNAL_SERVER_ERROR, e);
	}
	
	private ResponseEntity<String> error(HttpStatus status, Exception e) {
        return ResponseEntity.status(status).body(e.getMessage());
    }

}
