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

import at.kaindorf.exa_103_petjob.pojos.Pet;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/pets")
    public List<Pet> getPetsByType(@RequestParam(required = false) String type, @RequestParam(required = false) Integer pageNo, @RequestParam(required = false) Integer pageSize) {
        List<Pet> pets = new ArrayList<>();
        if (pageNo == null || pageNo == 0) {
            pageNo = 1;
        }
        if (pageSize == null || pageSize == 0) {
            pageSize = 10;
        }

        if ( type == null ) {
            pets = petRepo.findAll();
        } else {
            pets = petRepo.getPetsByType(type);
        }

        Pageable pageable = PageRequest.of(pageNo -1, pageSize);
        Page<Pet> petPage = new PageImpl<>(pets, pageable, pets.size());



        return petPage.getContent();
    }
}
