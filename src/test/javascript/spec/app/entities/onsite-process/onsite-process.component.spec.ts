/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { IResearchTestModule } from '../../../test.module';
import { OnsiteProcessComponent } from '../../../../../../main/webapp/app/entities/onsite-process/onsite-process.component';
import { OnsiteProcessService } from '../../../../../../main/webapp/app/entities/onsite-process/onsite-process.service';
import { OnsiteProcess } from '../../../../../../main/webapp/app/entities/onsite-process/onsite-process.model';

describe('Component Tests', () => {

    describe('OnsiteProcess Management Component', () => {
        let comp: OnsiteProcessComponent;
        let fixture: ComponentFixture<OnsiteProcessComponent>;
        let service: OnsiteProcessService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [IResearchTestModule],
                declarations: [OnsiteProcessComponent],
                providers: [
                    OnsiteProcessService
                ]
            })
            .overrideTemplate(OnsiteProcessComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(OnsiteProcessComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OnsiteProcessService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new OnsiteProcess(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.onsiteProcesses[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
