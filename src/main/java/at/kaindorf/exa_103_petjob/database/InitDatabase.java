package at.kaindorf.exa_103_petjob.database;

import at.kaindorf.exa_103_petjob.pojos.Owner;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import org.springframework.data.domain.Pageable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import at.kaindorf.exa_103_petjob.pojos.Pet;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;

@Component
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/petjob")
@CrossOrigin("*")
@RestController
public class InitDatabase {

    private final PetRepository petRepo;
    private final OwnerRepository ownerRepo;
    private final ChipRepository chipRepo;
    List<Owner> owners;

    @PostConstruct
    public void intiDB(){
        InputStream is = InitDatabase.class.getResourceAsStream("/Petstore.json");

        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

        try {
            owners = mapper.readerForListOf(Owner.class).readValue(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ownerRepo.saveAll(owners);
    }

    @GetMapping("/types")
    public Set<String> getTypes() {

       return petRepo.getTypes();

    }

    @PatchMapping("/pet/{id}")
    public void patchPet(@PathVariable Integer id, @RequestBody Pet pet) {
        Optional<Pet> existing = petRepo.findById(id);

        if ( existing.isPresent() ) {
            Pet oldpet = existing.get();

            oldpet.setName(pet.getName());
            oldpet.setType(pet.getType());


            petRepo.save(oldpet);
        }
    }


    @GetMapping("/pets")
    public Page<Pet> getPetsByType(@RequestParam(required = false, defaultValue = "Cat") String type, @RequestParam(required = false, defaultValue = "0") Integer pageNo, @RequestParam(required = false, defaultValue = "6") Integer pageSize) {
        Page<Pet> pets;

        Pageable pageable = PageRequest.of(pageNo, pageSize);
        pets = petRepo.getPetsByType(type, pageable);


        return pets;
    }
}
