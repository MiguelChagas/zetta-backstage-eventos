package br.ufla.zetta.backstage_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "eventos")
@Data
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome do evento é obrigatório")
    private String nome;

    @NotNull @FutureOrPresent
    @Column(name = "data_evento")
    private LocalDate dataEvento;

    private String localizacao;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario organizador;

    @Column(name = "criado_em")
    private LocalDateTime criadoEm = LocalDateTime.now();
}