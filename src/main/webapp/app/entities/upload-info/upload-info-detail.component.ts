import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { UploadInfo } from './upload-info.model';
import { UploadInfoService } from './upload-info.service';

@Component({
    selector: 'jhi-upload-info-detail',
    templateUrl: './upload-info-detail.component.html'
})
export class UploadInfoDetailComponent implements OnInit, OnDestroy {

    uploadInfo: UploadInfo;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private uploadInfoService: UploadInfoService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInUploadInfos();
    }

    load(id) {
        this.uploadInfoService.find(id)
            .subscribe((uploadInfoResponse: HttpResponse<UploadInfo>) => {
                this.uploadInfo = uploadInfoResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInUploadInfos() {
        this.eventSubscriber = this.eventManager.subscribe(
            'uploadInfoListModification',
            (response) => this.load(this.uploadInfo.id)
        );
    }
}
