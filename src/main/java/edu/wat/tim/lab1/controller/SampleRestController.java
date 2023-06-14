package edu.wat.tim.lab1.controller;

import edu.wat.tim.lab1.model.KoszykEntity;
import edu.wat.tim.lab1.model.KlientEntity;
import edu.wat.tim.lab1.model.ProduktEntity;
import edu.wat.tim.lab1.model.PozycjaEntity;
import edu.wat.tim.lab1.model.KomentarzEntity;
import edu.wat.tim.lab1.service.SampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SampleRestController {

    private final SampleService service;

    @GetMapping("/echo")
    public String echo(String value) {
        return value;
    }

    @GetMapping("/echo/{value}")
    public String echoPath(@PathVariable String value) {
        return value;
    }

    @PostMapping("/klient")
    public ResponseEntity<KlientEntity> createKlientEntity(@RequestBody KlientEntity entity) {
        KlientEntity savedEntity = service.createKlientEntity(entity);
        return new ResponseEntity<>(savedEntity, HttpStatus.CREATED);
    }

    @PostMapping("/produkt")
    public ResponseEntity<ProduktEntity> addProdukt(@RequestBody ProduktEntity produktEntity) {
        ProduktEntity savedEntity = service.addProdukt(produktEntity);
        return new ResponseEntity<>(savedEntity, HttpStatus.CREATED);
    }

    @PostMapping("/produkt/{id}/komentarz")
    public ResponseEntity<KomentarzEntity> addKomentarz(@RequestBody KomentarzEntity komentarzEntity, @PathVariable(value = "id") Long id) {
        KomentarzEntity savedEntity = service.addKomentarz(komentarzEntity, id);
        return new ResponseEntity<>(savedEntity, HttpStatus.OK);
    }


   // @GetMapping("/entity")
    //public ResponseEntity<List<KlientEntity>> getAllEntities() {
     //   List<KlientEntity> entities = service.getAllEntities();
       // return new ResponseEntity<>(entities, HttpStatus.OK);
    //}

    @PutMapping("/koszyk/{koszykid}/produkt/{produktid}")
    public ResponseEntity<PozycjaEntity> changeAmountOfProduct(@PathVariable(value = "koszykid") Long koszykId,
                                                            @PathVariable(value = "produktid") Long produktId,
                                                            @RequestParam(value = "iloscProdukt") Integer iloscProdukt)
    {
        return new ResponseEntity<>(service.changeAmountOfProduct(koszykId, produktId, iloscProdukt), HttpStatus.OK);
    }

    @PostMapping("/klient/{id}/koszyk")
    public ResponseEntity<KoszykEntity> addKoszykEntity(@RequestBody KoszykEntity entity, @PathVariable(value = "id") Long id) {
        KoszykEntity savedEntity = service.addKoszykEntity(entity, id);
        return new ResponseEntity<>(savedEntity, HttpStatus.OK);
    }

    @PostMapping("/koszyk/{koszykId}/produkt/{produktId}")
    public ResponseEntity<PozycjaEntity> addProduktToKoszyk(
            @PathVariable ("koszykId") Long koszykId,
            @PathVariable ("produktId") Long produktId,
            @RequestParam("iloscProdukt") Integer iloscProdukt) {
        PozycjaEntity pozycjaEntity = service.addPozycjaKoszyk(koszykId, produktId, iloscProdukt);
        return new ResponseEntity<>(pozycjaEntity, HttpStatus.OK);
    }

    @GetMapping("/produkt")
    public ResponseEntity<List<ProduktEntity>> findProduktByName(@RequestParam("name") String name) {
        List<ProduktEntity> produkty = service.searchProduktByName(name);
        return new ResponseEntity<>(produkty, HttpStatus.OK);
    }

    @DeleteMapping("/produkt/{produktid}")
    public ResponseEntity<?> deletePozycja(@PathVariable(value = "produktid") Long produktId)
    {
        service.deleteByProductEntityId(produktId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/koszyk/{koszykid}/produkt/{produktid}")
    public ResponseEntity<?> deleteProduktByKoszykAndProduktId(@PathVariable(value = "koszykid") Long koszykId,
                                                               @PathVariable(value = "produktid") Long produktId) {
        service.deleteAllByKoszykEntityIdAndProduktEntityId(koszykId, produktId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/koszyk/{koszykid}/pozycja/{pozycjaid}")
    public ResponseEntity<?> deleteByKoszykEntityIdAndPozycjaEntityId(@PathVariable(value = "koszykid") Long koszykId,
                                                                   @PathVariable(value = "pozycjaid") Long pozycjaId) {
        service.deleteByKoszykEntityIdAndId(koszykId, pozycjaId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    //@PutMapping("/entity/{id}")
    //public ResponseEntity<KlientEntity> updateEntity(@RequestBody KlientEntity entity, @PathVariable(value = "id") Long id) {
    //    return new ResponseEntity<>(service.updateEntity(entity, id), HttpStatus.OK);
    //}
}
