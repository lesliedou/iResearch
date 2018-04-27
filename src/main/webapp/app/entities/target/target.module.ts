import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IResearchSharedModule } from '../../shared';
import {
    TargetService,
    TargetPopupService,
    TargetComponent,
    TargetDetailComponent,
    TargetDialogComponent,
    TargetPopupComponent,
    TargetDeletePopupComponent,
    TargetDeleteDialogComponent,
    targetRoute,
    targetPopupRoute,
    TargetResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...targetRoute,
    ...targetPopupRoute,
];

@NgModule({
    imports: [
        IResearchSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        TargetComponent,
        TargetDetailComponent,
        TargetDialogComponent,
        TargetDeleteDialogComponent,
        TargetPopupComponent,
        TargetDeletePopupComponent,
    ],
    entryComponents: [
        TargetComponent,
        TargetDialogComponent,
        TargetPopupComponent,
        TargetDeleteDialogComponent,
        TargetDeletePopupComponent,
    ],
    providers: [
        TargetService,
        TargetPopupService,
        TargetResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class IResearchTargetModule {}
