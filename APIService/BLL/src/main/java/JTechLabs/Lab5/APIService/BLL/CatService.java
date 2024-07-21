package JTechLabs.Lab5.APIService.BLL;

import JTechLabs.Lab5.APIService.DLL.CatProducer;
import JTechLabs.Lab5.APIService.DLL.ICatRequestRepository;
import JTechLabs.Lab5.APIService.Models.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;


@Service
public class CatService{
    private final CatProducer catProducer;
    private final ICatRequestRepository catRequestRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public CatService(CatProducer catProducer, ICatRequestRepository requestRepository, ObjectMapper objectMapper) {
        this.catProducer = catProducer;
        this.catRequestRepository = requestRepository;
        this.objectMapper = objectMapper;
    }

    public void addCat(String hostname, CatDTO cat) throws JsonProcessingException {

        catProducer.putCat(hostname, cat);
    }

    public void deleteCat(String hostname, String name) throws JsonProcessingException {

        catProducer.deleteCat(hostname, name);
    }

    public void getCat(String hostname, String name, Integer requestID) throws JsonProcessingException {
        catProducer.getCat(hostname, name, requestID);
    }

    public void modifyCat(String hostname, CatDTO cat) throws JsonProcessingException {

        catProducer.saveCat(hostname, cat);
    }

    public void getCatsByColor(String hostname, catColor color, Integer requestID) throws JsonProcessingException {

        catProducer.getCatByColor(hostname, color, requestID);
    }

    public List<CatDTO> checkRequest(Integer requestId) throws JsonProcessingException {
        CatGetRequest request = catRequestRepository.findByRequestID(requestId);

        return objectMapper.readValue(request.getResult(), new TypeReference<List<CatDTO>>(){});
    }
}
