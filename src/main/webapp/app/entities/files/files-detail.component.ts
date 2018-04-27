import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Files } from './files.model';
import { FilesService } from './files.service';

@Component({
    selector: 'jhi-files-detail',
    templateUrl: './files-detail.component.html'
})
export class FilesDetailComponent implements OnInit, OnDestroy {

    files: Files;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private filesService: FilesService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInFiles();
    }

    load(id) {
        this.filesService.find(id)
            .subscribe((filesResponse: HttpResponse<Files>) => {
                this.files = filesResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInFiles() {
        this.eventSubscriber = this.eventManager.subscribe(
            'filesListModification',
            (response) => this.load(this.files.id)
        );
    }
}
