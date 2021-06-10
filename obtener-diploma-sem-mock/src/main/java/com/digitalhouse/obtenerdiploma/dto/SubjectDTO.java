package com.digitalhouse.obtenerdiploma.dto;

public class SubjectDTO {
  private String subject;
  private Integer note;

  public SubjectDTO(String subject, Integer note) {
    this.subject = subject;
    this.note = note;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public Integer getNote() {
    return note;
  }

  public void setNote(Integer note) {
    this.note = note;
  }

}
