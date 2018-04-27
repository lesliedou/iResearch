import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IResearchSharedModule } from '../../shared';
import {
    AclService,
    AclPopupService,
    AclComponent,
    AclDetailComponent,
    AclDialogComponent,
    AclPopupComponent,
    AclDeletePopupComponent,
    AclDeleteDialogComponent,
    aclRoute,
    aclPopupRoute,
    AclResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...aclRoute,
    ...aclPopupRoute,
];

@NgModule({
    imports: [
        IResearchSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        AclComponent,
        AclDetailComponent,
        AclDialogComponent,
        AclDeleteDialogComponent,
        AclPopupComponent,
        AclDeletePopupComponent,
    ],
    entryComponents: [
        AclComponent,
        AclDialogComponent,
        AclPopupComponent,
        AclDeleteDialogComponent,
        AclDeletePopupComponent,
    ],
    providers: [
        AclService,
        AclPopupService,
        AclResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class IResearchAclModule {}
