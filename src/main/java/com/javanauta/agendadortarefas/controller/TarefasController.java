package com.javanauta.agendadortarefas.controller;

import com.javanauta.agendadortarefas.business.TarefasService;
import com.javanauta.agendadortarefas.business.dto.TarefasDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefasController {

    private final TarefasService tarefasService;

    @PostMapping
    public ResponseEntity<TarefasDTO> gravarTarefas(@RequestBody TarefasDTO dto,
                                                    @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(tarefasService.gravarTarefa(token, dto));


    }

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefasDTO>> buscarListaDeTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal
            ){
        return ResponseEntity.ok(tarefasService.buscaTarefasAgendadasPorPerioso(dataInicial, dataFinal));
    }

    @GetMapping
    public ResponseEntity<List<TarefasDTO>> buscarTarefasPorEmailPorEmail(@RequestHeader("Authorization") String token){
        List<TarefasDTO> tarefas = tarefasService.buscarTarefaPorEmail(token);
        return ResponseEntity.ok(tarefas);
    }

}
