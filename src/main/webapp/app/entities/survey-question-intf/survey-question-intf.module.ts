import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IResearchSharedModule } from '../../shared';
import {
    SurveyQuestionIntfService,
    SurveyQuestionIntfPopupService,
    SurveyQuestionIntfComponent,
    SurveyQuestionIntfDetailComponent,
    SurveyQuestionIntfDialogComponent,
    SurveyQuestionIntfPopupComponent,
    SurveyQuestionIntfDeletePopupComponent,
    SurveyQuestionIntfDeleteDialogComponent,
    surveyQuestionIntfRoute,
    surveyQuestionIntfPopupRoute,
    SurveyQuestionIntfResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...surveyQuestionIntfRoute,
    ...surveyQuestionIntfPopupRoute,
];

@NgModule({
    imports: [
        IResearchSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        SurveyQuestionIntfComponent,
        SurveyQuestionIntfDetailComponent,
        SurveyQuestionIntfDialogComponent,
        SurveyQuestionIntfDeleteDialogComponent,
        SurveyQuestionIntfPopupComponent,
        SurveyQuestionIntfDeletePopupComponent,
    ],
    entryComponents: [
        SurveyQuestionIntfComponent,
        SurveyQuestionIntfDialogComponent,
        SurveyQuestionIntfPopupComponent,
        SurveyQuestionIntfDeleteDialogComponent,
        SurveyQuestionIntfDeletePopupComponent,
    ],
    providers: [
        SurveyQuestionIntfService,
        SurveyQuestionIntfPopupService,
        SurveyQuestionIntfResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class IResearchSurveyQuestionIntfModule {}
