import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { FilesComponent } from './files.component';
import { FilesDetailComponent } from './files-detail.component';
import { FilesPopupComponent } from './files-dialog.component';
import { FilesDeletePopupComponent } from './files-delete-dialog.component';

@Injectable()
export class FilesResolvePagingParams implements Resolve<any> {

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

export const filesRoute: Routes = [
    {
        path: 'files',
        component: FilesComponent,
        resolve: {
            'pagingParams': FilesResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.files.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'files/:id',
        component: FilesDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.files.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const filesPopupRoute: Routes = [
    {
        path: 'files-new',
        component: FilesPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.files.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'files/:id/edit',
        component: FilesPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.files.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'files/:id/delete',
        component: FilesDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.files.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
