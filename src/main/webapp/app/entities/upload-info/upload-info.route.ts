import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { UploadInfoComponent } from './upload-info.component';
import { UploadInfoDetailComponent } from './upload-info-detail.component';
import { UploadInfoPopupComponent } from './upload-info-dialog.component';
import { UploadInfoDeletePopupComponent } from './upload-info-delete-dialog.component';

@Injectable()
export class UploadInfoResolvePagingParams implements Resolve<any> {

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

export const uploadInfoRoute: Routes = [
    {
        path: 'upload-info',
        component: UploadInfoComponent,
        resolve: {
            'pagingParams': UploadInfoResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.uploadInfo.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'upload-info/:id',
        component: UploadInfoDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.uploadInfo.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const uploadInfoPopupRoute: Routes = [
    {
        path: 'upload-info-new',
        component: UploadInfoPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.uploadInfo.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'upload-info/:id/edit',
        component: UploadInfoPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.uploadInfo.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'upload-info/:id/delete',
        component: UploadInfoDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.uploadInfo.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
