/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { IResearchTestModule } from '../../../test.module';
import { SurveyQuestionFlowDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/survey-question-flow/survey-question-flow-delete-dialog.component';
import { SurveyQuestionFlowService } from '../../../../../../main/webapp/app/entities/survey-question-flow/survey-question-flow.service';

describe('Component Tests', () => {

    describe('SurveyQuestionFlow Management Delete Component', () => {
        let comp: SurveyQuestionFlowDeleteDialogComponent;
        let fixture: ComponentFixture<SurveyQuestionFlowDeleteDialogComponent>;
        let service: SurveyQuestionFlowService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [IResearchTestModule],
                declarations: [SurveyQuestionFlowDeleteDialogComponent],
                providers: [
                    SurveyQuestionFlowService
                ]
            })
            .overrideTemplate(SurveyQuestionFlowDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SurveyQuestionFlowDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SurveyQuestionFlowService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(Observable.of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
