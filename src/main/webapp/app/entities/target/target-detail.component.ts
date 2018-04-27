import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Target } from './target.model';
import { TargetService } from './target.service';

@Component({
    selector: 'jhi-target-detail',
    templateUrl: './target-detail.component.html'
})
export class TargetDetailComponent implements OnInit, OnDestroy {

    target: Target;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private targetService: TargetService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInTargets();
    }

    load(id) {
        this.targetService.find(id)
            .subscribe((targetResponse: HttpResponse<Target>) => {
                this.target = targetResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInTargets() {
        this.eventSubscriber = this.eventManager.subscribe(
            'targetListModification',
            (response) => this.load(this.target.id)
        );
    }
}
