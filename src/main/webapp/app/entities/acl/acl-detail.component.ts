import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { Acl } from './acl.model';
import { AclService } from './acl.service';

@Component({
    selector: 'jhi-acl-detail',
    templateUrl: './acl-detail.component.html'
})
export class AclDetailComponent implements OnInit, OnDestroy {

    acl: Acl;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private aclService: AclService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInAcls();
    }

    load(id) {
        this.aclService.find(id)
            .subscribe((aclResponse: HttpResponse<Acl>) => {
                this.acl = aclResponse.body;
            });
    }
    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInAcls() {
        this.eventSubscriber = this.eventManager.subscribe(
            'aclListModification',
            (response) => this.load(this.acl.id)
        );
    }
}
