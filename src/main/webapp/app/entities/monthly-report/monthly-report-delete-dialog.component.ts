import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { MonthlyReport } from './monthly-report.model';
import { MonthlyReportPopupService } from './monthly-report-popup.service';
import { MonthlyReportService } from './monthly-report.service';

@Component({
    selector: 'jhi-monthly-report-delete-dialog',
    templateUrl: './monthly-report-delete-dialog.component.html'
})
export class MonthlyReportDeleteDialogComponent {

    monthlyReport: MonthlyReport;

    constructor(
        private monthlyReportService: MonthlyReportService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.monthlyReportService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'monthlyReportListModification',
                content: 'Deleted an monthlyReport'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-monthly-report-delete-popup',
    template: ''
})
export class MonthlyReportDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private monthlyReportPopupService: MonthlyReportPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.monthlyReportPopupService
                .open(MonthlyReportDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
