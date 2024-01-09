package at.kaindorf.exa_103_petjob.database;

import at.kaindorf.exa_103_petjob.pojos.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository  extends JpaRepository<Pet, Integer> {

    @Query("SELECT pet FROM Pet pet WHERE pet.type=:input")
    List<Pet> getPetsByType(String input);


}
