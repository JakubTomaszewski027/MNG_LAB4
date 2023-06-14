package edu.wat.tim.lab1.repository;

import edu.wat.tim.lab1.model.PozycjaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface PozycjaEntityRepository extends JpaRepository<PozycjaEntity, Long> {
    public PozycjaEntity findByProduktEntityIdAndKoszykEntityId(Long produktId, Long koszykId);

    public PozycjaEntity findByKoszykEntityIdAndId(Long koszykId, Long pozycjaId);

    @Transactional
    public void deleteByProduktEntityId(Long produktId);

    @Transactional
    public void deleteByKoszykEntityIdAndId(Long koszykId, Long pozycjaId);

    @Transactional
    public void deleteAllByKoszykEntityIdAndProduktEntityId(Long basketId, Long productId);
}