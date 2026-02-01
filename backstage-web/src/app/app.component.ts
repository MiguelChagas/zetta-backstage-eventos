import { Component, OnInit } from '@angular/core';
import { ApiService } from './services/api.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  standalone: false,
})
export class AppComponent implements OnInit {
  eventos: any[] = [];
  tarefas: any[] = [];
  eventoSelecionado: any = null;

  constructor(private api: ApiService) {}

  ngOnInit() {
    this.carregarEventos();
  }

  carregarEventos() {
    this.api.getEventos(1).subscribe((dados) => {
      this.eventos = dados;
    });
  }

  verTarefas(evento: any) {
    this.eventoSelecionado = evento;
    this.api.getTarefas(evento.id).subscribe((dados) => {
      this.tarefas = dados;
    });
  }
}
