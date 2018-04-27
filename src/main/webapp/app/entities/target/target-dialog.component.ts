import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Target } from './target.model';
import { TargetPopupService } from './target-popup.service';
import { TargetService } from './target.service';

@Component({
    selector: 'jhi-target-dialog',
    templateUrl: './target-dialog.component.html'
})
export class TargetDialogComponent implements OnInit {

    target: Target;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private targetService: TargetService,
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
        if (this.target.id !== undefined) {
            this.subscribeToSaveResponse(
                this.targetService.update(this.target));
        } else {
            this.subscribeToSaveResponse(
                this.targetService.create(this.target));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Target>>) {
        result.subscribe((res: HttpResponse<Target>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Target) {
        this.eventManager.broadcast({ name: 'targetListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-target-popup',
    template: ''
})
export class TargetPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private targetPopupService: TargetPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.targetPopupService
                    .open(TargetDialogComponent as Component, params['id']);
            } else {
                this.targetPopupService
                    .open(TargetDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
