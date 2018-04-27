import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { MonthlyReport } from './monthly-report.model';
import { MonthlyReportService } from './monthly-report.service';

@Injectable()
export class MonthlyReportPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private monthlyReportService: MonthlyReportService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.monthlyReportService.find(id)
                    .subscribe((monthlyReportResponse: HttpResponse<MonthlyReport>) => {
                        const monthlyReport: MonthlyReport = monthlyReportResponse.body;
                        this.ngbModalRef = this.monthlyReportModalRef(component, monthlyReport);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.monthlyReportModalRef(component, new MonthlyReport());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    monthlyReportModalRef(component: Component, monthlyReport: MonthlyReport): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.monthlyReport = monthlyReport;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
