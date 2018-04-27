/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { IResearchTestModule } from '../../../test.module';
import { SurveyQuestionIntfDialogComponent } from '../../../../../../main/webapp/app/entities/survey-question-intf/survey-question-intf-dialog.component';
import { SurveyQuestionIntfService } from '../../../../../../main/webapp/app/entities/survey-question-intf/survey-question-intf.service';
import { SurveyQuestionIntf } from '../../../../../../main/webapp/app/entities/survey-question-intf/survey-question-intf.model';
import { SurveyQuestionService } from '../../../../../../main/webapp/app/entities/survey-question';

describe('Component Tests', () => {

    describe('SurveyQuestionIntf Management Dialog Component', () => {
        let comp: SurveyQuestionIntfDialogComponent;
        let fixture: ComponentFixture<SurveyQuestionIntfDialogComponent>;
        let service: SurveyQuestionIntfService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [IResearchTestModule],
                declarations: [SurveyQuestionIntfDialogComponent],
                providers: [
                    SurveyQuestionService,
                    SurveyQuestionIntfService
                ]
            })
            .overrideTemplate(SurveyQuestionIntfDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SurveyQuestionIntfDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SurveyQuestionIntfService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new SurveyQuestionIntf(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.surveyQuestionIntf = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'surveyQuestionIntfListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new SurveyQuestionIntf();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.surveyQuestionIntf = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'surveyQuestionIntfListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
