/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { IResearchTestModule } from '../../../test.module';
import { NotesDetailComponent } from '../../../../../../main/webapp/app/entities/notes/notes-detail.component';
import { NotesService } from '../../../../../../main/webapp/app/entities/notes/notes.service';
import { Notes } from '../../../../../../main/webapp/app/entities/notes/notes.model';

describe('Component Tests', () => {

    describe('Notes Management Detail Component', () => {
        let comp: NotesDetailComponent;
        let fixture: ComponentFixture<NotesDetailComponent>;
        let service: NotesService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [IResearchTestModule],
                declarations: [NotesDetailComponent],
                providers: [
                    NotesService
                ]
            })
            .overrideTemplate(NotesDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(NotesDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(NotesService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new Notes(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.notes).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
