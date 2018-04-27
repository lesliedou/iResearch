import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { MonthlyReport } from './monthly-report.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<MonthlyReport>;

@Injectable()
export class MonthlyReportService {

    private resourceUrl =  SERVER_API_URL + 'api/monthly-reports';

    constructor(private http: HttpClient) { }

    create(monthlyReport: MonthlyReport): Observable<EntityResponseType> {
        const copy = this.convert(monthlyReport);
        return this.http.post<MonthlyReport>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(monthlyReport: MonthlyReport): Observable<EntityResponseType> {
        const copy = this.convert(monthlyReport);
        return this.http.put<MonthlyReport>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<MonthlyReport>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<MonthlyReport[]>> {
        const options = createRequestOption(req);
        return this.http.get<MonthlyReport[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<MonthlyReport[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: MonthlyReport = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<MonthlyReport[]>): HttpResponse<MonthlyReport[]> {
        const jsonResponse: MonthlyReport[] = res.body;
        const body: MonthlyReport[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to MonthlyReport.
     */
    private convertItemFromServer(monthlyReport: MonthlyReport): MonthlyReport {
        const copy: MonthlyReport = Object.assign({}, monthlyReport);
        return copy;
    }

    /**
     * Convert a MonthlyReport to a JSON which can be sent to the server.
     */
    private convert(monthlyReport: MonthlyReport): MonthlyReport {
        const copy: MonthlyReport = Object.assign({}, monthlyReport);
        return copy;
    }
}
