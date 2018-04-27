import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { SurveyQuestion } from './survey-question.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<SurveyQuestion>;

@Injectable()
export class SurveyQuestionService {

    private resourceUrl =  SERVER_API_URL + 'api/survey-questions';

    constructor(private http: HttpClient) { }

    create(surveyQuestion: SurveyQuestion): Observable<EntityResponseType> {
        const copy = this.convert(surveyQuestion);
        return this.http.post<SurveyQuestion>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(surveyQuestion: SurveyQuestion): Observable<EntityResponseType> {
        const copy = this.convert(surveyQuestion);
        return this.http.put<SurveyQuestion>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<SurveyQuestion>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<SurveyQuestion[]>> {
        const options = createRequestOption(req);
        return this.http.get<SurveyQuestion[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<SurveyQuestion[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: SurveyQuestion = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<SurveyQuestion[]>): HttpResponse<SurveyQuestion[]> {
        const jsonResponse: SurveyQuestion[] = res.body;
        const body: SurveyQuestion[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to SurveyQuestion.
     */
    private convertItemFromServer(surveyQuestion: SurveyQuestion): SurveyQuestion {
        const copy: SurveyQuestion = Object.assign({}, surveyQuestion);
        return copy;
    }

    /**
     * Convert a SurveyQuestion to a JSON which can be sent to the server.
     */
    private convert(surveyQuestion: SurveyQuestion): SurveyQuestion {
        const copy: SurveyQuestion = Object.assign({}, surveyQuestion);
        return copy;
    }
}
