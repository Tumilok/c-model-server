package pl.edu.agh.racing.cmodel.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pl.edu.agh.racing.cmodel.dto.SimulationDto;
import pl.edu.agh.racing.cmodel.model.Log;
import pl.edu.agh.racing.cmodel.model.Simulation;
import pl.edu.agh.racing.cmodel.model.Status;

import java.util.Optional;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface SimulationMapper {
    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "name", source = "simulationDto.name"),
        @Mapping(target = "fw", source = "simulationDto.fw"),
        @Mapping(target = "rw", source = "simulationDto.rw"),
        @Mapping(target = "s", source = "simulationDto.s"),
        @Mapping(target = "diff", source = "simulationDto.diff"),
        @Mapping(target = "u", source = "simulationDto.u"),
        @Mapping(target = "n", source = "simulationDto.n"),
        @Mapping(target = "df", source = "simulationDto.df"),
        @Mapping(target = "d", source = "simulationDto.d"),
        @Mapping(target = "balance", source = "simulationDto.balance"),
        @Mapping(target = "mrfChlodnica", source = "simulationDto.mrfChlodnica"),
        @Mapping(target = "status", source = "status"),
        @Mapping(target = "logs", source = "logs"),
        @Mapping(target = "createDateTime", expression = "java(java.time.Instant.now())"),
        @Mapping(target = "updateDateTime", expression = "java(null)")
    })
    Simulation map(SimulationDto simulationDto, Status status, Set<Log> logs);

    @Mappings({
        @Mapping(target = "statusId", expression = "java(simulation.getStatus().getId())"),
        @Mapping(target = "updateDateTime", expression = "java(simulation.getUpdateDateTime())")
    })
    SimulationDto mapToDto(Simulation simulation);
}
