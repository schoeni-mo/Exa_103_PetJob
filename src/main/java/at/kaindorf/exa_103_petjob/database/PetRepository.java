package at.kaindorf.exa_103_petjob.database;

import at.kaindorf.exa_103_petjob.pojos.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PetRepository  extends JpaRepository<Pet, Integer> {

    @Query("SELECT pet FROM Pet pet WHERE pet.type=:input")
    Page<Pet> getPetsByType(String input, Pageable pageable);


    @Query("SELECT pet.type FROM Pet pet")
    Set<String> getTypes();


}
