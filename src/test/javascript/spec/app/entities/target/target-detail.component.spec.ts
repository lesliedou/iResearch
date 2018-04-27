/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { IResearchTestModule } from '../../../test.module';
import { TargetDetailComponent } from '../../../../../../main/webapp/app/entities/target/target-detail.component';
import { TargetService } from '../../../../../../main/webapp/app/entities/target/target.service';
import { Target } from '../../../../../../main/webapp/app/entities/target/target.model';

describe('Component Tests', () => {

    describe('Target Management Detail Component', () => {
        let comp: TargetDetailComponent;
        let fixture: ComponentFixture<TargetDetailComponent>;
        let service: TargetService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [IResearchTestModule],
                declarations: [TargetDetailComponent],
                providers: [
                    TargetService
                ]
            })
            .overrideTemplate(TargetDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TargetDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TargetService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new Target(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.target).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
