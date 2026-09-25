import { ApplicationConfig, provideZoneChangeDetection, provideBrowserGlobalErrorListeners } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';

import { provideHttpClient } from "@angular/common/http";
import { providePrimeNG } from 'primeng/config';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import Lara from '@primeng/themes/lara';

import { definePreset } from '@primeng/themes';

const MiTemaPersonalizado = definePreset(Lara, {
  semantic: {
    primary: {
      // --- TINTES CLAROS (Generados para fondos sutiles, focus rings, etc.) ---
      50: '#FEF2F6',  // Un rosa casi blanco
      100: '#FDE0EB', // Rosa pastel muy suave
      200: '#FBC2D8', // Rosa suave
      300: '#F794BA', // Rosa medio

      // --- EL CORAZÓN DEL DEGRADADO (Los 5 colores originales) ---
      400: '#F22E8A', // Tu color 1 (Rosa vibrante) - Ideal para acentos luminosos
      500: '#D941B0', // Tu color 2 (Magenta) - COLOR BASE POR DEFECTO
      600: '#B141BF', // Tu color 3 (Púrpura-Magenta) - ESTADO HOVER (al pasar el mouse)
      700: '#9C41D9', // Tu color 4 (Púrpura) - ESTADO ACTIVE (al hacer clic)
      800: '#9A41F2', // Tu color 5 (Violeta)

      // --- TONOS OSCUROS (Generados para contrastes y textos) ---
      900: '#7A28D0', // Violeta oscuro
      950: '#521396'  // Violeta muy oscuro y profundo
    }
  }
});

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideBrowserGlobalErrorListeners(),
    provideRouter(routes),
    // Peticiones HTTP para conectar con Spring Boot
    provideHttpClient(),
    // Uso de PrimeNG
    provideAnimationsAsync(),
    providePrimeNG({ theme: {
      preset: MiTemaPersonalizado, // Lara,
      options: { darkModeSelector: 'none' }
    } }),
  ],
};
