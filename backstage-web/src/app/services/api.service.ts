import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  private baseUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  cadastrar(usuario: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/usuarios`, usuario);
  }

  login(loginData: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/usuarios/login`, loginData);
  }

  getEventos(usuarioId: number): Observable<any[]> {
    return this.http.get<any[]>(
      `${this.baseUrl}/eventos/produtor/${usuarioId}`,
    );
  }

  criarEvento(evento: any, usuarioId: number): Observable<any> {
    return this.http.post(
      `${this.baseUrl}/eventos?usuarioId=${usuarioId}`,
      evento,
    );
  }

  getTarefas(
    eventoId: number,
    status?: string,
    categoria?: string,
  ): Observable<any[]> {
    let url = `${this.baseUrl}/itens/evento/${eventoId}`;
    const params: string[] = [];

    if (status && status !== 'TODOS') {
      params.push(`status=${status}`);
    }

    if (categoria && categoria !== 'TODOS') {
      params.push(`categoria=${categoria}`);
    }

    if (params.length > 0) {
      url += '?' + params.join('&');
    }

    return this.http.get<any[]>(url);
  }

  adicionarTarefa(tarefa: any, eventoId: number): Observable<any> {
    return this.http.post(`${this.baseUrl}/itens?eventoId=${eventoId}`, tarefa);
  }

  atualizarStatus(itemId: number, novoStatus: string): Observable<any> {
    return this.http.patch(
      `${this.baseUrl}/itens/${itemId}/status?status=${novoStatus}`,
      {},
    );
  }

  deletarEvento(eventoId: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/eventos/${eventoId}`);
  }

  deletarItem(itemId: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/itens/${itemId}`);
  }
}
