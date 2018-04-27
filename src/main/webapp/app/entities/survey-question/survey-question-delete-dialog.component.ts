import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { SurveyQuestion } from './survey-question.model';
import { SurveyQuestionPopupService } from './survey-question-popup.service';
import { SurveyQuestionService } from './survey-question.service';

@Component({
    selector: 'jhi-survey-question-delete-dialog',
    templateUrl: './survey-question-delete-dialog.component.html'
})
export class SurveyQuestionDeleteDialogComponent {

    surveyQuestion: SurveyQuestion;

    constructor(
        private surveyQuestionService: SurveyQuestionService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.surveyQuestionService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'surveyQuestionListModification',
                content: 'Deleted an surveyQuestion'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-survey-question-delete-popup',
    template: ''
})
export class SurveyQuestionDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private surveyQuestionPopupService: SurveyQuestionPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.surveyQuestionPopupService
                .open(SurveyQuestionDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
