package com.digitalhouse.obtenerdiploma.service;

import com.digitalhouse.obtenerdiploma.dto.StudentDTO;
import com.digitalhouse.obtenerdiploma.dto.CertificateDTO;

public interface CertificateService {
  public CertificateDTO analyzeNotes(StudentDTO house);
}

