package com.esliceu.securityWeb.Controller;

import org.springframework.web.bind.annotation.ControllerAdvice;

//https://javadesde0.com/manejando-excepciones-con-la-anotacion-controlleradvice-concepto-de-circuit-breaker-cortocircuito/
@ControllerAdvice
public class ExceptionGlobalResponse {
    /*

    	Response result;

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Response> runtimeException(RuntimeException e) {
		result = new Response(Time.getTime(), "[Exception Response] - Exception: " + e.getMessage(), 500, "Error");
		return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Response> exception(Exception e) {
		result = new Response(Time.getTime(), "[Exception Response] - Exception: " + e.getMessage(), 500, "Error");
		return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
	}

     */
}
