import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IResearchSharedModule } from '../../shared';
import {
    NotesService,
    NotesPopupService,
    NotesComponent,
    NotesDetailComponent,
    NotesDialogComponent,
    NotesPopupComponent,
    NotesDeletePopupComponent,
    NotesDeleteDialogComponent,
    notesRoute,
    notesPopupRoute,
    NotesResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...notesRoute,
    ...notesPopupRoute,
];

@NgModule({
    imports: [
        IResearchSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        NotesComponent,
        NotesDetailComponent,
        NotesDialogComponent,
        NotesDeleteDialogComponent,
        NotesPopupComponent,
        NotesDeletePopupComponent,
    ],
    entryComponents: [
        NotesComponent,
        NotesDialogComponent,
        NotesPopupComponent,
        NotesDeleteDialogComponent,
        NotesDeletePopupComponent,
    ],
    providers: [
        NotesService,
        NotesPopupService,
        NotesResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class IResearchNotesModule {}
