/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { IResearchTestModule } from '../../../test.module';
import { NotesComponent } from '../../../../../../main/webapp/app/entities/notes/notes.component';
import { NotesService } from '../../../../../../main/webapp/app/entities/notes/notes.service';
import { Notes } from '../../../../../../main/webapp/app/entities/notes/notes.model';

describe('Component Tests', () => {

    describe('Notes Management Component', () => {
        let comp: NotesComponent;
        let fixture: ComponentFixture<NotesComponent>;
        let service: NotesService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [IResearchTestModule],
                declarations: [NotesComponent],
                providers: [
                    NotesService
                ]
            })
            .overrideTemplate(NotesComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(NotesComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(NotesService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Notes(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.notes[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
