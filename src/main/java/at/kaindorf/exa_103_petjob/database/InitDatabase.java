package at.kaindorf.exa_103_petjob.database;

import at.kaindorf.exa_103_petjob.pojos.Owner;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

import at.kaindorf.exa_103_petjob.pojos.Pet;

@Component
@Slf4j
@RequiredArgsConstructor
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


    public Optional<List<Pet>> getAllPetsByType(String type){

        List<Pet> allPets = petRepo.findAll();

        List<Pet> petsByType = allPets.stream().filter(p -> p.getType().equals(type)).toList();

        if (!petsByType.isEmpty()){
            return Optional.of(petsByType);
        }else return Optional.empty();
    }
}
