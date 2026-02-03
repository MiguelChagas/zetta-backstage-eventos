import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  standalone: false,
})
export class LoginComponent {
  email = '';
  senha = '';
  erro = '';

  constructor(
    private api: ApiService,
    private router: Router,
  ) {}

  fazerLogin() {
    this.api.login({ email: this.email, senha: this.senha }).subscribe({
      next: (usuario) => {
        localStorage.setItem('usuario', JSON.stringify(usuario));
        this.router.navigate(['/dashboard']);
      },
      error: () => (this.erro = 'Email ou senha inválidos'),
    });
  }
}
