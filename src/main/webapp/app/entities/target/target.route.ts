import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { TargetComponent } from './target.component';
import { TargetDetailComponent } from './target-detail.component';
import { TargetPopupComponent } from './target-dialog.component';
import { TargetDeletePopupComponent } from './target-delete-dialog.component';

@Injectable()
export class TargetResolvePagingParams implements Resolve<any> {

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

export const targetRoute: Routes = [
    {
        path: 'target',
        component: TargetComponent,
        resolve: {
            'pagingParams': TargetResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.target.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'target/:id',
        component: TargetDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.target.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const targetPopupRoute: Routes = [
    {
        path: 'target-new',
        component: TargetPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.target.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'target/:id/edit',
        component: TargetPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.target.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'target/:id/delete',
        component: TargetDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.target.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
