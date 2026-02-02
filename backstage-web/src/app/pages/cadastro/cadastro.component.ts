import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  standalone: false,
})
export class CadastroComponent {
  usuario = { nome: '', email: '', senha: '' };
  msgErro = '';

  constructor(
    private api: ApiService,
    private router: Router,
  ) {}

  fazerCadastro() {
    this.api.cadastrar(this.usuario).subscribe({
      next: (resposta) => {
        alert('Conta criada com sucesso! Faça login para continuar.');
        this.router.navigate(['/login']);
      },
      error: (erro) => {
        this.msgErro =
          erro.error?.message || 'Erro ao cadastrar. Tente outro email.';
      },
    });
  }
}
