/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { IResearchTestModule } from '../../../test.module';
import { SurveyQuestionIntfDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/survey-question-intf/survey-question-intf-delete-dialog.component';
import { SurveyQuestionIntfService } from '../../../../../../main/webapp/app/entities/survey-question-intf/survey-question-intf.service';

describe('Component Tests', () => {

    describe('SurveyQuestionIntf Management Delete Component', () => {
        let comp: SurveyQuestionIntfDeleteDialogComponent;
        let fixture: ComponentFixture<SurveyQuestionIntfDeleteDialogComponent>;
        let service: SurveyQuestionIntfService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [IResearchTestModule],
                declarations: [SurveyQuestionIntfDeleteDialogComponent],
                providers: [
                    SurveyQuestionIntfService
                ]
            })
            .overrideTemplate(SurveyQuestionIntfDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SurveyQuestionIntfDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SurveyQuestionIntfService);
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
