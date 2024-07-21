package JTechLabs.Lab5.HostService.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class HostGetMessage {
    public Integer requestID;
    public String hostName;
}
