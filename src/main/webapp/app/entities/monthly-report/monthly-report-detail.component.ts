import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { MonthlyReport } from './monthly-report.model';
import { MonthlyReportService } from './monthly-report.service';

@Component({
    selector: 'jhi-monthly-report-detail',
    templateUrl: './monthly-report-detail.component.html'
})
export class MonthlyReportDetailComponent implements OnInit, OnDestroy {

    monthlyReport: MonthlyReport;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private monthlyReportService: MonthlyReportService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInMonthlyReports();
    }

    load(id) {
        this.monthlyReportService.find(id)
            .subscribe((monthlyReportResponse: HttpResponse<MonthlyReport>) => {
                this.monthlyReport = monthlyReportResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInMonthlyReports() {
        this.eventSubscriber = this.eventManager.subscribe(
            'monthlyReportListModification',
            (response) => this.load(this.monthlyReport.id)
        );
    }
}
