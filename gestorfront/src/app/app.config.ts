import { ApplicationConfig, provideBrowserGlobalErrorListeners } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';

import { provideHttpClient } from "@angular/common/http";
import { providePrimeNG } from 'primeng/config';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import Aura from '@primeng/themes/aura';

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideBrowserGlobalErrorListeners(),
    provideRouter(routes),
    // Peticiones HTTP para conectar con Spring Boot
    provideHttpClient(),
    // Uso de PrimeNG
    provideAnimationsAsync(),
    providePrimeNG({ theme: { preset: Aura } })
  ]
};
