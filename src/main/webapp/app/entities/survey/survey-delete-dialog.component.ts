import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Survey } from './survey.model';
import { SurveyPopupService } from './survey-popup.service';
import { SurveyService } from './survey.service';

@Component({
    selector: 'jhi-survey-delete-dialog',
    templateUrl: './survey-delete-dialog.component.html'
})
export class SurveyDeleteDialogComponent {

    survey: Survey;

    constructor(
        private surveyService: SurveyService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.surveyService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'surveyListModification',
                content: 'Deleted an survey'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-survey-delete-popup',
    template: ''
})
export class SurveyDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private surveyPopupService: SurveyPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.surveyPopupService
                .open(SurveyDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
