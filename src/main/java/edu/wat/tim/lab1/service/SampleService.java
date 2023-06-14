package edu.wat.tim.lab1.service;

import edu.wat.tim.lab1.model.KoszykEntity;
import edu.wat.tim.lab1.model.KlientEntity;
import edu.wat.tim.lab1.model.ProduktEntity;
import edu.wat.tim.lab1.model.PozycjaEntity;
import edu.wat.tim.lab1.model.KomentarzEntity;
import edu.wat.tim.lab1.repository.KoszykEntityRepository;
import edu.wat.tim.lab1.repository.KlientEntityRepository;
import edu.wat.tim.lab1.repository.ProduktEntityRepository;
import edu.wat.tim.lab1.repository.PozycjaEntityRepository;
import edu.wat.tim.lab1.repository.KomentarzEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SampleService {

    private final KlientEntityRepository klientEntityRepository;
    private final KoszykEntityRepository koszykEntityRepository;

    private final ProduktEntityRepository produktEntityRepository;

    private final PozycjaEntityRepository pozycjaEntityRepository;

    private final KomentarzEntityRepository komentarzEntityRepository;

    public KlientEntity createKlientEntity(KlientEntity entity) {
        return klientEntityRepository.save(entity);
    }

    public List<KlientEntity> getAllEntities() {
        return klientEntityRepository.findAll();
    }
    public List<ProduktEntity> searchProduktByName(String name) {
        return produktEntityRepository.findByName(name);
    }

    public KoszykEntity addKoszykEntity(KoszykEntity koszykEntity, Long klientId) {
        KlientEntity klientEntity = klientEntityRepository.findById(klientId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono encji o id " + klientId));
        koszykEntity.setKlientEntity(klientEntity);
        return koszykEntityRepository.save(koszykEntity);
    }

    public KomentarzEntity addKomentarz(KomentarzEntity komentarzEntity, Long produktId) {
        ProduktEntity produktEntity = produktEntityRepository.findById(produktId)
                .orElseThrow(() -> new RuntimeException("Produkt o takim ID nie istnieje: " + produktId));

        komentarzEntity.setProduktEntity(produktEntity);
        return komentarzEntityRepository.save(komentarzEntity);
    }


    public ProduktEntity addProdukt(ProduktEntity produktEntity) {
        return produktEntityRepository.save(produktEntity);
    }

    public void deleteKoszykEntity(Long koszykId) {
        koszykEntityRepository.deleteById(koszykId);
    }

    public PozycjaEntity addPozycjaKoszyk(Long koszykId, Long produktId, Integer ilosc) {
        KoszykEntity koszykEntity = koszykEntityRepository.findById(koszykId)
                .orElseThrow(() -> new RuntimeException("Koszyk o takim ID nie istnieje: " + koszykId));

        ProduktEntity produktEntity = produktEntityRepository.findById(produktId)
                .orElseThrow(() -> new RuntimeException("Produkt o takim ID nie istnieje: " + produktId));

        PozycjaEntity pozycjaEntity = new PozycjaEntity();
        pozycjaEntity.setIloscProdukt(ilosc);
        pozycjaEntity.setKoszykEntity(koszykEntity);
        pozycjaEntity.setProduktEntity(produktEntity);

        koszykEntity.getPozycjaEntities().add(pozycjaEntity);
        pozycjaEntityRepository.save(pozycjaEntity);
        return pozycjaEntity;
    }

    public KlientEntity updateEntity(KlientEntity updatedEntity, Long id) {
        KlientEntity entity = klientEntityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono encji o id " + id));

        entity.setName(updatedEntity.getName());
        return klientEntityRepository.save(entity);
    }

    public PozycjaEntity changeAmountOfProduct(Long koszykId, Long produktId, Integer iloscProdukt) {
        ProduktEntity produkt = produktEntityRepository.findById(produktId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono koszyka o id " + produktId));
        KoszykEntity koszyk = koszykEntityRepository.findById(koszykId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono koszyka o id " + koszykId));
        PozycjaEntity pozycja = pozycjaEntityRepository.findByProduktEntityIdAndKoszykEntityId(produktId, koszykId);
        if(iloscProdukt < 1){
            throw new RuntimeException("Podano liczbe mniejsza niz 1");
        }
        pozycja.setIloscProdukt(iloscProdukt);
        return pozycjaEntityRepository.save(pozycja);
    }

    public void deleteByProductEntityId(Long produktId) {
        PozycjaEntity pozycja = pozycjaEntityRepository.findById(produktId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono koszyka o id " + produktId));
        ProduktEntity produkt = pozycja.getProduktEntity();
        pozycjaEntityRepository.delete(pozycja);
        produktEntityRepository.delete(produkt);
    }

    public void deleteAllByKoszykEntityIdAndProduktEntityId(Long koszykId, Long produktId) {
        KoszykEntity koszyk = koszykEntityRepository.findById(koszykId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono koszyka o id " + koszykId));
        ProduktEntity produkt = produktEntityRepository.findById(produktId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono produktu o id " + produktId));
        pozycjaEntityRepository.deleteAllByKoszykEntityIdAndProduktEntityId(koszykId, produktId);

    }

    public void deleteByKoszykEntityIdAndId(Long koszykId, Long pozycjaId) {
        KoszykEntity koszyk = koszykEntityRepository.findById(koszykId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono koszyka o id " + koszykId));
        PozycjaEntity pozycja = pozycjaEntityRepository.findById(pozycjaId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono przedmiotu o id " + pozycjaId));
        PozycjaEntity pozycjaToDelete = pozycjaEntityRepository.findByKoszykEntityIdAndId(koszykId, pozycjaId);
        pozycjaEntityRepository.delete(pozycjaToDelete);
    }
}
