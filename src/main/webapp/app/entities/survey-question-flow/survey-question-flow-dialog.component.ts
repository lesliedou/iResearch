import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { SurveyQuestionFlow } from './survey-question-flow.model';
import { SurveyQuestionFlowPopupService } from './survey-question-flow-popup.service';
import { SurveyQuestionFlowService } from './survey-question-flow.service';
import { SurveyQuestion, SurveyQuestionService } from '../survey-question';

@Component({
    selector: 'jhi-survey-question-flow-dialog',
    templateUrl: './survey-question-flow-dialog.component.html'
})
export class SurveyQuestionFlowDialogComponent implements OnInit {

    surveyQuestionFlow: SurveyQuestionFlow;
    isSaving: boolean;

    surveyquestions: SurveyQuestion[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private surveyQuestionFlowService: SurveyQuestionFlowService,
        private surveyQuestionService: SurveyQuestionService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.surveyQuestionService
            .query({filter: 'surveyquestionflow-is-null'})
            .subscribe((res: HttpResponse<SurveyQuestion[]>) => {
                if (!this.surveyQuestionFlow.surveyQuestionId) {
                    this.surveyquestions = res.body;
                } else {
                    this.surveyQuestionService
                        .find(this.surveyQuestionFlow.surveyQuestionId)
                        .subscribe((subRes: HttpResponse<SurveyQuestion>) => {
                            this.surveyquestions = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.surveyQuestionFlow.id !== undefined) {
            this.subscribeToSaveResponse(
                this.surveyQuestionFlowService.update(this.surveyQuestionFlow));
        } else {
            this.subscribeToSaveResponse(
                this.surveyQuestionFlowService.create(this.surveyQuestionFlow));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<SurveyQuestionFlow>>) {
        result.subscribe((res: HttpResponse<SurveyQuestionFlow>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: SurveyQuestionFlow) {
        this.eventManager.broadcast({ name: 'surveyQuestionFlowListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackSurveyQuestionById(index: number, item: SurveyQuestion) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-survey-question-flow-popup',
    template: ''
})
export class SurveyQuestionFlowPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private surveyQuestionFlowPopupService: SurveyQuestionFlowPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.surveyQuestionFlowPopupService
                    .open(SurveyQuestionFlowDialogComponent as Component, params['id']);
            } else {
                this.surveyQuestionFlowPopupService
                    .open(SurveyQuestionFlowDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
