import { TestBed } from '@angular/core/testing';

import { AtleticaService } from './atletica.service';

describe('AtleticaService', () => {
  let service: AtleticaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AtleticaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
