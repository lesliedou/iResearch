import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { SurveyQuestionFlowComponent } from './survey-question-flow.component';
import { SurveyQuestionFlowDetailComponent } from './survey-question-flow-detail.component';
import { SurveyQuestionFlowPopupComponent } from './survey-question-flow-dialog.component';
import { SurveyQuestionFlowDeletePopupComponent } from './survey-question-flow-delete-dialog.component';

@Injectable()
export class SurveyQuestionFlowResolvePagingParams implements Resolve<any> {

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

export const surveyQuestionFlowRoute: Routes = [
    {
        path: 'survey-question-flow',
        component: SurveyQuestionFlowComponent,
        resolve: {
            'pagingParams': SurveyQuestionFlowResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.surveyQuestionFlow.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'survey-question-flow/:id',
        component: SurveyQuestionFlowDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.surveyQuestionFlow.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const surveyQuestionFlowPopupRoute: Routes = [
    {
        path: 'survey-question-flow-new',
        component: SurveyQuestionFlowPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.surveyQuestionFlow.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'survey-question-flow/:id/edit',
        component: SurveyQuestionFlowPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.surveyQuestionFlow.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'survey-question-flow/:id/delete',
        component: SurveyQuestionFlowDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.surveyQuestionFlow.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
