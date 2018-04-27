/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { IResearchTestModule } from '../../../test.module';
import { AclComponent } from '../../../../../../main/webapp/app/entities/acl/acl.component';
import { AclService } from '../../../../../../main/webapp/app/entities/acl/acl.service';
import { Acl } from '../../../../../../main/webapp/app/entities/acl/acl.model';

describe('Component Tests', () => {

    describe('Acl Management Component', () => {
        let comp: AclComponent;
        let fixture: ComponentFixture<AclComponent>;
        let service: AclService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [IResearchTestModule],
                declarations: [AclComponent],
                providers: [
                    AclService
                ]
            })
            .overrideTemplate(AclComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(AclComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AclService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Acl(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.acls[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
