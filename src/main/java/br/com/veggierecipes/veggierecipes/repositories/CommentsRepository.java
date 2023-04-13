package br.com.veggierecipes.veggierecipes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.veggierecipes.veggierecipes.models.Comments;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {

}
