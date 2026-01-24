package br.ufla.zetta.backstage_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "itens_checklist")
@Data
public class ItemChecklist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    @Enumerated(EnumType.STRING)
    private StatusItem status = StatusItem.PENDENTE;

    @Enumerated(EnumType.STRING)
    private Prioridade prioridade = Prioridade.MEDIA;

    private String categoria;

    @Column(name = "data_limite")
    private LocalDateTime dataLimite;

    @ManyToOne
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento evento;

    @Column(name = "criado_em")
    private LocalDateTime criadoEm = LocalDateTime.now();
}