package JTechLabs.Lab5.APIService.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HostGetMessage {
    public Integer requestID;
    public String hostName;
}
