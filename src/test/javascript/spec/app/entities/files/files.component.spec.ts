/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { IResearchTestModule } from '../../../test.module';
import { FilesComponent } from '../../../../../../main/webapp/app/entities/files/files.component';
import { FilesService } from '../../../../../../main/webapp/app/entities/files/files.service';
import { Files } from '../../../../../../main/webapp/app/entities/files/files.model';

describe('Component Tests', () => {

    describe('Files Management Component', () => {
        let comp: FilesComponent;
        let fixture: ComponentFixture<FilesComponent>;
        let service: FilesService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [IResearchTestModule],
                declarations: [FilesComponent],
                providers: [
                    FilesService
                ]
            })
            .overrideTemplate(FilesComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(FilesComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FilesService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Files(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.files[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
