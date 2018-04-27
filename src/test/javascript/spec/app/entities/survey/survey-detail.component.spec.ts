/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { IResearchTestModule } from '../../../test.module';
import { SurveyDetailComponent } from '../../../../../../main/webapp/app/entities/survey/survey-detail.component';
import { SurveyService } from '../../../../../../main/webapp/app/entities/survey/survey.service';
import { Survey } from '../../../../../../main/webapp/app/entities/survey/survey.model';

describe('Component Tests', () => {

    describe('Survey Management Detail Component', () => {
        let comp: SurveyDetailComponent;
        let fixture: ComponentFixture<SurveyDetailComponent>;
        let service: SurveyService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [IResearchTestModule],
                declarations: [SurveyDetailComponent],
                providers: [
                    SurveyService
                ]
            })
            .overrideTemplate(SurveyDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SurveyDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SurveyService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new Survey(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.survey).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
