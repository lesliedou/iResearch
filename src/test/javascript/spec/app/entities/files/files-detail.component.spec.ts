/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { IResearchTestModule } from '../../../test.module';
import { FilesDetailComponent } from '../../../../../../main/webapp/app/entities/files/files-detail.component';
import { FilesService } from '../../../../../../main/webapp/app/entities/files/files.service';
import { Files } from '../../../../../../main/webapp/app/entities/files/files.model';

describe('Component Tests', () => {

    describe('Files Management Detail Component', () => {
        let comp: FilesDetailComponent;
        let fixture: ComponentFixture<FilesDetailComponent>;
        let service: FilesService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [IResearchTestModule],
                declarations: [FilesDetailComponent],
                providers: [
                    FilesService
                ]
            })
            .overrideTemplate(FilesDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(FilesDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FilesService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new Files(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.files).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
