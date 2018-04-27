import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { OnsiteComponent } from './onsite.component';
import { OnsiteDetailComponent } from './onsite-detail.component';
import { OnsitePopupComponent } from './onsite-dialog.component';
import { OnsiteDeletePopupComponent } from './onsite-delete-dialog.component';

@Injectable()
export class OnsiteResolvePagingParams implements Resolve<any> {

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

export const onsiteRoute: Routes = [
    {
        path: 'onsite',
        component: OnsiteComponent,
        resolve: {
            'pagingParams': OnsiteResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.onsite.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'onsite/:id',
        component: OnsiteDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.onsite.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const onsitePopupRoute: Routes = [
    {
        path: 'onsite-new',
        component: OnsitePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.onsite.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'onsite/:id/edit',
        component: OnsitePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.onsite.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'onsite/:id/delete',
        component: OnsiteDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.onsite.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
