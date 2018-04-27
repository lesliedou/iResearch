import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { Acl } from './acl.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Acl>;

@Injectable()
export class AclService {

    private resourceUrl =  SERVER_API_URL + 'api/acls';

    constructor(private http: HttpClient) { }

    create(acl: Acl): Observable<EntityResponseType> {
        const copy = this.convert(acl);
        return this.http.post<Acl>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(acl: Acl): Observable<EntityResponseType> {
        const copy = this.convert(acl);
        return this.http.put<Acl>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Acl>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Acl[]>> {
        const options = createRequestOption(req);
        return this.http.get<Acl[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Acl[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Acl = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Acl[]>): HttpResponse<Acl[]> {
        const jsonResponse: Acl[] = res.body;
        const body: Acl[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Acl.
     */
    private convertItemFromServer(acl: Acl): Acl {
        const copy: Acl = Object.assign({}, acl);
        return copy;
    }

    /**
     * Convert a Acl to a JSON which can be sent to the server.
     */
    private convert(acl: Acl): Acl {
        const copy: Acl = Object.assign({}, acl);
        return copy;
    }
}
