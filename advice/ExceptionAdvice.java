/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tz.go.tcra.lims.advice;

import com.fasterxml.jackson.core.JsonParseException;
import io.jsonwebtoken.MalformedJwtException;
import javax.security.auth.message.AuthException;
import javax.servlet.ServletException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tz.go.tcra.licensing.dto.ErrorResponse;
import tz.go.tcra.licensing.exception.BadRequestException;
import tz.go.tcra.licensing.exception.DataNotFoundException;
import tz.go.tcra.licensing.exception.DuplicateException;
import tz.go.tcra.licensing.exception.ForbiddenException;
import tz.go.tcra.licensing.exception.GeneralException;
import tz.go.tcra.licensing.exception.OperationNotAllowedException;

/**
 *
 * @author emmanuel.mfikwa
 */
@ControllerAdvice
public class ExceptionAdvice {
    
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> generalExceptionResponse(GeneralException exc){

        ErrorResponse err=new ErrorResponse();
        err.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        err.setMessage(exc.getMessage());
        err.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(err,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> dataNotFoundExceptionResponse(DataNotFoundException exc){

        ErrorResponse err=new ErrorResponse();
        err.setStatus(HttpStatus.FAILED_DEPENDENCY.value());
        err.setMessage(exc.getMessage());
        err.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(err,HttpStatus.FAILED_DEPENDENCY);
    }
    
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> badRequestExceptionResponse(BadRequestException exc){

        ErrorResponse err=new ErrorResponse();
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setMessage(exc.getMessage());
        err.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> badRequestExceptionResponse(MalformedJwtException exc){

        ErrorResponse err=new ErrorResponse();
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setMessage(exc.getMessage());
        err.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> badRequestExceptionResponse(JsonParseException exc){

        ErrorResponse err=new ErrorResponse();
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setMessage(exc.getMessage());
        err.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> operationNotAllowedExceptionResponse(OperationNotAllowedException exc){

        ErrorResponse err=new ErrorResponse();
        err.setStatus(HttpStatus.PRECONDITION_FAILED.value());
        err.setMessage(exc.getMessage());
        err.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(err,HttpStatus.PRECONDITION_FAILED);
    }
    
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> badRequestExceptionResponse(NullPointerException exc){

        ErrorResponse err=new ErrorResponse();
        err.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        err.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.name());
        err.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(err,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> authenticationExceptionResponse(AuthException exc){

        ErrorResponse err=new ErrorResponse();
        err.setStatus(HttpStatus.UNAUTHORIZED.value());
        err.setMessage(exc.getMessage());
        err.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(err,HttpStatus.UNAUTHORIZED);
    }
    
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> servletExceptionResponse(ServletException exc){

        ErrorResponse err=new ErrorResponse();
        err.setStatus(HttpStatus.UNAUTHORIZED.value());
        err.setMessage(exc.getMessage());
        err.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(err,HttpStatus.UNAUTHORIZED);
    }
    
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> forbiddenExceptionResponse(ForbiddenException exc){

        ErrorResponse err=new ErrorResponse();
        err.setStatus(HttpStatus.FORBIDDEN.value());
        err.setMessage(exc.getMessage());
        err.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(err,HttpStatus.FORBIDDEN);
    }
    
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> forbiddenExceptionResponse(AccessDeniedException exc){

        ErrorResponse err=new ErrorResponse();
        err.setStatus(HttpStatus.FORBIDDEN.value());
        err.setMessage(exc.getMessage());
        err.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(err,HttpStatus.FORBIDDEN);
    }
    
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> duplicateExceptionResponse(DuplicateException exc){

        ErrorResponse err=new ErrorResponse();
        err.setStatus(HttpStatus.ALREADY_REPORTED.value());
        err.setMessage(exc.getMessage());
        err.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(err,HttpStatus.ALREADY_REPORTED);
    }
    
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> pageNotFoundExceptionResponse(HttpRequestMethodNotSupportedException  exc){

        ErrorResponse err=new ErrorResponse();
        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setMessage(exc.getMessage());
        err.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> generalExceptionResponse(Exception  exc){

        ErrorResponse err=new ErrorResponse();
        err.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        err.setMessage(exc.getMessage());
        err.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(err,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> authenticationExceptionResponse(AuthenticationException  exc){

        ErrorResponse err=new ErrorResponse();
        err.setStatus(HttpStatus.UNAUTHORIZED.value());
        err.setMessage(exc.getMessage());
        err.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(err,HttpStatus.UNAUTHORIZED);
    }
}
