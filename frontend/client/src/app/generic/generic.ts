import { AfterViewInit, Component, Input, OnInit, Optional } from '@angular/core';
import { Field, VisaoGeral } from 'zouund';
import { ApiConfiguration, InfoService } from 'api';
import { BehaviorSubject, lastValueFrom } from 'rxjs';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';

export interface Prop {
  type: string,
  title: string,
  nullable: boolean,
  required: boolean,
  allOf: string,
}

@Component({
  selector: 'nx-generic',
  imports: [
    CommonModule,
    VisaoGeral
  ],
  templateUrl: './generic.html',
  styleUrl: './generic.scss',
})
export class Generic implements AfterViewInit, OnInit {
  constructor(
    private config: ApiConfiguration,
    private http: HttpClient,
    private route: ActivatedRoute,
    @Optional() private apis?: InfoService,
  ) { }
  private _tipo?: string | undefined;
  public get tipo(): string | undefined {
    return this._tipo;
  }
  @Input()
  public set tipo(value: string | undefined) {
    if (this._tipo === value) return;
    this._tipo = value;
    (async () => {
      const api_contract: any = await lastValueFrom(this.http.get(this.config.rootUrl + '/api-json'));
      this.properties = api_contract?.components?.schemas[this.tipo!]?.properties as { [prop: string]: Prop };
    })()
  }
  private _properties?: { [prop: string]: Prop } | undefined;
  public get properties(): { [prop: string]: Prop } | undefined {
    return this._properties;
  }
  private types: any = {
    string: 'text',
  };
  public set properties(value: { [prop: string]: Prop } | undefined) {
    if (this._properties === value) return;
    this._properties = value;
    if (value)
      this.fields = Object.entries(value).map((entrie) => {
        const field = {
          visivel: ['id'].indexOf(entrie[0]) === -1,
          field: entrie[0],
          title: entrie[1].title || entrie[0],
          tipo: this.types[entrie[1].type] || entrie[1].type || 'text',
        } as Field;

        return field;
      });
  }
  ngOnInit() {
    this.route.data.subscribe(async data => {
      this.tipo = data['Schema']; // Output: 11
    });
  }
  fields?: Field[];
  lista = new BehaviorSubject<any[]>([]);
  async ngAfterViewInit() {
    if (this.apis) {
      setTimeout(async () => {
        this.pesquisar()
      }, 0)
    }
  }
  async pesquisar() {
    if (this.apis) this.lista.next(((await lastValueFrom(
      this.apis.apiControllerObterLista({
        body: {
          tipo: [
            this.tipo!.toLocaleLowerCase() as any + 's' as any
          ]
        }
      }))) as any)[this.tipo!.toLocaleLowerCase() as any + 's'])
  }
  async manter(item: any) {
    try {
      if (item.id === null) delete item.id;
      if (this.apis)
        await lastValueFrom(this.apis.apiControllerManter({
          body: {
            [this.tipo!.toLocaleLowerCase() as any + 's']: [item]
          }
        }));

    } catch (error) {
    }
    this.pesquisar();
  }
}
