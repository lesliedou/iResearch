import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { UploadInfo } from './upload-info.model';
import { UploadInfoPopupService } from './upload-info-popup.service';
import { UploadInfoService } from './upload-info.service';
import { Department, DepartmentService } from '../department';

@Component({
    selector: 'jhi-upload-info-dialog',
    templateUrl: './upload-info-dialog.component.html'
})
export class UploadInfoDialogComponent implements OnInit {

    uploadInfo: UploadInfo;
    isSaving: boolean;

    departments: Department[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private uploadInfoService: UploadInfoService,
        private departmentService: DepartmentService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.departmentService
            .query({filter: 'uploadinfo-is-null'})
            .subscribe((res: HttpResponse<Department[]>) => {
                if (!this.uploadInfo.departmentId) {
                    this.departments = res.body;
                } else {
                    this.departmentService
                        .find(this.uploadInfo.departmentId)
                        .subscribe((subRes: HttpResponse<Department>) => {
                            this.departments = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.uploadInfo.id !== undefined) {
            this.subscribeToSaveResponse(
                this.uploadInfoService.update(this.uploadInfo));
        } else {
            this.subscribeToSaveResponse(
                this.uploadInfoService.create(this.uploadInfo));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<UploadInfo>>) {
        result.subscribe((res: HttpResponse<UploadInfo>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: UploadInfo) {
        this.eventManager.broadcast({ name: 'uploadInfoListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackDepartmentById(index: number, item: Department) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-upload-info-popup',
    template: ''
})
export class UploadInfoPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private uploadInfoPopupService: UploadInfoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.uploadInfoPopupService
                    .open(UploadInfoDialogComponent as Component, params['id']);
            } else {
                this.uploadInfoPopupService
                    .open(UploadInfoDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
