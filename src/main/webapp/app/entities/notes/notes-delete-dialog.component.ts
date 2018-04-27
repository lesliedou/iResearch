import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Notes } from './notes.model';
import { NotesPopupService } from './notes-popup.service';
import { NotesService } from './notes.service';

@Component({
    selector: 'jhi-notes-delete-dialog',
    templateUrl: './notes-delete-dialog.component.html'
})
export class NotesDeleteDialogComponent {

    notes: Notes;

    constructor(
        private notesService: NotesService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.notesService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'notesListModification',
                content: 'Deleted an notes'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-notes-delete-popup',
    template: ''
})
export class NotesDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private notesPopupService: NotesPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.notesPopupService
                .open(NotesDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
