import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserActionBarComponent } from './user-action-bar.component';

describe('UserActionBarComponent', () => {
  let component: UserActionBarComponent;
  let fixture: ComponentFixture<UserActionBarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserActionBarComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserActionBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
