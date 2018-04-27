/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { IResearchTestModule } from '../../../test.module';
import { SurveyQuestionFlowDetailComponent } from '../../../../../../main/webapp/app/entities/survey-question-flow/survey-question-flow-detail.component';
import { SurveyQuestionFlowService } from '../../../../../../main/webapp/app/entities/survey-question-flow/survey-question-flow.service';
import { SurveyQuestionFlow } from '../../../../../../main/webapp/app/entities/survey-question-flow/survey-question-flow.model';

describe('Component Tests', () => {

    describe('SurveyQuestionFlow Management Detail Component', () => {
        let comp: SurveyQuestionFlowDetailComponent;
        let fixture: ComponentFixture<SurveyQuestionFlowDetailComponent>;
        let service: SurveyQuestionFlowService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [IResearchTestModule],
                declarations: [SurveyQuestionFlowDetailComponent],
                providers: [
                    SurveyQuestionFlowService
                ]
            })
            .overrideTemplate(SurveyQuestionFlowDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SurveyQuestionFlowDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SurveyQuestionFlowService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new SurveyQuestionFlow(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.surveyQuestionFlow).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
