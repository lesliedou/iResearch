import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { OnsiteProcess } from './onsite-process.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<OnsiteProcess>;

@Injectable()
export class OnsiteProcessService {

    private resourceUrl =  SERVER_API_URL + 'api/onsite-processes';

    constructor(private http: HttpClient) { }

    create(onsiteProcess: OnsiteProcess): Observable<EntityResponseType> {
        const copy = this.convert(onsiteProcess);
        return this.http.post<OnsiteProcess>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(onsiteProcess: OnsiteProcess): Observable<EntityResponseType> {
        const copy = this.convert(onsiteProcess);
        return this.http.put<OnsiteProcess>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<OnsiteProcess>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<OnsiteProcess[]>> {
        const options = createRequestOption(req);
        return this.http.get<OnsiteProcess[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<OnsiteProcess[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: OnsiteProcess = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<OnsiteProcess[]>): HttpResponse<OnsiteProcess[]> {
        const jsonResponse: OnsiteProcess[] = res.body;
        const body: OnsiteProcess[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to OnsiteProcess.
     */
    private convertItemFromServer(onsiteProcess: OnsiteProcess): OnsiteProcess {
        const copy: OnsiteProcess = Object.assign({}, onsiteProcess);
        return copy;
    }

    /**
     * Convert a OnsiteProcess to a JSON which can be sent to the server.
     */
    private convert(onsiteProcess: OnsiteProcess): OnsiteProcess {
        const copy: OnsiteProcess = Object.assign({}, onsiteProcess);
        return copy;
    }
}
