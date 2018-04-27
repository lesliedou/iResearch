import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IResearchSharedModule } from '../../shared';
import {
    SurveyQuestionService,
    SurveyQuestionPopupService,
    SurveyQuestionComponent,
    SurveyQuestionDetailComponent,
    SurveyQuestionDialogComponent,
    SurveyQuestionPopupComponent,
    SurveyQuestionDeletePopupComponent,
    SurveyQuestionDeleteDialogComponent,
    surveyQuestionRoute,
    surveyQuestionPopupRoute,
    SurveyQuestionResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...surveyQuestionRoute,
    ...surveyQuestionPopupRoute,
];

@NgModule({
    imports: [
        IResearchSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        SurveyQuestionComponent,
        SurveyQuestionDetailComponent,
        SurveyQuestionDialogComponent,
        SurveyQuestionDeleteDialogComponent,
        SurveyQuestionPopupComponent,
        SurveyQuestionDeletePopupComponent,
    ],
    entryComponents: [
        SurveyQuestionComponent,
        SurveyQuestionDialogComponent,
        SurveyQuestionPopupComponent,
        SurveyQuestionDeleteDialogComponent,
        SurveyQuestionDeletePopupComponent,
    ],
    providers: [
        SurveyQuestionService,
        SurveyQuestionPopupService,
        SurveyQuestionResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class IResearchSurveyQuestionModule {}
