import { Component, EventEmitter, Inject, Input, OnInit, Optional, Output } from '@angular/core';
import { Coluna, DataGrid } from '../data-grid/data-grid';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Editor } from '../editor/editor';
import { ActivatedRoute } from '@angular/router';
<<<<<<< HEAD
import { Servico } from 'zouund';
=======
>>>>>>> ae66475a3e15bf138f7414c8b11e7496a98b6c0f

export interface Field {
  title?: string;
  field?: string;
  tipo?: string;
  visivel?: boolean;
}
@Component({
  selector: 'nx-visao-geral',
  imports: [
    CommonModule,
    DataGrid,
    ReactiveFormsModule,
    Editor,
  ],
  templateUrl: './visao-geral.html',
  styleUrl: './visao-geral.scss',
})
export class VisaoGeral implements OnInit {
  constructor(
    private route: ActivatedRoute,
    @Inject('FIELDS') @Optional() fields?: Field[],
<<<<<<< HEAD
    @Optional() private servico?: Servico
  ) {
=======
  ){
>>>>>>> ae66475a3e15bf138f7414c8b11e7496a98b6c0f
    this.fields = fields;
  }
  /* form?: FormGroup; */
  private _fields?: Field[] | undefined;
  public get fields(): Field[] | undefined {
    return this._fields;
  }
  @Input()
  public set fields(value: Field[] | undefined) {
    if (this._fields === value) return;
    this._fields = value;
    if (value) this.cols = value.filter(f => f.visivel !== false).map(f => ({
      field: f.field,
      headerName: f.title,
    } as Coluna))
    if (value) {
      /* const ff: any = {};
      value.forEach(f => {
        if (f.field) ff[f.field!] = [,]
      }) */
      /*  this.form = this.formBuilder.group(ff);
       this.form.valueChanges.subscribe(props => {
         if (this.itemSelecionado) {
           Object.assign(this.itemSelecionado, props);
         }
       }) */
    }
  }
  private _itemSelecionado?: any;
  public get itemSelecionado(): any {
    return this._itemSelecionado;
  }
  @Input()
  public set itemSelecionado(value: any) {
    if (this._itemSelecionado === value) return;
    this._itemSelecionado = value;
    /*  this.form?.reset(value); */
  }
  @Input() lista: any;
  cols?: Coluna[];
  async novo() {
    const novo = {};
    this.lista = [novo, ...(this.lista || [])];
    this.itemSelecionado = novo;
  }
  @Output() confirmar = new EventEmitter<any>();

<<<<<<< HEAD
  ngOnInit() {
    this.route.data.subscribe(async data => {
      this.fields = data['fields']; // Output: 11
      setTimeout(() => {
        this.pesquisar();
      })
    });
    this.confirmar.subscribe(async (item) => {
      await this.servico?.Manter(item)
    })
  }
  async pesquisar() {
    this.lista = await this.servico?.ObterLista();
=======
   ngOnInit() {
    this.route.data.subscribe(async data => {
      this.fields = data['fields']; // Output: 11
    });
>>>>>>> ae66475a3e15bf138f7414c8b11e7496a98b6c0f
  }
}
