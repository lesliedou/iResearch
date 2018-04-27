/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { IResearchTestModule } from '../../../test.module';
import { OnsiteProcessDetailComponent } from '../../../../../../main/webapp/app/entities/onsite-process/onsite-process-detail.component';
import { OnsiteProcessService } from '../../../../../../main/webapp/app/entities/onsite-process/onsite-process.service';
import { OnsiteProcess } from '../../../../../../main/webapp/app/entities/onsite-process/onsite-process.model';

describe('Component Tests', () => {

    describe('OnsiteProcess Management Detail Component', () => {
        let comp: OnsiteProcessDetailComponent;
        let fixture: ComponentFixture<OnsiteProcessDetailComponent>;
        let service: OnsiteProcessService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [IResearchTestModule],
                declarations: [OnsiteProcessDetailComponent],
                providers: [
                    OnsiteProcessService
                ]
            })
            .overrideTemplate(OnsiteProcessDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(OnsiteProcessDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OnsiteProcessService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new OnsiteProcess(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.onsiteProcess).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
