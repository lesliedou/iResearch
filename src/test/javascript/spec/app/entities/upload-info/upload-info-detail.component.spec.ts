/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { IResearchTestModule } from '../../../test.module';
import { UploadInfoDetailComponent } from '../../../../../../main/webapp/app/entities/upload-info/upload-info-detail.component';
import { UploadInfoService } from '../../../../../../main/webapp/app/entities/upload-info/upload-info.service';
import { UploadInfo } from '../../../../../../main/webapp/app/entities/upload-info/upload-info.model';

describe('Component Tests', () => {

    describe('UploadInfo Management Detail Component', () => {
        let comp: UploadInfoDetailComponent;
        let fixture: ComponentFixture<UploadInfoDetailComponent>;
        let service: UploadInfoService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [IResearchTestModule],
                declarations: [UploadInfoDetailComponent],
                providers: [
                    UploadInfoService
                ]
            })
            .overrideTemplate(UploadInfoDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(UploadInfoDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UploadInfoService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new UploadInfo(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.uploadInfo).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
