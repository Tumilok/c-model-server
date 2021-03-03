package pl.edu.agh.racing.cmodel.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.racing.cmodel.dto.SimulationDto;
import pl.edu.agh.racing.cmodel.service.SimulationService;

@RestController
@RequestMapping("/api/simulations")
@AllArgsConstructor
public class SimulationController {

    private final SimulationService simulationService;

    @PostMapping
    public ResponseEntity<Void> createSimulation(@RequestBody SimulationDto simulationDto){
        simulationService.save(simulationDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/by-id/{simulationId}")
    public ResponseEntity<SimulationDto> getSimulationById(@PathVariable Long simulationId){
        return ResponseEntity.status(HttpStatus.OK).body(simulationService.getSimulationById(simulationId));
    }


}
