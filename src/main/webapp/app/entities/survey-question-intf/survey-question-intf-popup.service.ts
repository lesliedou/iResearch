import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { SurveyQuestionIntf } from './survey-question-intf.model';
import { SurveyQuestionIntfService } from './survey-question-intf.service';

@Injectable()
export class SurveyQuestionIntfPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private surveyQuestionIntfService: SurveyQuestionIntfService

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
                this.surveyQuestionIntfService.find(id)
                    .subscribe((surveyQuestionIntfResponse: HttpResponse<SurveyQuestionIntf>) => {
                        const surveyQuestionIntf: SurveyQuestionIntf = surveyQuestionIntfResponse.body;
                        this.ngbModalRef = this.surveyQuestionIntfModalRef(component, surveyQuestionIntf);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.surveyQuestionIntfModalRef(component, new SurveyQuestionIntf());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    surveyQuestionIntfModalRef(component: Component, surveyQuestionIntf: SurveyQuestionIntf): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.surveyQuestionIntf = surveyQuestionIntf;
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
