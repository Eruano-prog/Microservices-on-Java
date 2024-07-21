package JTechLabs.Lab5.CatService.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CatGetRequest {
    @Id
    @GeneratedValue()
    public Integer id;
    public Integer requestID;
    public String hostName;
    public String result;
}
