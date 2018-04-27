import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Files } from './files.model';
import { FilesPopupService } from './files-popup.service';
import { FilesService } from './files.service';

@Component({
    selector: 'jhi-files-delete-dialog',
    templateUrl: './files-delete-dialog.component.html'
})
export class FilesDeleteDialogComponent {

    files: Files;

    constructor(
        private filesService: FilesService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.filesService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'filesListModification',
                content: 'Deleted an files'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-files-delete-popup',
    template: ''
})
export class FilesDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private filesPopupService: FilesPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.filesPopupService
                .open(FilesDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
