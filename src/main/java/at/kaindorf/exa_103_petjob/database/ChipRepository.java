package at.kaindorf.exa_103_petjob.database;

import at.kaindorf.exa_103_petjob.pojos.Chip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChipRepository extends JpaRepository<Chip, String> {
}
