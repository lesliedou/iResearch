import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { ConfigAclComponent } from './config-acl.component';
import { ConfigAclDetailComponent } from './config-acl-detail.component';
import { ConfigAclPopupComponent } from './config-acl-dialog.component';
import { ConfigAclDeletePopupComponent } from './config-acl-delete-dialog.component';

@Injectable()
export class ConfigAclResolvePagingParams implements Resolve<any> {

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

export const configAclRoute: Routes = [
    {
        path: 'config-acl',
        component: ConfigAclComponent,
        resolve: {
            'pagingParams': ConfigAclResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.configAcl.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'config-acl/:id',
        component: ConfigAclDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.configAcl.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const configAclPopupRoute: Routes = [
    {
        path: 'config-acl-new',
        component: ConfigAclPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.configAcl.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'config-acl/:id/edit',
        component: ConfigAclPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.configAcl.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'config-acl/:id/delete',
        component: ConfigAclDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.configAcl.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
