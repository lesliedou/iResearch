import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { OnsiteProcess } from './onsite-process.model';
import { OnsiteProcessService } from './onsite-process.service';

@Component({
    selector: 'jhi-onsite-process-detail',
    templateUrl: './onsite-process-detail.component.html'
})
export class OnsiteProcessDetailComponent implements OnInit, OnDestroy {

    onsiteProcess: OnsiteProcess;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private onsiteProcessService: OnsiteProcessService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInOnsiteProcesses();
    }

    load(id) {
        this.onsiteProcessService.find(id)
            .subscribe((onsiteProcessResponse: HttpResponse<OnsiteProcess>) => {
                this.onsiteProcess = onsiteProcessResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInOnsiteProcesses() {
        this.eventSubscriber = this.eventManager.subscribe(
            'onsiteProcessListModification',
            (response) => this.load(this.onsiteProcess.id)
        );
    }
}
