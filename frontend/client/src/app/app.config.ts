import { ApplicationConfig, provideBrowserGlobalErrorListeners } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { provideClientHydration, withEventReplay } from '@angular/platform-browser';
import { provideHttpClient } from '@angular/common/http';
import { ApiModule } from 'api';
<<<<<<< HEAD
import { Field, Servico } from 'zouund';
=======
import { Field } from 'zouund';
>>>>>>> ae66475a3e15bf138f7414c8b11e7496a98b6c0f

export const appConfig: ApplicationConfig = {
  providers: [
    provideHttpClient(),
    provideBrowserGlobalErrorListeners(),
    provideRouter(routes), provideClientHydration(withEventReplay()),
    ...ApiModule.forRoot({ rootUrl: 'http://localhost:3000' }).providers!,
<<<<<<< HEAD
    Servico,
=======
>>>>>>> ae66475a3e15bf138f7414c8b11e7496a98b6c0f
  ]
};
