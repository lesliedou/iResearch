import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IResearchSharedModule } from '../../shared';
import {
    FilesService,
    FilesPopupService,
    FilesComponent,
    FilesDetailComponent,
    FilesDialogComponent,
    FilesPopupComponent,
    FilesDeletePopupComponent,
    FilesDeleteDialogComponent,
    filesRoute,
    filesPopupRoute,
    FilesResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...filesRoute,
    ...filesPopupRoute,
];

@NgModule({
    imports: [
        IResearchSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        FilesComponent,
        FilesDetailComponent,
        FilesDialogComponent,
        FilesDeleteDialogComponent,
        FilesPopupComponent,
        FilesDeletePopupComponent,
    ],
    entryComponents: [
        FilesComponent,
        FilesDialogComponent,
        FilesPopupComponent,
        FilesDeleteDialogComponent,
        FilesDeletePopupComponent,
    ],
    providers: [
        FilesService,
        FilesPopupService,
        FilesResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class IResearchFilesModule {}
