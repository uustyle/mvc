package sample.customer.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
@ControllerAdvice
public class ExceptionHandler {

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
//    @org.springframework.web.bind.annotation.ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
//    @org.springframework.web.bind.annotation.ExceptionHandler({ RuntimeException.class })
    @org.springframework.web.bind.annotation.ExceptionHandler({ Exception.class })
    @ResponseBody
    public Map<String, Object> handleError() {
        Map<String, Object> errorMap = new HashMap<String, Object>();
        errorMap.put("message", "許可されていないメソッド");
        errorMap.put("status", HttpStatus.METHOD_NOT_ALLOWED);
        return errorMap;
    }


////@ResponseStatus(HttpStatus.OK)
//@org.springframework.web.bind.annotation.ExceptionHandler({ RuntimeException.class })
//public ResponseEntity<ErrorResponse> exceptionHandler() {
//	ErrorResponse error = new ErrorResponse();
//	error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
//	error.setMessage("Please contact your administrator");
//	return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
//}

}
