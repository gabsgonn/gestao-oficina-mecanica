import { Component, signal } from '@angular/core';
import { RouterOutlet, RouterLinkWithHref } from '@angular/router';
import { Modelos } from './modelos';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'nx-root',
  imports: [
    RouterOutlet,
    RouterLinkWithHref,
    CommonModule,
  ],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  protected Modelos = Modelos;
  protected readonly title = signal('oficina-cliente');
}
