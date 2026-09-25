import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CidadesListComponent } from './cidades-list.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('CidadesListComponent', () => {
  let component: CidadesListComponent;
  let fixture: ComponentFixture<CidadesListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CidadesListComponent, HttpClientTestingModule]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CidadesListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
