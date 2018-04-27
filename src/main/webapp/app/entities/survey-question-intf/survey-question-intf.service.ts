import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { SurveyQuestionIntf } from './survey-question-intf.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<SurveyQuestionIntf>;

@Injectable()
export class SurveyQuestionIntfService {

    private resourceUrl =  SERVER_API_URL + 'api/survey-question-intfs';

    constructor(private http: HttpClient) { }

    create(surveyQuestionIntf: SurveyQuestionIntf): Observable<EntityResponseType> {
        const copy = this.convert(surveyQuestionIntf);
        return this.http.post<SurveyQuestionIntf>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(surveyQuestionIntf: SurveyQuestionIntf): Observable<EntityResponseType> {
        const copy = this.convert(surveyQuestionIntf);
        return this.http.put<SurveyQuestionIntf>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<SurveyQuestionIntf>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<SurveyQuestionIntf[]>> {
        const options = createRequestOption(req);
        return this.http.get<SurveyQuestionIntf[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<SurveyQuestionIntf[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: SurveyQuestionIntf = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<SurveyQuestionIntf[]>): HttpResponse<SurveyQuestionIntf[]> {
        const jsonResponse: SurveyQuestionIntf[] = res.body;
        const body: SurveyQuestionIntf[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to SurveyQuestionIntf.
     */
    private convertItemFromServer(surveyQuestionIntf: SurveyQuestionIntf): SurveyQuestionIntf {
        const copy: SurveyQuestionIntf = Object.assign({}, surveyQuestionIntf);
        return copy;
    }

    /**
     * Convert a SurveyQuestionIntf to a JSON which can be sent to the server.
     */
    private convert(surveyQuestionIntf: SurveyQuestionIntf): SurveyQuestionIntf {
        const copy: SurveyQuestionIntf = Object.assign({}, surveyQuestionIntf);
        return copy;
    }
}
