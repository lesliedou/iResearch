import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { SurveyQuestionFlow } from './survey-question-flow.model';
import { SurveyQuestionFlowPopupService } from './survey-question-flow-popup.service';
import { SurveyQuestionFlowService } from './survey-question-flow.service';

@Component({
    selector: 'jhi-survey-question-flow-delete-dialog',
    templateUrl: './survey-question-flow-delete-dialog.component.html'
})
export class SurveyQuestionFlowDeleteDialogComponent {

    surveyQuestionFlow: SurveyQuestionFlow;

    constructor(
        private surveyQuestionFlowService: SurveyQuestionFlowService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.surveyQuestionFlowService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'surveyQuestionFlowListModification',
                content: 'Deleted an surveyQuestionFlow'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-survey-question-flow-delete-popup',
    template: ''
})
export class SurveyQuestionFlowDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private surveyQuestionFlowPopupService: SurveyQuestionFlowPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.surveyQuestionFlowPopupService
                .open(SurveyQuestionFlowDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
