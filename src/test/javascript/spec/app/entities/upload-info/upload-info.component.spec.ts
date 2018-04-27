/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { IResearchTestModule } from '../../../test.module';
import { UploadInfoComponent } from '../../../../../../main/webapp/app/entities/upload-info/upload-info.component';
import { UploadInfoService } from '../../../../../../main/webapp/app/entities/upload-info/upload-info.service';
import { UploadInfo } from '../../../../../../main/webapp/app/entities/upload-info/upload-info.model';

describe('Component Tests', () => {

    describe('UploadInfo Management Component', () => {
        let comp: UploadInfoComponent;
        let fixture: ComponentFixture<UploadInfoComponent>;
        let service: UploadInfoService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [IResearchTestModule],
                declarations: [UploadInfoComponent],
                providers: [
                    UploadInfoService
                ]
            })
            .overrideTemplate(UploadInfoComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(UploadInfoComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UploadInfoService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new UploadInfo(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.uploadInfos[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
