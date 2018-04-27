import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { SurveyQuestionComponent } from './survey-question.component';
import { SurveyQuestionDetailComponent } from './survey-question-detail.component';
import { SurveyQuestionPopupComponent } from './survey-question-dialog.component';
import { SurveyQuestionDeletePopupComponent } from './survey-question-delete-dialog.component';

@Injectable()
export class SurveyQuestionResolvePagingParams implements Resolve<any> {

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

export const surveyQuestionRoute: Routes = [
    {
        path: 'survey-question',
        component: SurveyQuestionComponent,
        resolve: {
            'pagingParams': SurveyQuestionResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.surveyQuestion.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'survey-question/:id',
        component: SurveyQuestionDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.surveyQuestion.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const surveyQuestionPopupRoute: Routes = [
    {
        path: 'survey-question-new',
        component: SurveyQuestionPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.surveyQuestion.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'survey-question/:id/edit',
        component: SurveyQuestionPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.surveyQuestion.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'survey-question/:id/delete',
        component: SurveyQuestionDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.surveyQuestion.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
