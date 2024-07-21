package JTechLabs.Lab5.CatService.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
        Host HostToSet = null;
        if (host != null){
            HostToSet = host.toHost();
        }
        return new Cat(id, name, birthDate, type, color, HostToSet, friends);
    }
}
