import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Notes } from './notes.model';
import { NotesService } from './notes.service';

@Component({
    selector: 'jhi-notes-detail',
    templateUrl: './notes-detail.component.html'
})
export class NotesDetailComponent implements OnInit, OnDestroy {

    notes: Notes;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private notesService: NotesService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInNotes();
    }

    load(id) {
        this.notesService.find(id)
            .subscribe((notesResponse: HttpResponse<Notes>) => {
                this.notes = notesResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInNotes() {
        this.eventSubscriber = this.eventManager.subscribe(
            'notesListModification',
            (response) => this.load(this.notes.id)
        );
    }
}
