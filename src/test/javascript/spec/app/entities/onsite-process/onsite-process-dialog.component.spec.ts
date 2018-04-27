/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { IResearchTestModule } from '../../../test.module';
import { OnsiteProcessDialogComponent } from '../../../../../../main/webapp/app/entities/onsite-process/onsite-process-dialog.component';
import { OnsiteProcessService } from '../../../../../../main/webapp/app/entities/onsite-process/onsite-process.service';
import { OnsiteProcess } from '../../../../../../main/webapp/app/entities/onsite-process/onsite-process.model';
import { OnsiteService } from '../../../../../../main/webapp/app/entities/onsite';

describe('Component Tests', () => {

    describe('OnsiteProcess Management Dialog Component', () => {
        let comp: OnsiteProcessDialogComponent;
        let fixture: ComponentFixture<OnsiteProcessDialogComponent>;
        let service: OnsiteProcessService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [IResearchTestModule],
                declarations: [OnsiteProcessDialogComponent],
                providers: [
                    OnsiteService,
                    OnsiteProcessService
                ]
            })
            .overrideTemplate(OnsiteProcessDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(OnsiteProcessDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OnsiteProcessService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new OnsiteProcess(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.onsiteProcess = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'onsiteProcessListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new OnsiteProcess();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.onsiteProcess = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'onsiteProcessListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
