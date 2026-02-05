package br.ufla.zetta.backstage_api.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

/**
 * Teste Unitário da Entidade Evento.
 * * Objetivo:
 * Verificar se a classe de modelo está comportando-se como um objeto
 * de dados válido. Testamos se os Getters e Setters estão armazenando
 * e recuperando as informações corretamente, garantindo a integridade
 * básica dos dados antes de qualquer interação com o banco.
 */

public class EventoTest {

    @Test
    public void deveCriarEventoComDadosCorretos() {
        Evento evento = new Evento();
        
        evento.setNome("Show de Teste");
        evento.setLocalizacao("Arena Zetta");
        evento.setDataEvento(LocalDate.now().plusDays(10));

        Assertions.assertEquals("Show de Teste", evento.getNome());
        Assertions.assertEquals("Arena Zetta", evento.getLocalizacao());
        Assertions.assertNotNull(evento.getDataEvento());
    }
}