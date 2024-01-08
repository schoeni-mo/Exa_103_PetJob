package at.kaindorf.exa_103_petjob.pojos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Data
//@NamedQueries({
//        @NamedQuery(name = "Song.findAll", query = "SELECT p FROM Pet p WHERE p.name LIKE :name")
//})
public class Pet {
    @NonNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long petId;

    @NonNull
    private String picture;
    @NonNull
    private String type;
    @NonNull
    private String name;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;
    @NonNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @JsonManagedReference
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JoinColumn(name = "chip_id")
    private Chip chip;
}
