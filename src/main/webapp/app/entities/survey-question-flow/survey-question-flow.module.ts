import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IResearchSharedModule } from '../../shared';
import {
    SurveyQuestionFlowService,
    SurveyQuestionFlowPopupService,
    SurveyQuestionFlowComponent,
    SurveyQuestionFlowDetailComponent,
    SurveyQuestionFlowDialogComponent,
    SurveyQuestionFlowPopupComponent,
    SurveyQuestionFlowDeletePopupComponent,
    SurveyQuestionFlowDeleteDialogComponent,
    surveyQuestionFlowRoute,
    surveyQuestionFlowPopupRoute,
    SurveyQuestionFlowResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...surveyQuestionFlowRoute,
    ...surveyQuestionFlowPopupRoute,
];

@NgModule({
    imports: [
        IResearchSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        SurveyQuestionFlowComponent,
        SurveyQuestionFlowDetailComponent,
        SurveyQuestionFlowDialogComponent,
        SurveyQuestionFlowDeleteDialogComponent,
        SurveyQuestionFlowPopupComponent,
        SurveyQuestionFlowDeletePopupComponent,
    ],
    entryComponents: [
        SurveyQuestionFlowComponent,
        SurveyQuestionFlowDialogComponent,
        SurveyQuestionFlowPopupComponent,
        SurveyQuestionFlowDeleteDialogComponent,
        SurveyQuestionFlowDeletePopupComponent,
    ],
    providers: [
        SurveyQuestionFlowService,
        SurveyQuestionFlowPopupService,
        SurveyQuestionFlowResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class IResearchSurveyQuestionFlowModule {}
