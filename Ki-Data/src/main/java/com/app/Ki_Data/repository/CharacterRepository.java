package com.app.Ki_Data.repository;
import com.app.Ki_Data.model.CharacterPg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<CharacterPg,Integer> {
}
