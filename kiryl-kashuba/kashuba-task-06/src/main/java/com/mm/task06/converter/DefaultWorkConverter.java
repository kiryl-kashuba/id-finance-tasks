package com.mm.task06.converter;

import com.mm.task06.db.tables.pojos.Work;
import com.mm.task06.model.WorkModel;

public class DefaultWorkConverter implements WorkConverter {

  @Override
  public WorkModel convertToModel(Work work) {
    return new WorkModel(
        work.getId(),
        work.getSalary(),
        work.getEducation(),
        work.getNextIncomeDate());
  }

  @Override
  public Work convertFromModel(WorkModel workModel) {
    return new Work(
        workModel.getId(),
        workModel.getSalary(),
        workModel.getEducation(),
        workModel.getNextIncomeDate());
  }
}
