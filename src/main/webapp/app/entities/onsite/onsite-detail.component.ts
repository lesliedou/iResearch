import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Onsite } from './onsite.model';
import { OnsiteService } from './onsite.service';

@Component({
    selector: 'jhi-onsite-detail',
    templateUrl: './onsite-detail.component.html'
})
export class OnsiteDetailComponent implements OnInit, OnDestroy {

    onsite: Onsite;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private onsiteService: OnsiteService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInOnsites();
    }

    load(id) {
        this.onsiteService.find(id)
            .subscribe((onsiteResponse: HttpResponse<Onsite>) => {
                this.onsite = onsiteResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInOnsites() {
        this.eventSubscriber = this.eventManager.subscribe(
            'onsiteListModification',
            (response) => this.load(this.onsite.id)
        );
    }
}
