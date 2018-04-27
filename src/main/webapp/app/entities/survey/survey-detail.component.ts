import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Survey } from './survey.model';
import { SurveyService } from './survey.service';

@Component({
    selector: 'jhi-survey-detail',
    templateUrl: './survey-detail.component.html'
})
export class SurveyDetailComponent implements OnInit, OnDestroy {

    survey: Survey;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private surveyService: SurveyService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInSurveys();
    }

    load(id) {
        this.surveyService.find(id)
            .subscribe((surveyResponse: HttpResponse<Survey>) => {
                this.survey = surveyResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInSurveys() {
        this.eventSubscriber = this.eventManager.subscribe(
            'surveyListModification',
            (response) => this.load(this.survey.id)
        );
    }
}
