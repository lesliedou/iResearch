import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { SurveyComponent } from './survey.component';
import { SurveyDetailComponent } from './survey-detail.component';
import { SurveyPopupComponent } from './survey-dialog.component';
import { SurveyDeletePopupComponent } from './survey-delete-dialog.component';

@Injectable()
export class SurveyResolvePagingParams implements Resolve<any> {

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

export const surveyRoute: Routes = [
    {
        path: 'survey',
        component: SurveyComponent,
        resolve: {
            'pagingParams': SurveyResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.survey.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'survey/:id',
        component: SurveyDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.survey.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const surveyPopupRoute: Routes = [
    {
        path: 'survey-new',
        component: SurveyPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.survey.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'survey/:id/edit',
        component: SurveyPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.survey.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'survey/:id/delete',
        component: SurveyDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.survey.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
