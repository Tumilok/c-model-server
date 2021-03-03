package pl.edu.agh.racing.cmodel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimulationDto {
    private Long id;
    private String name;
    private String fw;
    private String rw;
    private String s;
    private String diff;
    private String u;
    private String n;
    private Double df;
    private Double d;
    private Double balance;
    private Double mrfChlodnica;
    private Long statusId;
    private Instant createDateTime;
    private Instant updateDateTime;
}
