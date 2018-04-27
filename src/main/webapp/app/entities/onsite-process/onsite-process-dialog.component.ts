import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { OnsiteProcess } from './onsite-process.model';
import { OnsiteProcessPopupService } from './onsite-process-popup.service';
import { OnsiteProcessService } from './onsite-process.service';
import { Onsite, OnsiteService } from '../onsite';

@Component({
    selector: 'jhi-onsite-process-dialog',
    templateUrl: './onsite-process-dialog.component.html'
})
export class OnsiteProcessDialogComponent implements OnInit {

    onsiteProcess: OnsiteProcess;
    isSaving: boolean;

    onsites: Onsite[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private onsiteProcessService: OnsiteProcessService,
        private onsiteService: OnsiteService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.onsiteService
            .query({filter: 'onsiteprocess-is-null'})
            .subscribe((res: HttpResponse<Onsite[]>) => {
                if (!this.onsiteProcess.onsiteId) {
                    this.onsites = res.body;
                } else {
                    this.onsiteService
                        .find(this.onsiteProcess.onsiteId)
                        .subscribe((subRes: HttpResponse<Onsite>) => {
                            this.onsites = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.onsiteProcess.id !== undefined) {
            this.subscribeToSaveResponse(
                this.onsiteProcessService.update(this.onsiteProcess));
        } else {
            this.subscribeToSaveResponse(
                this.onsiteProcessService.create(this.onsiteProcess));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<OnsiteProcess>>) {
        result.subscribe((res: HttpResponse<OnsiteProcess>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: OnsiteProcess) {
        this.eventManager.broadcast({ name: 'onsiteProcessListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackOnsiteById(index: number, item: Onsite) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-onsite-process-popup',
    template: ''
})
export class OnsiteProcessPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private onsiteProcessPopupService: OnsiteProcessPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.onsiteProcessPopupService
                    .open(OnsiteProcessDialogComponent as Component, params['id']);
            } else {
                this.onsiteProcessPopupService
                    .open(OnsiteProcessDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
