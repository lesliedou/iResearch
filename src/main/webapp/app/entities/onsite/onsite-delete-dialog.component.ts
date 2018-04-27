import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Onsite } from './onsite.model';
import { OnsitePopupService } from './onsite-popup.service';
import { OnsiteService } from './onsite.service';

@Component({
    selector: 'jhi-onsite-delete-dialog',
    templateUrl: './onsite-delete-dialog.component.html'
})
export class OnsiteDeleteDialogComponent {

    onsite: Onsite;

    constructor(
        private onsiteService: OnsiteService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.onsiteService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'onsiteListModification',
                content: 'Deleted an onsite'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-onsite-delete-popup',
    template: ''
})
export class OnsiteDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private onsitePopupService: OnsitePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.onsitePopupService
                .open(OnsiteDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
