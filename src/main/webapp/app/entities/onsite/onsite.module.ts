import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IResearchSharedModule } from '../../shared';
import {
    OnsiteService,
    OnsitePopupService,
    OnsiteComponent,
    OnsiteDetailComponent,
    OnsiteDialogComponent,
    OnsitePopupComponent,
    OnsiteDeletePopupComponent,
    OnsiteDeleteDialogComponent,
    onsiteRoute,
    onsitePopupRoute,
    OnsiteResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...onsiteRoute,
    ...onsitePopupRoute,
];

@NgModule({
    imports: [
        IResearchSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        OnsiteComponent,
        OnsiteDetailComponent,
        OnsiteDialogComponent,
        OnsiteDeleteDialogComponent,
        OnsitePopupComponent,
        OnsiteDeletePopupComponent,
    ],
    entryComponents: [
        OnsiteComponent,
        OnsiteDialogComponent,
        OnsitePopupComponent,
        OnsiteDeleteDialogComponent,
        OnsiteDeletePopupComponent,
    ],
    providers: [
        OnsiteService,
        OnsitePopupService,
        OnsiteResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class IResearchOnsiteModule {}
