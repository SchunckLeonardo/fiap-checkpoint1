package br.com.fiap.checkpoint1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.LocalDate;

@Entity
@Data
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Nome do cliente é obrigatório")
    private String clienteNome;

    private LocalDate dataPedido = LocalDate.now();

    @PositiveOrZero(message = "Valor total não pode ser negativo")
    private double valorTotal;
}
