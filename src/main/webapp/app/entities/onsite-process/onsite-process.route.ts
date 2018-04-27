import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { OnsiteProcessComponent } from './onsite-process.component';
import { OnsiteProcessDetailComponent } from './onsite-process-detail.component';
import { OnsiteProcessPopupComponent } from './onsite-process-dialog.component';
import { OnsiteProcessDeletePopupComponent } from './onsite-process-delete-dialog.component';

@Injectable()
export class OnsiteProcessResolvePagingParams implements Resolve<any> {

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

export const onsiteProcessRoute: Routes = [
    {
        path: 'onsite-process',
        component: OnsiteProcessComponent,
        resolve: {
            'pagingParams': OnsiteProcessResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.onsiteProcess.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'onsite-process/:id',
        component: OnsiteProcessDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.onsiteProcess.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const onsiteProcessPopupRoute: Routes = [
    {
        path: 'onsite-process-new',
        component: OnsiteProcessPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.onsiteProcess.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'onsite-process/:id/edit',
        component: OnsiteProcessPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.onsiteProcess.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'onsite-process/:id/delete',
        component: OnsiteProcessDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.onsiteProcess.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
