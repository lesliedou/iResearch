import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Onsite } from './onsite.model';
import { OnsitePopupService } from './onsite-popup.service';
import { OnsiteService } from './onsite.service';
import { Department, DepartmentService } from '../department';

@Component({
    selector: 'jhi-onsite-dialog',
    templateUrl: './onsite-dialog.component.html'
})
export class OnsiteDialogComponent implements OnInit {

    onsite: Onsite;
    isSaving: boolean;

    departments: Department[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private onsiteService: OnsiteService,
        private departmentService: DepartmentService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.departmentService
            .query({filter: 'onsite-is-null'})
            .subscribe((res: HttpResponse<Department[]>) => {
                if (!this.onsite.departmentId) {
                    this.departments = res.body;
                } else {
                    this.departmentService
                        .find(this.onsite.departmentId)
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
        if (this.onsite.id !== undefined) {
            this.subscribeToSaveResponse(
                this.onsiteService.update(this.onsite));
        } else {
            this.subscribeToSaveResponse(
                this.onsiteService.create(this.onsite));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Onsite>>) {
        result.subscribe((res: HttpResponse<Onsite>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Onsite) {
        this.eventManager.broadcast({ name: 'onsiteListModification', content: 'OK'});
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
    selector: 'jhi-onsite-popup',
    template: ''
})
export class OnsitePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private onsitePopupService: OnsitePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.onsitePopupService
                    .open(OnsiteDialogComponent as Component, params['id']);
            } else {
                this.onsitePopupService
                    .open(OnsiteDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
