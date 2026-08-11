import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AtivosListComponent } from './ativos-list.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { MessageService } from 'primeng/api';

describe('AtivosListComponent', () => {
  let component: AtivosListComponent;
  let fixture: ComponentFixture<AtivosListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AtivosListComponent, HttpClientTestingModule],
      providers: [MessageService]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AtivosListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
