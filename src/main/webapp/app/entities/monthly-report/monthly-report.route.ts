import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { MonthlyReportComponent } from './monthly-report.component';
import { MonthlyReportDetailComponent } from './monthly-report-detail.component';
import { MonthlyReportPopupComponent } from './monthly-report-dialog.component';
import { MonthlyReportDeletePopupComponent } from './monthly-report-delete-dialog.component';

@Injectable()
export class MonthlyReportResolvePagingParams implements Resolve<any> {

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

export const monthlyReportRoute: Routes = [
    {
        path: 'monthly-report',
        component: MonthlyReportComponent,
        resolve: {
            'pagingParams': MonthlyReportResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.monthlyReport.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'monthly-report/:id',
        component: MonthlyReportDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.monthlyReport.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const monthlyReportPopupRoute: Routes = [
    {
        path: 'monthly-report-new',
        component: MonthlyReportPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.monthlyReport.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'monthly-report/:id/edit',
        component: MonthlyReportPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.monthlyReport.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'monthly-report/:id/delete',
        component: MonthlyReportDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.monthlyReport.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
