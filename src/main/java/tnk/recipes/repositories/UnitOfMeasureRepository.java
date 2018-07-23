package tnk.recipes.repositories;

import org.springframework.data.repository.CrudRepository;
import tnk.recipes.domain.UnitOfMeasure;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {
    Optional<UnitOfMeasure> findByDescription(String description);
}
