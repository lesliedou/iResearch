import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { OnsiteProcess } from './onsite-process.model';
import { OnsiteProcessPopupService } from './onsite-process-popup.service';
import { OnsiteProcessService } from './onsite-process.service';

@Component({
    selector: 'jhi-onsite-process-delete-dialog',
    templateUrl: './onsite-process-delete-dialog.component.html'
})
export class OnsiteProcessDeleteDialogComponent {

    onsiteProcess: OnsiteProcess;

    constructor(
        private onsiteProcessService: OnsiteProcessService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.onsiteProcessService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'onsiteProcessListModification',
                content: 'Deleted an onsiteProcess'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-onsite-process-delete-popup',
    template: ''
})
export class OnsiteProcessDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private onsiteProcessPopupService: OnsiteProcessPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.onsiteProcessPopupService
                .open(OnsiteProcessDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
