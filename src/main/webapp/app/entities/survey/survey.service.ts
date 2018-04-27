import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { Survey } from './survey.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Survey>;

@Injectable()
export class SurveyService {

    private resourceUrl =  SERVER_API_URL + 'api/surveys';

    constructor(private http: HttpClient) { }

    create(survey: Survey): Observable<EntityResponseType> {
        const copy = this.convert(survey);
        return this.http.post<Survey>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(survey: Survey): Observable<EntityResponseType> {
        const copy = this.convert(survey);
        return this.http.put<Survey>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Survey>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Survey[]>> {
        const options = createRequestOption(req);
        return this.http.get<Survey[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Survey[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Survey = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Survey[]>): HttpResponse<Survey[]> {
        const jsonResponse: Survey[] = res.body;
        const body: Survey[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Survey.
     */
    private convertItemFromServer(survey: Survey): Survey {
        const copy: Survey = Object.assign({}, survey);
        return copy;
    }

    /**
     * Convert a Survey to a JSON which can be sent to the server.
     */
    private convert(survey: Survey): Survey {
        const copy: Survey = Object.assign({}, survey);
        return copy;
    }
}
