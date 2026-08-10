import { ComponentFixture, TestBed } from '@angular/core/testing';
import { OrdensCompraListComponent } from './ordens-compra-list.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('OrdensCompraListComponent', () => {
  let component: OrdensCompraListComponent;
  let fixture: ComponentFixture<OrdensCompraListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OrdensCompraListComponent, HttpClientTestingModule]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(OrdensCompraListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
