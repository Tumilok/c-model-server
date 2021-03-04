package pl.edu.agh.racing.cmodel.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.edu.agh.racing.cmodel.dto.SimulationDto;
import pl.edu.agh.racing.cmodel.mapper.SimulationMapper;
import pl.edu.agh.racing.cmodel.model.Simulation;
import pl.edu.agh.racing.cmodel.repository.SimulationRepository;
import pl.edu.agh.racing.cmodel.repository.StatusRepository;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SimulationService {

    private final SimulationRepository simulationRepository;
    private final StatusRepository statusRepository;
    private final SimulationMapper simulationMapper;

    private final int simulationsPerPage = 20;

    public void save(SimulationDto simulationDto){
        Simulation simulation = simulationMapper.map(simulationDto, statusRepository.getOne(1L), new HashSet<>());
        simulationRepository.save(simulation);
    }

    public SimulationDto getSimulationById(Long id){
        if(simulationRepository.findById(id).isEmpty()){
            return null;
        }
        return simulationMapper.mapToDto(simulationRepository.findById(id).get());
    }

    public List<SimulationDto> getAllSimulations() {
        return simulationRepository.findAll().stream()
                .map(simulationMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public List<SimulationDto> getAllSimulationWithPagination(int pageNumber) {
        return simulationRepository.findAll(PageRequest.of(pageNumber - 1, simulationsPerPage)).stream()
                .map(simulationMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
