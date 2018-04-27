import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { SurveyQuestionFlow } from './survey-question-flow.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<SurveyQuestionFlow>;

@Injectable()
export class SurveyQuestionFlowService {

    private resourceUrl =  SERVER_API_URL + 'api/survey-question-flows';

    constructor(private http: HttpClient) { }

    create(surveyQuestionFlow: SurveyQuestionFlow): Observable<EntityResponseType> {
        const copy = this.convert(surveyQuestionFlow);
        return this.http.post<SurveyQuestionFlow>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(surveyQuestionFlow: SurveyQuestionFlow): Observable<EntityResponseType> {
        const copy = this.convert(surveyQuestionFlow);
        return this.http.put<SurveyQuestionFlow>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<SurveyQuestionFlow>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<SurveyQuestionFlow[]>> {
        const options = createRequestOption(req);
        return this.http.get<SurveyQuestionFlow[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<SurveyQuestionFlow[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: SurveyQuestionFlow = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<SurveyQuestionFlow[]>): HttpResponse<SurveyQuestionFlow[]> {
        const jsonResponse: SurveyQuestionFlow[] = res.body;
        const body: SurveyQuestionFlow[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to SurveyQuestionFlow.
     */
    private convertItemFromServer(surveyQuestionFlow: SurveyQuestionFlow): SurveyQuestionFlow {
        const copy: SurveyQuestionFlow = Object.assign({}, surveyQuestionFlow);
        return copy;
    }

    /**
     * Convert a SurveyQuestionFlow to a JSON which can be sent to the server.
     */
    private convert(surveyQuestionFlow: SurveyQuestionFlow): SurveyQuestionFlow {
        const copy: SurveyQuestionFlow = Object.assign({}, surveyQuestionFlow);
        return copy;
    }
}
