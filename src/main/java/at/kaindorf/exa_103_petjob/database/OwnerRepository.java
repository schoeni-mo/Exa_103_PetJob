package at.kaindorf.exa_103_petjob.database;

import at.kaindorf.exa_103_petjob.pojos.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface OwnerRepository extends JpaRepository<Owner, Long> {

}
