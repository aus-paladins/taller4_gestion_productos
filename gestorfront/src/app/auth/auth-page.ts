import { Component, inject } from '@angular/core';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from './auth.service';

@Component({
  selector: 'app-auth-page',
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './auth-page.html',
  styleUrl: './auth-page.scss'
})
export class AuthPage {
  private readonly formBuilder = inject(FormBuilder);
  private readonly auth = inject(AuthService);
  private readonly router = inject(Router);

  readonly esRegistro = this.router.url.startsWith('/register');
  cargando = false;
  error = '';
  readonly form = this.formBuilder.nonNullable.group({
    username: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(80)]],
    password: ['', [Validators.required, Validators.minLength(8)]]
  });

  enviar(): void {
    if (this.form.invalid || this.cargando) {
      this.form.markAllAsTouched();
      return;
    }

    this.cargando = true;
    this.error = '';
    const { username, password } = this.form.getRawValue();
    const request = this.esRegistro
      ? this.auth.register(username, password)
      : this.auth.login(username, password);

    request.subscribe({
      next: () => this.router.navigate(['/productos']),
      error: (response) => {
        this.error = response.status === 409
          ? 'Ese nombre de usuario ya está registrado.'
          : this.esRegistro
            ? 'No se pudo crear la cuenta. Verificá los datos e intentá nuevamente.'
            : 'Usuario o contraseña incorrectos.';
        this.cargando = false;
      }
    });
  }
}