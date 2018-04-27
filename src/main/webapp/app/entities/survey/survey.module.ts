import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IResearchSharedModule } from '../../shared';
import {
    SurveyService,
    SurveyPopupService,
    SurveyComponent,
    SurveyDetailComponent,
    SurveyDialogComponent,
    SurveyPopupComponent,
    SurveyDeletePopupComponent,
    SurveyDeleteDialogComponent,
    surveyRoute,
    surveyPopupRoute,
    SurveyResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...surveyRoute,
    ...surveyPopupRoute,
];

@NgModule({
    imports: [
        IResearchSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        SurveyComponent,
        SurveyDetailComponent,
        SurveyDialogComponent,
        SurveyDeleteDialogComponent,
        SurveyPopupComponent,
        SurveyDeletePopupComponent,
    ],
    entryComponents: [
        SurveyComponent,
        SurveyDialogComponent,
        SurveyPopupComponent,
        SurveyDeleteDialogComponent,
        SurveyDeletePopupComponent,
    ],
    providers: [
        SurveyService,
        SurveyPopupService,
        SurveyResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class IResearchSurveyModule {}
