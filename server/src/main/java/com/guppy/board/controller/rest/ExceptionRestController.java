package com.guppy.board.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by guppy.kang on 2018. 3. 28.
 * email : guppy.kang@kakaocorp.com
 */

@RestController
@CrossOrigin(value = "*", origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
@RequestMapping(value = "/api/exception")
public class ExceptionRestController {

    @GetMapping(value="/unauthorized", produces = "application/javascript")
    public ResponseEntity<String> exception401 () {
        return new ResponseEntity<>("{\"message\": \"Full authentication is required to access this resource\"}", HttpStatus.UNAUTHORIZED);
    }
}
