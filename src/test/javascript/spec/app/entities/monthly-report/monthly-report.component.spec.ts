/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { IResearchTestModule } from '../../../test.module';
import { MonthlyReportComponent } from '../../../../../../main/webapp/app/entities/monthly-report/monthly-report.component';
import { MonthlyReportService } from '../../../../../../main/webapp/app/entities/monthly-report/monthly-report.service';
import { MonthlyReport } from '../../../../../../main/webapp/app/entities/monthly-report/monthly-report.model';

describe('Component Tests', () => {

    describe('MonthlyReport Management Component', () => {
        let comp: MonthlyReportComponent;
        let fixture: ComponentFixture<MonthlyReportComponent>;
        let service: MonthlyReportService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [IResearchTestModule],
                declarations: [MonthlyReportComponent],
                providers: [
                    MonthlyReportService
                ]
            })
            .overrideTemplate(MonthlyReportComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(MonthlyReportComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MonthlyReportService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new MonthlyReport(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.monthlyReports[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
