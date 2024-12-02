package ssf.day18.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ssf.day18.models.Carpark;
import ssf.day18.services.CarparkService;

@RestController
@RequestMapping
public class CarparkRestController {
    @Autowired
    CarparkService cpSvc;

    @GetMapping(path="/api/carparks", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Carpark>> getCarparks() {
        return ResponseEntity.ok().body(cpSvc.getCarparks());
    }
}
