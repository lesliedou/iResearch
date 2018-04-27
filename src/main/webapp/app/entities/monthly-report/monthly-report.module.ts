import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IResearchSharedModule } from '../../shared';
import {
    MonthlyReportService,
    MonthlyReportPopupService,
    MonthlyReportComponent,
    MonthlyReportDetailComponent,
    MonthlyReportDialogComponent,
    MonthlyReportPopupComponent,
    MonthlyReportDeletePopupComponent,
    MonthlyReportDeleteDialogComponent,
    monthlyReportRoute,
    monthlyReportPopupRoute,
    MonthlyReportResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...monthlyReportRoute,
    ...monthlyReportPopupRoute,
];

@NgModule({
    imports: [
        IResearchSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        MonthlyReportComponent,
        MonthlyReportDetailComponent,
        MonthlyReportDialogComponent,
        MonthlyReportDeleteDialogComponent,
        MonthlyReportPopupComponent,
        MonthlyReportDeletePopupComponent,
    ],
    entryComponents: [
        MonthlyReportComponent,
        MonthlyReportDialogComponent,
        MonthlyReportPopupComponent,
        MonthlyReportDeleteDialogComponent,
        MonthlyReportDeletePopupComponent,
    ],
    providers: [
        MonthlyReportService,
        MonthlyReportPopupService,
        MonthlyReportResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class IResearchMonthlyReportModule {}
