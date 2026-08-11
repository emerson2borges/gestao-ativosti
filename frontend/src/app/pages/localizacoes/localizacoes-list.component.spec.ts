import { ComponentFixture, TestBed } from '@angular/core/testing';
import { LocalizacoesListComponent } from './localizacoes-list.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('LocalizacoesListComponent', () => {
  let component: LocalizacoesListComponent;
  let fixture: ComponentFixture<LocalizacoesListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LocalizacoesListComponent, HttpClientTestingModule]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(LocalizacoesListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
