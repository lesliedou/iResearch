import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { AclComponent } from './acl.component';
import { AclDetailComponent } from './acl-detail.component';
import { AclPopupComponent } from './acl-dialog.component';
import { AclDeletePopupComponent } from './acl-delete-dialog.component';

@Injectable()
export class AclResolvePagingParams implements Resolve<any> {

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

export const aclRoute: Routes = [
    {
        path: 'acl',
        component: AclComponent,
        resolve: {
            'pagingParams': AclResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.acl.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'acl/:id',
        component: AclDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.acl.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const aclPopupRoute: Routes = [
    {
        path: 'acl-new',
        component: AclPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.acl.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'acl/:id/edit',
        component: AclPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.acl.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'acl/:id/delete',
        component: AclDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.acl.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
