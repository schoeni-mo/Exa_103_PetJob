package at.kaindorf.exa_103_petjob.pojos;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Owner {
    @NonNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long ownerId;

    @NonNull
    private String firstname;
    @NonNull
    private String lastname;

    @JsonManagedReference
    @OneToMany(mappedBy = "owner", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Pet> petList = new ArrayList<>();
}
