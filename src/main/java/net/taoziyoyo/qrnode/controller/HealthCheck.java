package net.taoziyoyo.qrnode.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * health checker
 */
@RestController
@RequestMapping("v1")
public class HealthCheck {

    @RequestMapping(value = "/health", method = {RequestMethod.GET})
    public ResponseEntity<String> healthCheck() {
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<String>("healthcheck is OK", status);
    }
}
