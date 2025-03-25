package br.com.fiap.checkpoint1.controller;

import br.com.fiap.checkpoint1.model.Pedido;
import br.com.fiap.checkpoint1.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public List<Pedido> getAll() {
        return pedidoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getById(@PathVariable Long id) {
        return pedidoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Pedido create(@Valid @RequestBody Pedido pedido) {
        return pedidoService.save(pedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> update(@PathVariable Long id, @Valid @RequestBody Pedido pedidoAtualizado) {
        Optional<Pedido> existentPedido = pedidoService.findById(id);

        if (existentPedido.isPresent()) {
            Pedido pedido = existentPedido.get();
            pedido.setClienteNome(pedidoAtualizado.getClienteNome());
            pedido.setValorTotal(pedidoAtualizado.getValorTotal());

            Pedido savedPedido = pedidoService.save(pedido);
            return ResponseEntity.ok(savedPedido);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (pedidoService.findById(id).isPresent()) {
            pedidoService.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}