package JTechLabs.Lab5.APIService.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "host")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Host {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;
    public String name;
    public Date birthDate;
    @OneToMany
    public List<Cat> cats;

    public String password;
    public String roles;

    public HostDTO toDTO(){
        return new HostDTO(id, name, birthDate, cats, password, roles);
    }
}
