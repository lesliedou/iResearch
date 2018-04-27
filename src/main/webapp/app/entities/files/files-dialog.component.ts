import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Files } from './files.model';
import { FilesPopupService } from './files-popup.service';
import { FilesService } from './files.service';

@Component({
    selector: 'jhi-files-dialog',
    templateUrl: './files-dialog.component.html'
})
export class FilesDialogComponent implements OnInit {

    files: Files;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private filesService: FilesService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.files.id !== undefined) {
            this.subscribeToSaveResponse(
                this.filesService.update(this.files));
        } else {
            this.subscribeToSaveResponse(
                this.filesService.create(this.files));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Files>>) {
        result.subscribe((res: HttpResponse<Files>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Files) {
        this.eventManager.broadcast({ name: 'filesListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-files-popup',
    template: ''
})
export class FilesPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private filesPopupService: FilesPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.filesPopupService
                    .open(FilesDialogComponent as Component, params['id']);
            } else {
                this.filesPopupService
                    .open(FilesDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
