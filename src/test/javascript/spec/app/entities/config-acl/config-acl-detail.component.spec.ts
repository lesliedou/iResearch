/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { IResearchTestModule } from '../../../test.module';
import { ConfigAclDetailComponent } from '../../../../../../main/webapp/app/entities/config-acl/config-acl-detail.component';
import { ConfigAclService } from '../../../../../../main/webapp/app/entities/config-acl/config-acl.service';
import { ConfigAcl } from '../../../../../../main/webapp/app/entities/config-acl/config-acl.model';

describe('Component Tests', () => {

    describe('ConfigAcl Management Detail Component', () => {
        let comp: ConfigAclDetailComponent;
        let fixture: ComponentFixture<ConfigAclDetailComponent>;
        let service: ConfigAclService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [IResearchTestModule],
                declarations: [ConfigAclDetailComponent],
                providers: [
                    ConfigAclService
                ]
            })
            .overrideTemplate(ConfigAclDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ConfigAclDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ConfigAclService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new ConfigAcl(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.configAcl).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
