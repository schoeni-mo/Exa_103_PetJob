package at.kaindorf.exa_103_petjob.pojos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Chip {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String chipId;
    @NonNull
    private String type;

    @JsonBackReference
    @OneToOne(mappedBy = "chip")
    private Pet pet;
}
