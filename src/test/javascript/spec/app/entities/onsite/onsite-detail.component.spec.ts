/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { IResearchTestModule } from '../../../test.module';
import { OnsiteDetailComponent } from '../../../../../../main/webapp/app/entities/onsite/onsite-detail.component';
import { OnsiteService } from '../../../../../../main/webapp/app/entities/onsite/onsite.service';
import { Onsite } from '../../../../../../main/webapp/app/entities/onsite/onsite.model';

describe('Component Tests', () => {

    describe('Onsite Management Detail Component', () => {
        let comp: OnsiteDetailComponent;
        let fixture: ComponentFixture<OnsiteDetailComponent>;
        let service: OnsiteService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [IResearchTestModule],
                declarations: [OnsiteDetailComponent],
                providers: [
                    OnsiteService
                ]
            })
            .overrideTemplate(OnsiteDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(OnsiteDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OnsiteService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new Onsite(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.onsite).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
