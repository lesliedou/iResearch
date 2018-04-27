import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { UploadInfo } from './upload-info.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<UploadInfo>;

@Injectable()
export class UploadInfoService {

    private resourceUrl =  SERVER_API_URL + 'api/upload-infos';

    constructor(private http: HttpClient) { }

    create(uploadInfo: UploadInfo): Observable<EntityResponseType> {
        const copy = this.convert(uploadInfo);
        return this.http.post<UploadInfo>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(uploadInfo: UploadInfo): Observable<EntityResponseType> {
        const copy = this.convert(uploadInfo);
        return this.http.put<UploadInfo>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<UploadInfo>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<UploadInfo[]>> {
        const options = createRequestOption(req);
        return this.http.get<UploadInfo[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<UploadInfo[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: UploadInfo = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<UploadInfo[]>): HttpResponse<UploadInfo[]> {
        const jsonResponse: UploadInfo[] = res.body;
        const body: UploadInfo[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to UploadInfo.
     */
    private convertItemFromServer(uploadInfo: UploadInfo): UploadInfo {
        const copy: UploadInfo = Object.assign({}, uploadInfo);
        return copy;
    }

    /**
     * Convert a UploadInfo to a JSON which can be sent to the server.
     */
    private convert(uploadInfo: UploadInfo): UploadInfo {
        const copy: UploadInfo = Object.assign({}, uploadInfo);
        return copy;
    }
}
