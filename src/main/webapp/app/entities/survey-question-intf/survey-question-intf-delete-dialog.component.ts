import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { SurveyQuestionIntf } from './survey-question-intf.model';
import { SurveyQuestionIntfPopupService } from './survey-question-intf-popup.service';
import { SurveyQuestionIntfService } from './survey-question-intf.service';

@Component({
    selector: 'jhi-survey-question-intf-delete-dialog',
    templateUrl: './survey-question-intf-delete-dialog.component.html'
})
export class SurveyQuestionIntfDeleteDialogComponent {

    surveyQuestionIntf: SurveyQuestionIntf;

    constructor(
        private surveyQuestionIntfService: SurveyQuestionIntfService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.surveyQuestionIntfService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'surveyQuestionIntfListModification',
                content: 'Deleted an surveyQuestionIntf'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-survey-question-intf-delete-popup',
    template: ''
})
export class SurveyQuestionIntfDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private surveyQuestionIntfPopupService: SurveyQuestionIntfPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.surveyQuestionIntfPopupService
                .open(SurveyQuestionIntfDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
