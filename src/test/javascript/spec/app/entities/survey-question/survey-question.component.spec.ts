/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { IResearchTestModule } from '../../../test.module';
import { SurveyQuestionComponent } from '../../../../../../main/webapp/app/entities/survey-question/survey-question.component';
import { SurveyQuestionService } from '../../../../../../main/webapp/app/entities/survey-question/survey-question.service';
import { SurveyQuestion } from '../../../../../../main/webapp/app/entities/survey-question/survey-question.model';

describe('Component Tests', () => {

    describe('SurveyQuestion Management Component', () => {
        let comp: SurveyQuestionComponent;
        let fixture: ComponentFixture<SurveyQuestionComponent>;
        let service: SurveyQuestionService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [IResearchTestModule],
                declarations: [SurveyQuestionComponent],
                providers: [
                    SurveyQuestionService
                ]
            })
            .overrideTemplate(SurveyQuestionComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SurveyQuestionComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SurveyQuestionService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new SurveyQuestion(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.surveyQuestions[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
