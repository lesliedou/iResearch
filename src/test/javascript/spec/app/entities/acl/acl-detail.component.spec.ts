/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { IResearchTestModule } from '../../../test.module';
import { AclDetailComponent } from '../../../../../../main/webapp/app/entities/acl/acl-detail.component';
import { AclService } from '../../../../../../main/webapp/app/entities/acl/acl.service';
import { Acl } from '../../../../../../main/webapp/app/entities/acl/acl.model';

describe('Component Tests', () => {

    describe('Acl Management Detail Component', () => {
        let comp: AclDetailComponent;
        let fixture: ComponentFixture<AclDetailComponent>;
        let service: AclService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [IResearchTestModule],
                declarations: [AclDetailComponent],
                providers: [
                    AclService
                ]
            })
            .overrideTemplate(AclDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(AclDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AclService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new Acl(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.acl).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
