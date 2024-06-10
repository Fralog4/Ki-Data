package com.app.Ki_Data.repository;
import com.app.Ki_Data.dto.CharacterDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.app.Ki_Data.model.Character;

@Repository
public interface CharacterRepository extends JpaRepository<Character,Integer> {
}
