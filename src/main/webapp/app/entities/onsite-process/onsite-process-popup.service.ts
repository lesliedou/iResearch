import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { OnsiteProcess } from './onsite-process.model';
import { OnsiteProcessService } from './onsite-process.service';

@Injectable()
export class OnsiteProcessPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private onsiteProcessService: OnsiteProcessService

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
                this.onsiteProcessService.find(id)
                    .subscribe((onsiteProcessResponse: HttpResponse<OnsiteProcess>) => {
                        const onsiteProcess: OnsiteProcess = onsiteProcessResponse.body;
                        this.ngbModalRef = this.onsiteProcessModalRef(component, onsiteProcess);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.onsiteProcessModalRef(component, new OnsiteProcess());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    onsiteProcessModalRef(component: Component, onsiteProcess: OnsiteProcess): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.onsiteProcess = onsiteProcess;
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
