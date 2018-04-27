import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { SurveyQuestionIntf } from './survey-question-intf.model';
import { SurveyQuestionIntfService } from './survey-question-intf.service';

@Component({
    selector: 'jhi-survey-question-intf-detail',
    templateUrl: './survey-question-intf-detail.component.html'
})
export class SurveyQuestionIntfDetailComponent implements OnInit, OnDestroy {

    surveyQuestionIntf: SurveyQuestionIntf;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private surveyQuestionIntfService: SurveyQuestionIntfService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInSurveyQuestionIntfs();
    }

    load(id) {
        this.surveyQuestionIntfService.find(id)
            .subscribe((surveyQuestionIntfResponse: HttpResponse<SurveyQuestionIntf>) => {
                this.surveyQuestionIntf = surveyQuestionIntfResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInSurveyQuestionIntfs() {
        this.eventSubscriber = this.eventManager.subscribe(
            'surveyQuestionIntfListModification',
            (response) => this.load(this.surveyQuestionIntf.id)
        );
    }
}
