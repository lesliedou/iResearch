/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { IResearchTestModule } from '../../../test.module';
import { SurveyQuestionDetailComponent } from '../../../../../../main/webapp/app/entities/survey-question/survey-question-detail.component';
import { SurveyQuestionService } from '../../../../../../main/webapp/app/entities/survey-question/survey-question.service';
import { SurveyQuestion } from '../../../../../../main/webapp/app/entities/survey-question/survey-question.model';

describe('Component Tests', () => {

    describe('SurveyQuestion Management Detail Component', () => {
        let comp: SurveyQuestionDetailComponent;
        let fixture: ComponentFixture<SurveyQuestionDetailComponent>;
        let service: SurveyQuestionService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [IResearchTestModule],
                declarations: [SurveyQuestionDetailComponent],
                providers: [
                    SurveyQuestionService
                ]
            })
            .overrideTemplate(SurveyQuestionDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SurveyQuestionDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SurveyQuestionService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new SurveyQuestion(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.surveyQuestion).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
