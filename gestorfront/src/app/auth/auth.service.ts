import { computed, inject, Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';

import { environment } from '../../environments/environment';

export interface AuthSession {
  accessToken: string;
  username: string;
  role: 'ADMIN' | 'INVITADO';
}

@Injectable({ providedIn: 'root' })
export class AuthService {
  private readonly http = inject(HttpClient);
  private readonly apiUrl = `${environment.apiUrl}/auth`;
  private readonly sessionKey = 'gestor-productos-session';
  readonly session = signal<AuthSession | null>(this.readSession());
  readonly esAdmin = computed(() => this.session()?.role === 'ADMIN');

  token(): string | null {
    return this.session()?.accessToken ?? null;
  }

  login(username: string, password: string): Observable<AuthSession> {
    return this.http.post<AuthSession>(`${this.apiUrl}/login`, { username, password })
      .pipe(tap(session => this.saveSession(session)));
  }

  register(username: string, password: string): Observable<AuthSession> {
    return this.http.post<AuthSession>(`${this.apiUrl}/register`, { username, password })
      .pipe(tap(session => this.saveSession(session)));
  }

  logout(): void {
    localStorage.removeItem(this.sessionKey);
    this.session.set(null);
  }

  private saveSession(session: AuthSession): void {
    localStorage.setItem(this.sessionKey, JSON.stringify(session));
    this.session.set(session);
  }

  private readSession(): AuthSession | null {
    try {
      const stored = localStorage.getItem(this.sessionKey);
      return stored ? JSON.parse(stored) as AuthSession : null;
    } catch {
      return null;
    }
  }
}