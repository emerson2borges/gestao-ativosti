import { ComponentFixture, TestBed } from '@angular/core/testing';
import { SubativosListComponent } from './subativos-list.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { MessageService } from 'primeng/api';

describe('SubativosListComponent', () => {
  let component: SubativosListComponent;
  let fixture: ComponentFixture<SubativosListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SubativosListComponent, HttpClientTestingModule],
      providers: [MessageService]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SubativosListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
