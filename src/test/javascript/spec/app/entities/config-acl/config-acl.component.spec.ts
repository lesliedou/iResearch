/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { IResearchTestModule } from '../../../test.module';
import { ConfigAclComponent } from '../../../../../../main/webapp/app/entities/config-acl/config-acl.component';
import { ConfigAclService } from '../../../../../../main/webapp/app/entities/config-acl/config-acl.service';
import { ConfigAcl } from '../../../../../../main/webapp/app/entities/config-acl/config-acl.model';

describe('Component Tests', () => {

    describe('ConfigAcl Management Component', () => {
        let comp: ConfigAclComponent;
        let fixture: ComponentFixture<ConfigAclComponent>;
        let service: ConfigAclService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [IResearchTestModule],
                declarations: [ConfigAclComponent],
                providers: [
                    ConfigAclService
                ]
            })
            .overrideTemplate(ConfigAclComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ConfigAclComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ConfigAclService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new ConfigAcl(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.configAcls[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
