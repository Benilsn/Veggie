package br.com.veggierecipes.veggierecipes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.veggierecipes.veggierecipes.models.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

}
