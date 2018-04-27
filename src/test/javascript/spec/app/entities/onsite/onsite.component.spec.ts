/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { IResearchTestModule } from '../../../test.module';
import { OnsiteComponent } from '../../../../../../main/webapp/app/entities/onsite/onsite.component';
import { OnsiteService } from '../../../../../../main/webapp/app/entities/onsite/onsite.service';
import { Onsite } from '../../../../../../main/webapp/app/entities/onsite/onsite.model';

describe('Component Tests', () => {

    describe('Onsite Management Component', () => {
        let comp: OnsiteComponent;
        let fixture: ComponentFixture<OnsiteComponent>;
        let service: OnsiteService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [IResearchTestModule],
                declarations: [OnsiteComponent],
                providers: [
                    OnsiteService
                ]
            })
            .overrideTemplate(OnsiteComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(OnsiteComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OnsiteService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Onsite(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.onsites[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
