import { ComponentFixture, TestBed } from '@angular/core/testing';
import { TiposAtivoListComponent } from './tipos-ativo-list.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('TiposAtivoListComponent', () => {
  let component: TiposAtivoListComponent;
  let fixture: ComponentFixture<TiposAtivoListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TiposAtivoListComponent, HttpClientTestingModule]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TiposAtivoListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
