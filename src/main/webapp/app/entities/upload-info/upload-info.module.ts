import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IResearchSharedModule } from '../../shared';
import {
    UploadInfoService,
    UploadInfoPopupService,
    UploadInfoComponent,
    UploadInfoDetailComponent,
    UploadInfoDialogComponent,
    UploadInfoPopupComponent,
    UploadInfoDeletePopupComponent,
    UploadInfoDeleteDialogComponent,
    uploadInfoRoute,
    uploadInfoPopupRoute,
    UploadInfoResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...uploadInfoRoute,
    ...uploadInfoPopupRoute,
];

@NgModule({
    imports: [
        IResearchSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        UploadInfoComponent,
        UploadInfoDetailComponent,
        UploadInfoDialogComponent,
        UploadInfoDeleteDialogComponent,
        UploadInfoPopupComponent,
        UploadInfoDeletePopupComponent,
    ],
    entryComponents: [
        UploadInfoComponent,
        UploadInfoDialogComponent,
        UploadInfoPopupComponent,
        UploadInfoDeleteDialogComponent,
        UploadInfoDeletePopupComponent,
    ],
    providers: [
        UploadInfoService,
        UploadInfoPopupService,
        UploadInfoResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class IResearchUploadInfoModule {}
