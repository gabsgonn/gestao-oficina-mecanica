import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Field } from 'zouund';

@Component({
  selector: 'nx-input',
  imports: [
    CommonModule,
    ReactiveFormsModule,
  ],
  templateUrl: './input.html',
  styleUrl: './input.scss',
})
export class InputComponent {
  @Input() form?: FormGroup;
  @Input() field?: Field;
}
