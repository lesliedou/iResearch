/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { IResearchTestModule } from '../../../test.module';
import { SurveyQuestionFlowDialogComponent } from '../../../../../../main/webapp/app/entities/survey-question-flow/survey-question-flow-dialog.component';
import { SurveyQuestionFlowService } from '../../../../../../main/webapp/app/entities/survey-question-flow/survey-question-flow.service';
import { SurveyQuestionFlow } from '../../../../../../main/webapp/app/entities/survey-question-flow/survey-question-flow.model';
import { SurveyQuestionService } from '../../../../../../main/webapp/app/entities/survey-question';

describe('Component Tests', () => {

    describe('SurveyQuestionFlow Management Dialog Component', () => {
        let comp: SurveyQuestionFlowDialogComponent;
        let fixture: ComponentFixture<SurveyQuestionFlowDialogComponent>;
        let service: SurveyQuestionFlowService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [IResearchTestModule],
                declarations: [SurveyQuestionFlowDialogComponent],
                providers: [
                    SurveyQuestionService,
                    SurveyQuestionFlowService
                ]
            })
            .overrideTemplate(SurveyQuestionFlowDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SurveyQuestionFlowDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SurveyQuestionFlowService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new SurveyQuestionFlow(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.surveyQuestionFlow = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'surveyQuestionFlowListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new SurveyQuestionFlow();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.surveyQuestionFlow = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'surveyQuestionFlowListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
