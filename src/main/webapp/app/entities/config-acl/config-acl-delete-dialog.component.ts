import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ConfigAcl } from './config-acl.model';
import { ConfigAclPopupService } from './config-acl-popup.service';
import { ConfigAclService } from './config-acl.service';

@Component({
    selector: 'jhi-config-acl-delete-dialog',
    templateUrl: './config-acl-delete-dialog.component.html'
})
export class ConfigAclDeleteDialogComponent {

    configAcl: ConfigAcl;

    constructor(
        private configAclService: ConfigAclService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.configAclService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'configAclListModification',
                content: 'Deleted an configAcl'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-config-acl-delete-popup',
    template: ''
})
export class ConfigAclDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private configAclPopupService: ConfigAclPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.configAclPopupService
                .open(ConfigAclDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
