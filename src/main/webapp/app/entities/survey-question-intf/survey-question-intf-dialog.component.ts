import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { SurveyQuestionIntf } from './survey-question-intf.model';
import { SurveyQuestionIntfPopupService } from './survey-question-intf-popup.service';
import { SurveyQuestionIntfService } from './survey-question-intf.service';
import { SurveyQuestion, SurveyQuestionService } from '../survey-question';

@Component({
    selector: 'jhi-survey-question-intf-dialog',
    templateUrl: './survey-question-intf-dialog.component.html'
})
export class SurveyQuestionIntfDialogComponent implements OnInit {

    surveyQuestionIntf: SurveyQuestionIntf;
    isSaving: boolean;

    surveyquestions: SurveyQuestion[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private surveyQuestionIntfService: SurveyQuestionIntfService,
        private surveyQuestionService: SurveyQuestionService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.surveyQuestionService
            .query({filter: 'surveyquestionintf-is-null'})
            .subscribe((res: HttpResponse<SurveyQuestion[]>) => {
                if (!this.surveyQuestionIntf.surveyQuestionId) {
                    this.surveyquestions = res.body;
                } else {
                    this.surveyQuestionService
                        .find(this.surveyQuestionIntf.surveyQuestionId)
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
        if (this.surveyQuestionIntf.id !== undefined) {
            this.subscribeToSaveResponse(
                this.surveyQuestionIntfService.update(this.surveyQuestionIntf));
        } else {
            this.subscribeToSaveResponse(
                this.surveyQuestionIntfService.create(this.surveyQuestionIntf));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<SurveyQuestionIntf>>) {
        result.subscribe((res: HttpResponse<SurveyQuestionIntf>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: SurveyQuestionIntf) {
        this.eventManager.broadcast({ name: 'surveyQuestionIntfListModification', content: 'OK'});
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
    selector: 'jhi-survey-question-intf-popup',
    template: ''
})
export class SurveyQuestionIntfPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private surveyQuestionIntfPopupService: SurveyQuestionIntfPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.surveyQuestionIntfPopupService
                    .open(SurveyQuestionIntfDialogComponent as Component, params['id']);
            } else {
                this.surveyQuestionIntfPopupService
                    .open(SurveyQuestionIntfDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
