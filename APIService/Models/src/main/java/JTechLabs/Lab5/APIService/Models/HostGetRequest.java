package JTechLabs.Lab5.APIService.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HostGetRequest {
    @Id
    @GeneratedValue()
    public Integer id;
    public Integer requestID;
    public String result;
}
