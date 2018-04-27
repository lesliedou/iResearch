import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { SurveyQuestionIntfComponent } from './survey-question-intf.component';
import { SurveyQuestionIntfDetailComponent } from './survey-question-intf-detail.component';
import { SurveyQuestionIntfPopupComponent } from './survey-question-intf-dialog.component';
import { SurveyQuestionIntfDeletePopupComponent } from './survey-question-intf-delete-dialog.component';

@Injectable()
export class SurveyQuestionIntfResolvePagingParams implements Resolve<any> {

    constructor(private paginationUtil: JhiPaginationUtil) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const page = route.queryParams['page'] ? route.queryParams['page'] : '1';
        const sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,asc';
        return {
            page: this.paginationUtil.parsePage(page),
            predicate: this.paginationUtil.parsePredicate(sort),
            ascending: this.paginationUtil.parseAscending(sort)
      };
    }
}

export const surveyQuestionIntfRoute: Routes = [
    {
        path: 'survey-question-intf',
        component: SurveyQuestionIntfComponent,
        resolve: {
            'pagingParams': SurveyQuestionIntfResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.surveyQuestionIntf.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'survey-question-intf/:id',
        component: SurveyQuestionIntfDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.surveyQuestionIntf.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const surveyQuestionIntfPopupRoute: Routes = [
    {
        path: 'survey-question-intf-new',
        component: SurveyQuestionIntfPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.surveyQuestionIntf.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'survey-question-intf/:id/edit',
        component: SurveyQuestionIntfPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.surveyQuestionIntf.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'survey-question-intf/:id/delete',
        component: SurveyQuestionIntfDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.surveyQuestionIntf.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
