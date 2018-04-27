import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { Onsite } from './onsite.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Onsite>;

@Injectable()
export class OnsiteService {

    private resourceUrl =  SERVER_API_URL + 'api/onsites';

    constructor(private http: HttpClient) { }

    create(onsite: Onsite): Observable<EntityResponseType> {
        const copy = this.convert(onsite);
        return this.http.post<Onsite>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(onsite: Onsite): Observable<EntityResponseType> {
        const copy = this.convert(onsite);
        return this.http.put<Onsite>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Onsite>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Onsite[]>> {
        const options = createRequestOption(req);
        return this.http.get<Onsite[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Onsite[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Onsite = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Onsite[]>): HttpResponse<Onsite[]> {
        const jsonResponse: Onsite[] = res.body;
        const body: Onsite[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Onsite.
     */
    private convertItemFromServer(onsite: Onsite): Onsite {
        const copy: Onsite = Object.assign({}, onsite);
        return copy;
    }

    /**
     * Convert a Onsite to a JSON which can be sent to the server.
     */
    private convert(onsite: Onsite): Onsite {
        const copy: Onsite = Object.assign({}, onsite);
        return copy;
    }
}
