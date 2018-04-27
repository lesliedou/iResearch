import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { ConfigAcl } from './config-acl.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<ConfigAcl>;

@Injectable()
export class ConfigAclService {

    private resourceUrl =  SERVER_API_URL + 'api/config-acls';

    constructor(private http: HttpClient) { }

    create(configAcl: ConfigAcl): Observable<EntityResponseType> {
        const copy = this.convert(configAcl);
        return this.http.post<ConfigAcl>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(configAcl: ConfigAcl): Observable<EntityResponseType> {
        const copy = this.convert(configAcl);
        return this.http.put<ConfigAcl>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ConfigAcl>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<ConfigAcl[]>> {
        const options = createRequestOption(req);
        return this.http.get<ConfigAcl[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<ConfigAcl[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: ConfigAcl = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<ConfigAcl[]>): HttpResponse<ConfigAcl[]> {
        const jsonResponse: ConfigAcl[] = res.body;
        const body: ConfigAcl[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to ConfigAcl.
     */
    private convertItemFromServer(configAcl: ConfigAcl): ConfigAcl {
        const copy: ConfigAcl = Object.assign({}, configAcl);
        return copy;
    }

    /**
     * Convert a ConfigAcl to a JSON which can be sent to the server.
     */
    private convert(configAcl: ConfigAcl): ConfigAcl {
        const copy: ConfigAcl = Object.assign({}, configAcl);
        return copy;
    }
}
