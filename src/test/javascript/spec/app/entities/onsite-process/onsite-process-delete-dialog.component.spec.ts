/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { IResearchTestModule } from '../../../test.module';
import { OnsiteProcessDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/onsite-process/onsite-process-delete-dialog.component';
import { OnsiteProcessService } from '../../../../../../main/webapp/app/entities/onsite-process/onsite-process.service';

describe('Component Tests', () => {

    describe('OnsiteProcess Management Delete Component', () => {
        let comp: OnsiteProcessDeleteDialogComponent;
        let fixture: ComponentFixture<OnsiteProcessDeleteDialogComponent>;
        let service: OnsiteProcessService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [IResearchTestModule],
                declarations: [OnsiteProcessDeleteDialogComponent],
                providers: [
                    OnsiteProcessService
                ]
            })
            .overrideTemplate(OnsiteProcessDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(OnsiteProcessDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OnsiteProcessService);
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
