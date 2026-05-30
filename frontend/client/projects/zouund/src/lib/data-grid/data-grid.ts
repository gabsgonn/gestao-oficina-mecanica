import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';

export interface Coluna {
  field: string;
  headerName: string;
}

@Component({
  selector: 'nx-data-grid',
  imports: [
    CommonModule,
  ],
  templateUrl: './data-grid.html',
  styleUrl: './data-grid.css',
})
export class DataGrid {
  @Output() select = new EventEmitter<any>;
  @Input() colunas?: Coluna[]
  @Input() origem?: any[];
}
