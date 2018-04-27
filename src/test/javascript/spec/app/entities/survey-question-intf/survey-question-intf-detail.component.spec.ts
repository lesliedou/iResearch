/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { IResearchTestModule } from '../../../test.module';
import { SurveyQuestionIntfDetailComponent } from '../../../../../../main/webapp/app/entities/survey-question-intf/survey-question-intf-detail.component';
import { SurveyQuestionIntfService } from '../../../../../../main/webapp/app/entities/survey-question-intf/survey-question-intf.service';
import { SurveyQuestionIntf } from '../../../../../../main/webapp/app/entities/survey-question-intf/survey-question-intf.model';

describe('Component Tests', () => {

    describe('SurveyQuestionIntf Management Detail Component', () => {
        let comp: SurveyQuestionIntfDetailComponent;
        let fixture: ComponentFixture<SurveyQuestionIntfDetailComponent>;
        let service: SurveyQuestionIntfService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [IResearchTestModule],
                declarations: [SurveyQuestionIntfDetailComponent],
                providers: [
                    SurveyQuestionIntfService
                ]
            })
            .overrideTemplate(SurveyQuestionIntfDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SurveyQuestionIntfDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SurveyQuestionIntfService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new SurveyQuestionIntf(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.surveyQuestionIntf).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
