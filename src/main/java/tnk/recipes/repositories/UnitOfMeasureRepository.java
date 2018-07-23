package tnk.recipes.repositories;

import org.springframework.data.repository.CrudRepository;
import tnk.recipes.domain.UnitOfMeasure;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {
}
