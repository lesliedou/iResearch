import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { ConfigAcl } from './config-acl.model';
import { ConfigAclService } from './config-acl.service';

@Component({
    selector: 'jhi-config-acl-detail',
    templateUrl: './config-acl-detail.component.html'
})
export class ConfigAclDetailComponent implements OnInit, OnDestroy {

    configAcl: ConfigAcl;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private configAclService: ConfigAclService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInConfigAcls();
    }

    load(id) {
        this.configAclService.find(id)
            .subscribe((configAclResponse: HttpResponse<ConfigAcl>) => {
                this.configAcl = configAclResponse.body;
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

    registerChangeInConfigAcls() {
        this.eventSubscriber = this.eventManager.subscribe(
            'configAclListModification',
            (response) => this.load(this.configAcl.id)
        );
    }
}
