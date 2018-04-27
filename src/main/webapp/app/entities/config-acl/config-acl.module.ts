import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IResearchSharedModule } from '../../shared';
import {
    ConfigAclService,
    ConfigAclPopupService,
    ConfigAclComponent,
    ConfigAclDetailComponent,
    ConfigAclDialogComponent,
    ConfigAclPopupComponent,
    ConfigAclDeletePopupComponent,
    ConfigAclDeleteDialogComponent,
    configAclRoute,
    configAclPopupRoute,
    ConfigAclResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...configAclRoute,
    ...configAclPopupRoute,
];

@NgModule({
    imports: [
        IResearchSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ConfigAclComponent,
        ConfigAclDetailComponent,
        ConfigAclDialogComponent,
        ConfigAclDeleteDialogComponent,
        ConfigAclPopupComponent,
        ConfigAclDeletePopupComponent,
    ],
    entryComponents: [
        ConfigAclComponent,
        ConfigAclDialogComponent,
        ConfigAclPopupComponent,
        ConfigAclDeleteDialogComponent,
        ConfigAclDeletePopupComponent,
    ],
    providers: [
        ConfigAclService,
        ConfigAclPopupService,
        ConfigAclResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class IResearchConfigAclModule {}
