import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Acl } from './acl.model';
import { AclPopupService } from './acl-popup.service';
import { AclService } from './acl.service';

@Component({
    selector: 'jhi-acl-delete-dialog',
    templateUrl: './acl-delete-dialog.component.html'
})
export class AclDeleteDialogComponent {

    acl: Acl;

    constructor(
        private aclService: AclService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.aclService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'aclListModification',
                content: 'Deleted an acl'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-acl-delete-popup',
    template: ''
})
export class AclDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private aclPopupService: AclPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.aclPopupService
                .open(AclDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
