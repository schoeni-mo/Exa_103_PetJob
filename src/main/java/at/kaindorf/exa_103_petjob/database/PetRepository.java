package at.kaindorf.exa_103_petjob.database;

import at.kaindorf.exa_103_petjob.pojos.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PetRepository  extends JpaRepository<Pet, Integer> {

    @Query("SELECT pet FROM Pet pet WHERE pet.type = :type")
    Pet getPetByType(String type);
}
