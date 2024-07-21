package JTechLabs.Lab5.HostService.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
public class CatDTO {
    public Integer id;
    public String name;
    public Date birthDate;
    public String type;
    public catColor color;
    @JsonIgnore
    public HostDTO host;
    @JsonIgnore
    public List<Cat> friends;

    public Cat toCat(){
        return new Cat(id, name, birthDate, type, color, host.toHost(), friends);
    }
}
