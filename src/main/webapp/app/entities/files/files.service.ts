import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { Files } from './files.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Files>;

@Injectable()
export class FilesService {

    private resourceUrl =  SERVER_API_URL + 'api/files';

    constructor(private http: HttpClient) { }

    create(files: Files): Observable<EntityResponseType> {
        const copy = this.convert(files);
        return this.http.post<Files>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(files: Files): Observable<EntityResponseType> {
        const copy = this.convert(files);
        return this.http.put<Files>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Files>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Files[]>> {
        const options = createRequestOption(req);
        return this.http.get<Files[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Files[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Files = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Files[]>): HttpResponse<Files[]> {
        const jsonResponse: Files[] = res.body;
        const body: Files[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Files.
     */
    private convertItemFromServer(files: Files): Files {
        const copy: Files = Object.assign({}, files);
        return copy;
    }

    /**
     * Convert a Files to a JSON which can be sent to the server.
     */
    private convert(files: Files): Files {
        const copy: Files = Object.assign({}, files);
        return copy;
    }
}
