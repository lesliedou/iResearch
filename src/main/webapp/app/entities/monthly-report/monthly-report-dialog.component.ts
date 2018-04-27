import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { MonthlyReport } from './monthly-report.model';
import { MonthlyReportPopupService } from './monthly-report-popup.service';
import { MonthlyReportService } from './monthly-report.service';
import { Department, DepartmentService } from '../department';

@Component({
    selector: 'jhi-monthly-report-dialog',
    templateUrl: './monthly-report-dialog.component.html'
})
export class MonthlyReportDialogComponent implements OnInit {

    monthlyReport: MonthlyReport;
    isSaving: boolean;

    departments: Department[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private monthlyReportService: MonthlyReportService,
        private departmentService: DepartmentService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.departmentService
            .query({filter: 'monthlyreport-is-null'})
            .subscribe((res: HttpResponse<Department[]>) => {
                if (!this.monthlyReport.departmentId) {
                    this.departments = res.body;
                } else {
                    this.departmentService
                        .find(this.monthlyReport.departmentId)
                        .subscribe((subRes: HttpResponse<Department>) => {
                            this.departments = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.monthlyReport.id !== undefined) {
            this.subscribeToSaveResponse(
                this.monthlyReportService.update(this.monthlyReport));
        } else {
            this.subscribeToSaveResponse(
                this.monthlyReportService.create(this.monthlyReport));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<MonthlyReport>>) {
        result.subscribe((res: HttpResponse<MonthlyReport>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: MonthlyReport) {
        this.eventManager.broadcast({ name: 'monthlyReportListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackDepartmentById(index: number, item: Department) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-monthly-report-popup',
    template: ''
})
export class MonthlyReportPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private monthlyReportPopupService: MonthlyReportPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.monthlyReportPopupService
                    .open(MonthlyReportDialogComponent as Component, params['id']);
            } else {
                this.monthlyReportPopupService
                    .open(MonthlyReportDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
