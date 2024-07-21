package JTechLabs.Lab5.CatService.BLL;


import JTechLabs.Lab5.CatService.DLL.ICatRepository;
import JTechLabs.Lab5.CatService.DLL.IHostRepository;
import JTechLabs.Lab5.CatService.DLL.IRequestRepository;
import JTechLabs.Lab5.CatService.Models.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CatService {
    private final ICatRepository catRepository;
    private final IHostRepository hostRepository;
    private final IRequestRepository requestRepository;
    private final ObjectMapper mapper;

    @Autowired
    public CatService(ICatRepository catRepository, IHostRepository hostRepository, IRequestRepository requestRepository, ObjectMapper mapper) {
        this.catRepository = catRepository;
        this.hostRepository = hostRepository;
        this.requestRepository = requestRepository;
        this.mapper = mapper;
    }

    public CatDTO addCat(String hostname, CatDTO cat) {
        Host host = null;
        try {
            host = hostRepository.findByName(hostname)
                    .orElseThrow(() -> new EntityNotFoundException("Host " + hostname + " does not exist"));
        }
        catch (EntityNotFoundException e) {
            System.out.println("Host " + hostname + " does not exist");
            return null;
        }
        Cat catToAdd = cat.toCat();
        catToAdd.setHost(host);

        Cat addedCat = catRepository.save(catToAdd);

        return addedCat.toDTO();
    }

    public void deleteCat(String hostname, String name){
        Cat cat = catRepository.findByHost_NameAndNameIgnoreCase(hostname, name)
                .orElseThrow(() -> new EntityNotFoundException("Cat with name " + name + " not found"));

        catRepository.delete(cat);
    }

    @Transactional
    public CatDTO getCat(String hostname, String name, Integer requestID){
        Cat cat = catRepository.findByHost_NameAndNameIgnoreCase(hostname, name)
                .orElseThrow(() -> new EntityNotFoundException("Cat with name " + name + " not found"));

        ArrayList<CatDTO> catDTOs = new ArrayList<>();
        catDTOs.add(cat.toDTO());

        try {
            String result = mapper.writeValueAsString(catDTOs);
            CatGetRequest request = new CatGetRequest(null, requestID, hostname, result);
            requestRepository.save(request);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        return cat.toDTO();
    }

    @Transactional
    public CatDTO modifyCat(String hostname, CatDTO cat){
        Host host = hostRepository.findByName(hostname)
                .orElseThrow(() -> new EntityNotFoundException("Host " + hostname + " does not exist"));

        Cat catToModify = catRepository.findByHost_NameAndNameIgnoreCase(hostname, cat.getName())
                .orElseThrow(() -> new EntityNotFoundException("Cat with name " + cat.getName() + " not found"));

        catToModify.setFromDTO(cat);
        catToModify.setHost(host);

        Cat savedCat = catRepository.save(catToModify);

        return savedCat.toDTO();
    }

    public void addFriend(String receiver, CatDTO catFriend){

        Cat catToAdd = catFriend.toCat();

        Cat cat = catRepository.findByNameIgnoreCase(receiver)
                .orElseThrow(() -> new EntityNotFoundException("Cat with name " + catToAdd.name + " not found"));

        cat.friends.add(catToAdd);

        catRepository.save(cat);
    }

    @Transactional
    public List<CatDTO> getCatsByColor(String hostname, catColor color, Integer requestID){

        List<CatDTO> cats = catRepository.findByHost_NameAndColor(hostname, color)
                .stream()
                .map(Cat::toDTO)
                .collect(Collectors.toList());

        try {
            String result = mapper.writeValueAsString(cats);
            CatGetRequest request = new CatGetRequest(null, requestID, hostname, result);
            requestRepository.save(request);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }

        return cats;
    }
}
