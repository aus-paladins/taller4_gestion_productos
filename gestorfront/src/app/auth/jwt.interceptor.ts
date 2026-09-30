import { inject } from '@angular/core';
import { HttpErrorResponse, HttpInterceptorFn } from '@angular/common/http';
import { Router } from '@angular/router';
import { catchError, throwError } from 'rxjs';
import { AuthService } from './auth.service';

export const jwtInterceptor: HttpInterceptorFn = (request, next) => {
  const auth = inject(AuthService);
  const router = inject(Router);

  const cerrarSesion = () => {
    auth.logout();
    router.navigate(['/login']);
  };

  // Los GET son públicos y el back no rechaza un token vencido ahí, así que se chequea el `exp` antes de enviar
  if (auth.sesionExpirada()) {
    cerrarSesion();
    return next(request);
  }

  const token = auth.token();
  if (!token) {
    return next(request);
  }

  return next(request.clone({
    setHeaders: { Authorization: `Bearer ${token}` }
  })).pipe(
    catchError((error: HttpErrorResponse) => {
      if (error.status === 401) {
        cerrarSesion();
      }
      return throwError(() => error);
    })
  );
};
