import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { UploadInfo } from './upload-info.model';
import { UploadInfoPopupService } from './upload-info-popup.service';
import { UploadInfoService } from './upload-info.service';

@Component({
    selector: 'jhi-upload-info-delete-dialog',
    templateUrl: './upload-info-delete-dialog.component.html'
})
export class UploadInfoDeleteDialogComponent {

    uploadInfo: UploadInfo;

    constructor(
        private uploadInfoService: UploadInfoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.uploadInfoService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'uploadInfoListModification',
                content: 'Deleted an uploadInfo'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-upload-info-delete-popup',
    template: ''
})
export class UploadInfoDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private uploadInfoPopupService: UploadInfoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.uploadInfoPopupService
                .open(UploadInfoDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
