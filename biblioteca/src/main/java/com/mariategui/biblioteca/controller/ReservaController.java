package com.mariategui.biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mariategui.biblioteca.entity.Reserva;
import com.mariategui.biblioteca.service.ReservaService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reserva")

public class ReservaController {
    @Autowired
    private ReservaService reservaService;

    @GetMapping()
    public ResponseEntity<List<Reserva>> list() {
        return ResponseEntity.ok().body(reservaService.listar());
    }

    @PostMapping()
    public ResponseEntity<Reserva> save(@RequestBody Reserva reserva) {
        return ResponseEntity.ok(reservaService.guardar(reserva));
    }

    @PutMapping()
    public ResponseEntity<Reserva> update(@RequestBody Reserva reserva) {
        return ResponseEntity.ok(reservaService.actualizar(reserva));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> listById(@PathVariable(required = true) Integer id) {
        return ResponseEntity.ok().body(reservaService.listarPorId(id).get());
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable(required = true) Integer id) {
        reservaService.eliminarPorId(id);
        return "Eliminacion Correcta";
    }
}
