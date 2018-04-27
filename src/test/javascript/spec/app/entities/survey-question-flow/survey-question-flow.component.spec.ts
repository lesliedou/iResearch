/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { IResearchTestModule } from '../../../test.module';
import { SurveyQuestionFlowComponent } from '../../../../../../main/webapp/app/entities/survey-question-flow/survey-question-flow.component';
import { SurveyQuestionFlowService } from '../../../../../../main/webapp/app/entities/survey-question-flow/survey-question-flow.service';
import { SurveyQuestionFlow } from '../../../../../../main/webapp/app/entities/survey-question-flow/survey-question-flow.model';

describe('Component Tests', () => {

    describe('SurveyQuestionFlow Management Component', () => {
        let comp: SurveyQuestionFlowComponent;
        let fixture: ComponentFixture<SurveyQuestionFlowComponent>;
        let service: SurveyQuestionFlowService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [IResearchTestModule],
                declarations: [SurveyQuestionFlowComponent],
                providers: [
                    SurveyQuestionFlowService
                ]
            })
            .overrideTemplate(SurveyQuestionFlowComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SurveyQuestionFlowComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SurveyQuestionFlowService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new SurveyQuestionFlow(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.surveyQuestionFlows[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
