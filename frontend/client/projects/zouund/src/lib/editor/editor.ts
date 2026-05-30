import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Field } from 'zouund';
import { InputComponent } from '../input/input';

@Component({
  selector: 'nx-editor',
  imports: [
    ReactiveFormsModule,
    CommonModule,
    InputComponent,
  ],
  templateUrl: './editor.html',
  styleUrl: './editor.scss',
})
export class Editor {
  private _source?: any;
  public get source(): any {
    return this._source;
  }
  @Input()
  public set source(value: any) {
    if (this._source === value) return;
    this._source = value;
    if (this.form) this.form.reset(value);
  }
  // @Output() itemSelecionado = new EventEmitter<any>();
  constructor(
    private formBuilder: FormBuilder
  ) { }
  form?: FormGroup;
  private _fields?: Field[] | undefined;
  public get fields(): Field[] | undefined {
    return this._fields;
  }
  @Input()
  public set fields(value: Field[] | undefined) {
    if (this._fields === value) return;
    this._fields = value;
    if (value) {
      const ff: any = {};
      value.forEach(f => {
        if (f.field) ff[f.field!] = [,]
      })
      if (!this.form) {
        this.form = this.formBuilder.group(ff);
        this.form.valueChanges.subscribe(props => {
          if (this.source) {
            Object.assign(this.source, props);
          }
        })
      }
    }
  }
}
