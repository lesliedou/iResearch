/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { IResearchTestModule } from '../../../test.module';
import { TargetComponent } from '../../../../../../main/webapp/app/entities/target/target.component';
import { TargetService } from '../../../../../../main/webapp/app/entities/target/target.service';
import { Target } from '../../../../../../main/webapp/app/entities/target/target.model';

describe('Component Tests', () => {

    describe('Target Management Component', () => {
        let comp: TargetComponent;
        let fixture: ComponentFixture<TargetComponent>;
        let service: TargetService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [IResearchTestModule],
                declarations: [TargetComponent],
                providers: [
                    TargetService
                ]
            })
            .overrideTemplate(TargetComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TargetComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TargetService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Target(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.targets[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
