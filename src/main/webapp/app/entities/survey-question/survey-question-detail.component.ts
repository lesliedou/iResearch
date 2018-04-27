import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { SurveyQuestion } from './survey-question.model';
import { SurveyQuestionService } from './survey-question.service';

@Component({
    selector: 'jhi-survey-question-detail',
    templateUrl: './survey-question-detail.component.html'
})
export class SurveyQuestionDetailComponent implements OnInit, OnDestroy {

    surveyQuestion: SurveyQuestion;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private surveyQuestionService: SurveyQuestionService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInSurveyQuestions();
    }

    load(id) {
        this.surveyQuestionService.find(id)
            .subscribe((surveyQuestionResponse: HttpResponse<SurveyQuestion>) => {
                this.surveyQuestion = surveyQuestionResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInSurveyQuestions() {
        this.eventSubscriber = this.eventManager.subscribe(
            'surveyQuestionListModification',
            (response) => this.load(this.surveyQuestion.id)
        );
    }
}
