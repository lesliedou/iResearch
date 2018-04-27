/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { IResearchTestModule } from '../../../test.module';
import { SurveyQuestionIntfComponent } from '../../../../../../main/webapp/app/entities/survey-question-intf/survey-question-intf.component';
import { SurveyQuestionIntfService } from '../../../../../../main/webapp/app/entities/survey-question-intf/survey-question-intf.service';
import { SurveyQuestionIntf } from '../../../../../../main/webapp/app/entities/survey-question-intf/survey-question-intf.model';

describe('Component Tests', () => {

    describe('SurveyQuestionIntf Management Component', () => {
        let comp: SurveyQuestionIntfComponent;
        let fixture: ComponentFixture<SurveyQuestionIntfComponent>;
        let service: SurveyQuestionIntfService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [IResearchTestModule],
                declarations: [SurveyQuestionIntfComponent],
                providers: [
                    SurveyQuestionIntfService
                ]
            })
            .overrideTemplate(SurveyQuestionIntfComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SurveyQuestionIntfComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SurveyQuestionIntfService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new SurveyQuestionIntf(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.surveyQuestionIntfs[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
