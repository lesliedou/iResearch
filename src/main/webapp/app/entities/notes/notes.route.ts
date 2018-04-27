import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { NotesComponent } from './notes.component';
import { NotesDetailComponent } from './notes-detail.component';
import { NotesPopupComponent } from './notes-dialog.component';
import { NotesDeletePopupComponent } from './notes-delete-dialog.component';

@Injectable()
export class NotesResolvePagingParams implements Resolve<any> {

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

export const notesRoute: Routes = [
    {
        path: 'notes',
        component: NotesComponent,
        resolve: {
            'pagingParams': NotesResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.notes.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'notes/:id',
        component: NotesDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.notes.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const notesPopupRoute: Routes = [
    {
        path: 'notes-new',
        component: NotesPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.notes.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'notes/:id/edit',
        component: NotesPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.notes.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'notes/:id/delete',
        component: NotesDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'iResearchApp.notes.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
