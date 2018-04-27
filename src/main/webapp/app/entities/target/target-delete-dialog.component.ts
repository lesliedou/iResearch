import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Target } from './target.model';
import { TargetPopupService } from './target-popup.service';
import { TargetService } from './target.service';

@Component({
    selector: 'jhi-target-delete-dialog',
    templateUrl: './target-delete-dialog.component.html'
})
export class TargetDeleteDialogComponent {

    target: Target;

    constructor(
        private targetService: TargetService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.targetService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'targetListModification',
                content: 'Deleted an target'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-target-delete-popup',
    template: ''
})
export class TargetDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private targetPopupService: TargetPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.targetPopupService
                .open(TargetDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
