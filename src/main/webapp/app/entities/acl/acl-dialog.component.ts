import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { Acl } from './acl.model';
import { AclPopupService } from './acl-popup.service';
import { AclService } from './acl.service';

@Component({
    selector: 'jhi-acl-dialog',
    templateUrl: './acl-dialog.component.html'
})
export class AclDialogComponent implements OnInit {

    acl: Acl;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private aclService: AclService,
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
        if (this.acl.id !== undefined) {
            this.subscribeToSaveResponse(
                this.aclService.update(this.acl));
        } else {
            this.subscribeToSaveResponse(
                this.aclService.create(this.acl));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Acl>>) {
        result.subscribe((res: HttpResponse<Acl>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Acl) {
        this.eventManager.broadcast({ name: 'aclListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-acl-popup',
    template: ''
})
export class AclPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private aclPopupService: AclPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.aclPopupService
                    .open(AclDialogComponent as Component, params['id']);
            } else {
                this.aclPopupService
                    .open(AclDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
