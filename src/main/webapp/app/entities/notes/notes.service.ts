import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { Notes } from './notes.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Notes>;

@Injectable()
export class NotesService {

    private resourceUrl =  SERVER_API_URL + 'api/notes';

    constructor(private http: HttpClient) { }

    create(notes: Notes): Observable<EntityResponseType> {
        const copy = this.convert(notes);
        return this.http.post<Notes>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(notes: Notes): Observable<EntityResponseType> {
        const copy = this.convert(notes);
        return this.http.put<Notes>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Notes>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Notes[]>> {
        const options = createRequestOption(req);
        return this.http.get<Notes[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Notes[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Notes = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Notes[]>): HttpResponse<Notes[]> {
        const jsonResponse: Notes[] = res.body;
        const body: Notes[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Notes.
     */
    private convertItemFromServer(notes: Notes): Notes {
        const copy: Notes = Object.assign({}, notes);
        return copy;
    }

    /**
     * Convert a Notes to a JSON which can be sent to the server.
     */
    private convert(notes: Notes): Notes {
        const copy: Notes = Object.assign({}, notes);
        return copy;
    }
}
