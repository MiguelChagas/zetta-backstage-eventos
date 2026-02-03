import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-checklist',
  templateUrl: './checklist.component.html',
  standalone: false,
})
export class ChecklistComponent implements OnInit {
  eventoId!: number;
  evento: any = { nome: 'Carregando...' };
  tarefas: any[] = [];

  novaTarefa = { nome: '', prioridade: 'MEDIA', dataLimite: '' };

  constructor(
    private route: ActivatedRoute,
    private api: ApiService,
  ) {}

  ngOnInit() {
    this.eventoId = Number(this.route.snapshot.paramMap.get('id'));

    const eventoJson = localStorage.getItem('eventoAtual');
    if (eventoJson) {
      this.evento = JSON.parse(eventoJson);
    }

    this.carregarTarefas();
  }

  carregarTarefas() {
    this.api.getTarefas(this.eventoId).subscribe({
      next: (dados) => {
        this.tarefas = dados.sort((a, b) =>
          a.status === b.status ? 0 : a.status === 'PENDENTE' ? -1 : 1,
        );
      },
      error: (e) => {
        console.error('Erro ao carregar tarefas', e);
        alert('Erro no servidor ao buscar tarefas.');
        this.tarefas = [];
      },
    });
  }

  adicionarTarefa() {
    if (!this.novaTarefa.nome) return;

    const tarefaParaEnviar = {
      nome: this.novaTarefa.nome,
      descricao: '',
      prioridade: this.novaTarefa.prioridade,
      dataLimite: this.novaTarefa.dataLimite || null,
    };

    this.api.adicionarTarefa(tarefaParaEnviar, this.eventoId).subscribe({
      next: () => {
        this.novaTarefa.nome = '';
        this.novaTarefa.dataLimite = '';
        this.carregarTarefas();
      },
      error: (e) => {
        console.error(e);
        alert('Erro ao adicionar tarefa.');
      },
    });
  }

  alternarStatus(item: any) {
    const novoStatus = item.status === 'PENDENTE' ? 'CONCLUIDO' : 'PENDENTE';

    this.api.atualizarStatus(item.id, novoStatus).subscribe({
      next: () => {
        item.status = novoStatus;
      },
      error: (e) => alert('Erro ao atualizar status.'),
    });
  }
}
