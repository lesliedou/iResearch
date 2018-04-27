import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { SurveyQuestion } from './survey-question.model';
import { SurveyQuestionPopupService } from './survey-question-popup.service';
import { SurveyQuestionService } from './survey-question.service';
import { Onsite, OnsiteService } from '../onsite';
import { Department, DepartmentService } from '../department';

@Component({
    selector: 'jhi-survey-question-dialog',
    templateUrl: './survey-question-dialog.component.html'
})
export class SurveyQuestionDialogComponent implements OnInit {

    surveyQuestion: SurveyQuestion;
    isSaving: boolean;

    surveys: SurveyQuestion[];

    onsites: Onsite[];

    departments: Department[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private surveyQuestionService: SurveyQuestionService,
        private onsiteService: OnsiteService,
        private departmentService: DepartmentService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.surveyQuestionService
            .query({filter: 'surveyquestion-is-null'})
            .subscribe((res: HttpResponse<SurveyQuestion[]>) => {
                if (!this.surveyQuestion.surveyId) {
                    this.surveys = res.body;
                } else {
                    this.surveyQuestionService
                        .find(this.surveyQuestion.surveyId)
                        .subscribe((subRes: HttpResponse<SurveyQuestion>) => {
                            this.surveys = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.onsiteService
            .query({filter: 'surveyquestion-is-null'})
            .subscribe((res: HttpResponse<Onsite[]>) => {
                if (!this.surveyQuestion.onsiteId) {
                    this.onsites = res.body;
                } else {
                    this.onsiteService
                        .find(this.surveyQuestion.onsiteId)
                        .subscribe((subRes: HttpResponse<Onsite>) => {
                            this.onsites = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.departmentService
            .query({filter: 'surveyquestion-is-null'})
            .subscribe((res: HttpResponse<Department[]>) => {
                if (!this.surveyQuestion.departmentId) {
                    this.departments = res.body;
                } else {
                    this.departmentService
                        .find(this.surveyQuestion.departmentId)
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
        if (this.surveyQuestion.id !== undefined) {
            this.subscribeToSaveResponse(
                this.surveyQuestionService.update(this.surveyQuestion));
        } else {
            this.subscribeToSaveResponse(
                this.surveyQuestionService.create(this.surveyQuestion));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<SurveyQuestion>>) {
        result.subscribe((res: HttpResponse<SurveyQuestion>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: SurveyQuestion) {
        this.eventManager.broadcast({ name: 'surveyQuestionListModification', content: 'OK'});
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

    trackOnsiteById(index: number, item: Onsite) {
        return item.id;
    }

    trackDepartmentById(index: number, item: Department) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-survey-question-popup',
    template: ''
})
export class SurveyQuestionPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private surveyQuestionPopupService: SurveyQuestionPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.surveyQuestionPopupService
                    .open(SurveyQuestionDialogComponent as Component, params['id']);
            } else {
                this.surveyQuestionPopupService
                    .open(SurveyQuestionDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
