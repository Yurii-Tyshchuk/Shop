package ru.skillsad.sad.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ExceptionAdviceController {

    /**
     * Отвечает за обратку исключений при записи в бд
     *
     * @return Json с параметром message в котором будет краткое описание ошибки
     */
    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<ResponseTemp> DBException(TransactionSystemException e) {
        StringBuilder message = new StringBuilder();
        if (e.getRootCause() instanceof ConstraintViolationException) {
            ConstraintViolationException exception = (ConstraintViolationException) e.getRootCause();

            for (ConstraintViolation<?> violation : exception.getConstraintViolations()) {
                message.append(violation.getMessage().concat(";"));
            }
        } else {
            message.append("Ошибка, проверяй логи");
        }

        return new ResponseEntity<>(new ResponseTemp(message.toString()), HttpStatus.BAD_REQUEST);
    }

    /**
     * @param e исключение связанное с ошибкой валидации, которое Spring сам запихнет =D
     * @return Json с параметром message в котором будет краткое описание ошибки
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseTemp> validException(MethodArgumentNotValidException e) {
        StringBuilder stringBuilder = new StringBuilder();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            stringBuilder.append(error.getDefaultMessage());
            stringBuilder.append("\n");
        }
        return new ResponseEntity<>(new ResponseTemp(stringBuilder.toString()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ResponseTemp> handleMaxSizeException(MaxUploadSizeExceededException exc) {
        return new ResponseEntity<>(new ResponseTemp("File too large!"), HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ResponseTemp> requestParamValidException(MissingServletRequestParameterException ex) {
        return new ResponseEntity<>(new ResponseTemp(ex.getParameterName() + " <= Пустой или что нибудь еще"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ResponseTemp> userNullException(NoSuchElementException ex) {
        return new ResponseEntity<>(new ResponseTemp(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
