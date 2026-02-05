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

  // Filtros
  filtroStatus: string = 'TODOS';
  filtroCategoria: string = 'TODOS';

  novaTarefa = {
    nome: '',
    prioridade: 'MEDIA',
    dataLimite: '',
    categoria: 'GERAL',
    descricao: '',
  };

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
    this.api
      .getTarefas(this.eventoId, this.filtroStatus, this.filtroCategoria)
      .subscribe({
        next: (dados) => {
          this.tarefas = dados.sort((a, b) =>
            a.status === b.status ? 0 : a.status === 'PENDENTE' ? -1 : 1,
          );
        },
        error: (e) => console.error('Erro ao carregar tarefas', e),
      });
  }

  aplicarFiltro() {
    this.carregarTarefas();
  }

  limparFiltros() {
    this.filtroStatus = 'TODOS';
    this.filtroCategoria = 'TODOS';
    this.carregarTarefas();
  }

  adicionarTarefa() {
    if (!this.novaTarefa.nome) return;

    const tarefaParaEnviar: any = { ...this.novaTarefa };

    if (tarefaParaEnviar.dataLimite) {
      if (tarefaParaEnviar.dataLimite.length === 16) {
        tarefaParaEnviar.dataLimite += ':00';
      }
    } else {
      tarefaParaEnviar.dataLimite = null;
    }

    this.api.adicionarTarefa(tarefaParaEnviar, this.eventoId).subscribe({
      next: () => {
        this.novaTarefa = {
          nome: '',
          prioridade: 'MEDIA',
          dataLimite: '',
          categoria: 'GERAL',
          descricao: '',
        };
        this.carregarTarefas();
      },
      error: (e) => {
        console.error('Erro ao criar tarefa:', e);
        alert('Erro ao criar tarefa.');
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

  deletarItem(item: any) {
    if (!confirm(`Tem certeza que deseja deletar "${item.nome}"?`)) {
      return;
    }

    this.api.deletarItem(item.id).subscribe({
      next: () => {
        this.carregarTarefas();
      },
      error: () => alert('Erro ao deletar item.'),
    });
  }
}
