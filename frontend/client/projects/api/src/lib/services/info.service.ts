/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { apiControllerManter } from '../fn/info/api-controller-manter';
import { ApiControllerManter$Params } from '../fn/info/api-controller-manter';
import { apiControllerObterLista } from '../fn/info/api-controller-obter-lista';
import { ApiControllerObterLista$Params } from '../fn/info/api-controller-obter-lista';
import { Resultado } from '../models/resultado';

@Injectable()
export class InfoService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `apiControllerObterLista()` */
  static readonly ApiControllerObterListaPath = '/Info/ObterLista';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `apiControllerObterLista()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  apiControllerObterLista$Response(params: ApiControllerObterLista$Params, context?: HttpContext): Observable<StrictHttpResponse<Resultado>> {
    return apiControllerObterLista(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `apiControllerObterLista$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  apiControllerObterLista(params: ApiControllerObterLista$Params, context?: HttpContext): Observable<Resultado> {
    return this.apiControllerObterLista$Response(params, context).pipe(
      map((r: StrictHttpResponse<Resultado>): Resultado => r.body)
    );
  }

  /** Path part for operation `apiControllerManter()` */
  static readonly ApiControllerManterPath = '/Info/Manter';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `apiControllerManter()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  apiControllerManter$Response(params: ApiControllerManter$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return apiControllerManter(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `apiControllerManter$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  apiControllerManter(params: ApiControllerManter$Params, context?: HttpContext): Observable<void> {
    return this.apiControllerManter$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

}
