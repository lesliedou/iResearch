import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { ConfigAcl } from './config-acl.model';
import { ConfigAclPopupService } from './config-acl-popup.service';
import { ConfigAclService } from './config-acl.service';

@Component({
    selector: 'jhi-config-acl-dialog',
    templateUrl: './config-acl-dialog.component.html'
})
export class ConfigAclDialogComponent implements OnInit {

    configAcl: ConfigAcl;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private configAclService: ConfigAclService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.configAcl.id !== undefined) {
            this.subscribeToSaveResponse(
                this.configAclService.update(this.configAcl));
        } else {
            this.subscribeToSaveResponse(
                this.configAclService.create(this.configAcl));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ConfigAcl>>) {
        result.subscribe((res: HttpResponse<ConfigAcl>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: ConfigAcl) {
        this.eventManager.broadcast({ name: 'configAclListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-config-acl-popup',
    template: ''
})
export class ConfigAclPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private configAclPopupService: ConfigAclPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.configAclPopupService
                    .open(ConfigAclDialogComponent as Component, params['id']);
            } else {
                this.configAclPopupService
                    .open(ConfigAclDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
