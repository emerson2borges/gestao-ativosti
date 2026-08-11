import { ComponentFixture, TestBed } from '@angular/core/testing';
import { EstoqueListComponent } from './estoque-list.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('EstoqueListComponent', () => {
  let component: EstoqueListComponent;
  let fixture: ComponentFixture<EstoqueListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EstoqueListComponent, HttpClientTestingModule]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EstoqueListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
