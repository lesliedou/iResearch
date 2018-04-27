import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { SurveyQuestionFlow } from './survey-question-flow.model';
import { SurveyQuestionFlowService } from './survey-question-flow.service';

@Component({
    selector: 'jhi-survey-question-flow-detail',
    templateUrl: './survey-question-flow-detail.component.html'
})
export class SurveyQuestionFlowDetailComponent implements OnInit, OnDestroy {

    surveyQuestionFlow: SurveyQuestionFlow;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private surveyQuestionFlowService: SurveyQuestionFlowService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInSurveyQuestionFlows();
    }

    load(id) {
        this.surveyQuestionFlowService.find(id)
            .subscribe((surveyQuestionFlowResponse: HttpResponse<SurveyQuestionFlow>) => {
                this.surveyQuestionFlow = surveyQuestionFlowResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInSurveyQuestionFlows() {
        this.eventSubscriber = this.eventManager.subscribe(
            'surveyQuestionFlowListModification',
            (response) => this.load(this.surveyQuestionFlow.id)
        );
    }
}
