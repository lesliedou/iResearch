import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IResearchSharedModule } from '../../shared';
import {
    OnsiteProcessService,
    OnsiteProcessPopupService,
    OnsiteProcessComponent,
    OnsiteProcessDetailComponent,
    OnsiteProcessDialogComponent,
    OnsiteProcessPopupComponent,
    OnsiteProcessDeletePopupComponent,
    OnsiteProcessDeleteDialogComponent,
    onsiteProcessRoute,
    onsiteProcessPopupRoute,
    OnsiteProcessResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...onsiteProcessRoute,
    ...onsiteProcessPopupRoute,
];

@NgModule({
    imports: [
        IResearchSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        OnsiteProcessComponent,
        OnsiteProcessDetailComponent,
        OnsiteProcessDialogComponent,
        OnsiteProcessDeleteDialogComponent,
        OnsiteProcessPopupComponent,
        OnsiteProcessDeletePopupComponent,
    ],
    entryComponents: [
        OnsiteProcessComponent,
        OnsiteProcessDialogComponent,
        OnsiteProcessPopupComponent,
        OnsiteProcessDeleteDialogComponent,
        OnsiteProcessDeletePopupComponent,
    ],
    providers: [
        OnsiteProcessService,
        OnsiteProcessPopupService,
        OnsiteProcessResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class IResearchOnsiteProcessModule {}
