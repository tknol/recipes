package tnk.recipes.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tnk.recipes.domain.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
