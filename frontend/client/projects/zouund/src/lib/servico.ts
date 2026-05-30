import { HttpClient } from "@angular/common/http";
import { Inject, Injectable } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { lastValueFrom } from "rxjs";

@Injectable()
export class Servico {
    private endpoint?: string;
    constructor(
        private route: ActivatedRoute,
        private http: HttpClient,
    ) {
        route.data.subscribe(data => {
            this.endpoint = data['endpoint']
        });
    }
    async ObterLista<T>() {
        try {
            return lastValueFrom(this.http.get<T[]>(this.endpoint + '/obterLista'))
        } catch (error) {
            return null
        }
    }
    async Manter<T>(objeto: T) {
        try {
            return lastValueFrom(this.http.post<T>(this.endpoint + '/manter', objeto))
        } catch (error) {
            return null
        }
    }

}