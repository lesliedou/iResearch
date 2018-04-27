import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { SurveyQuestionFlow } from './survey-question-flow.model';
import { SurveyQuestionFlowService } from './survey-question-flow.service';

@Injectable()
export class SurveyQuestionFlowPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private surveyQuestionFlowService: SurveyQuestionFlowService

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
                this.surveyQuestionFlowService.find(id)
                    .subscribe((surveyQuestionFlowResponse: HttpResponse<SurveyQuestionFlow>) => {
                        const surveyQuestionFlow: SurveyQuestionFlow = surveyQuestionFlowResponse.body;
                        this.ngbModalRef = this.surveyQuestionFlowModalRef(component, surveyQuestionFlow);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.surveyQuestionFlowModalRef(component, new SurveyQuestionFlow());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    surveyQuestionFlowModalRef(component: Component, surveyQuestionFlow: SurveyQuestionFlow): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.surveyQuestionFlow = surveyQuestionFlow;
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
