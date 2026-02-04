import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  standalone: false,
})
export class DashboardComponent implements OnInit {
  usuario: any = null;
  eventos: any[] = [];

  mostrandoFormulario = false;
  novoEvento = { nome: '', dataEvento: '', localizacao: '' };

  constructor(
    private api: ApiService,
    private router: Router,
  ) {}

  ngOnInit() {
    const userJson = localStorage.getItem('usuario');
    if (!userJson) {
      this.router.navigate(['/login']);
      return;
    }
    this.usuario = JSON.parse(userJson);
    this.carregarEventos();
  }

  carregarEventos() {
    this.api.getEventos(this.usuario.id).subscribe({
      next: (dados) => (this.eventos = dados),
      error: (e) => console.error('Erro ao buscar eventos:', e),
    });
  }

  abrirEvento(evento: any) {
    localStorage.setItem('eventoAtual', JSON.stringify(evento));
    this.router.navigate(['/evento', evento.id, 'checklist']);
  }

  toggleForm() {
    this.mostrandoFormulario = !this.mostrandoFormulario;
  }

  salvarEvento() {
    if (!this.novoEvento.nome) return;

    this.api.criarEvento(this.novoEvento, this.usuario.id).subscribe(() => {
      this.novoEvento = { nome: '', dataEvento: '', localizacao: '' };
      this.mostrandoFormulario = false;
      this.carregarEventos();
      alert('Evento criado com sucesso!');
    });
  }

  deletarEvento(evento: any, $event: Event) {
    $event.stopPropagation();

    if (!confirm(`Tem certeza que deseja deletar o evento "${evento.nome}"?`)) {
      return;
    }

    this.api.deletarEvento(evento.id).subscribe({
      next: () => {
        this.carregarEventos();
        alert('Evento deletado com sucesso!');
      },
      error: () => alert('Erro ao deletar evento.'),
    });
  }

  sair() {
    localStorage.clear();
    this.router.navigate(['/login']);
  }
}
