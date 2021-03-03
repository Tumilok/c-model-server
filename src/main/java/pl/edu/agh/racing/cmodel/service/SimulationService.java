package pl.edu.agh.racing.cmodel.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.agh.racing.cmodel.dto.SimulationDto;
import pl.edu.agh.racing.cmodel.mapper.SimulationMapper;
import pl.edu.agh.racing.cmodel.model.Simulation;
import pl.edu.agh.racing.cmodel.repository.SimulationRepository;
import pl.edu.agh.racing.cmodel.repository.StatusRepository;

import java.util.HashSet;

@Service
@AllArgsConstructor
public class SimulationService {

    SimulationRepository simulationRepository;
    StatusRepository statusRepository;
    SimulationMapper simulationMapper;

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
}
