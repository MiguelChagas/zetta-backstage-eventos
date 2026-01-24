package br.ufla.zetta.backstage_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank @Email
    @Column(unique = true)
    private String email;

    @NotBlank @Size(min = 6)
    private String senha;

    @Column(name = "criado_em")
    private LocalDateTime criadoEm = LocalDateTime.now();
}