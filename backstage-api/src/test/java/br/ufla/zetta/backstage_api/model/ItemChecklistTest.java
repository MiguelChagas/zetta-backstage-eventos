package br.ufla.zetta.backstage_api.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


/**
 * Teste Unitário da Entidade ItemChecklist.
 * * Objetivo:
 * Validar a criação de tarefas e o uso correto do Enum de Status (StatusItem).
 * Isso garante que o sistema consegue diferenciar itens PENDENTES de CONCLUIDOS
 * no nível mais básico da aplicação.
 */
public class ItemChecklistTest {

    @Test
    public void deveCriarItemComStatusPendente() {
        ItemChecklist item = new ItemChecklist();
        
        item.setNome("Verificar Cabos de Som");
        item.setStatus(StatusItem.PENDENTE); 
        item.setPrioridade( Prioridade.ALTA);

        Assertions.assertEquals("Verificar Cabos de Som", item.getNome());
        
        Assertions.assertEquals(StatusItem.PENDENTE, item.getStatus());
        
        Assertions.assertNotNull(item.getStatus());
    }

    @Test
    public void devePermitirAlterarStatusParaConcluido() {
        ItemChecklist item = new ItemChecklist();
        item.setStatus(StatusItem.PENDENTE);

        item.setStatus(StatusItem.CONCLUIDO);

        Assertions.assertEquals(StatusItem.CONCLUIDO, item.getStatus());
    }
}