//package ua.ithillel.evo.questengine.exception.handler;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//import ua.ithillel.evo.questengine.data.entity.Hint;
//import ua.ithillel.evo.questengine.data.entity.User;
//import ua.ithillel.evo.questengine.exception.ApplicationGlobalException;
//import ua.ithillel.evo.questengine.exception.model.ErrorMessage;
//
//import javax.servlet.http.HttpServletRequest;
//
//@ControllerAdvice
//@Slf4j
//public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
//
//    @ExceptionHandler({User.class, Hint.class})
//    public ResponseEntity<ErrorMessage> handleException(ApplicationGlobalException exception, HttpServletRequest request) {
//        var httpStatus = exception.getHttpStatus();
//
//        log.error(String.format("Exception received, path: '%s'", request.getRequestURI()), exception);
//
//        ErrorMessage errorMessage = ErrorMessage.builder()
//                .message(exception.getMessage())
//                .statusCode(httpStatus.value())
//                .timestamp(System.currentTimeMillis())
//                .error(exception.getClass().getName())
//                .build();
//
//        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
//    }
//}
