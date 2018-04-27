import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Survey } from './survey.model';
import { SurveyPopupService } from './survey-popup.service';
import { SurveyService } from './survey.service';
import { Department, DepartmentService } from '../department';

@Component({
    selector: 'jhi-survey-dialog',
    templateUrl: './survey-dialog.component.html'
})
export class SurveyDialogComponent implements OnInit {

    survey: Survey;
    isSaving: boolean;

    departments: Department[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private surveyService: SurveyService,
        private departmentService: DepartmentService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.departmentService
            .query({filter: 'survey-is-null'})
            .subscribe((res: HttpResponse<Department[]>) => {
                if (!this.survey.departmentId) {
                    this.departments = res.body;
                } else {
                    this.departmentService
                        .find(this.survey.departmentId)
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
        if (this.survey.id !== undefined) {
            this.subscribeToSaveResponse(
                this.surveyService.update(this.survey));
        } else {
            this.subscribeToSaveResponse(
                this.surveyService.create(this.survey));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Survey>>) {
        result.subscribe((res: HttpResponse<Survey>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Survey) {
        this.eventManager.broadcast({ name: 'surveyListModification', content: 'OK'});
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
    selector: 'jhi-survey-popup',
    template: ''
})
export class SurveyPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private surveyPopupService: SurveyPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.surveyPopupService
                    .open(SurveyDialogComponent as Component, params['id']);
            } else {
                this.surveyPopupService
                    .open(SurveyDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
