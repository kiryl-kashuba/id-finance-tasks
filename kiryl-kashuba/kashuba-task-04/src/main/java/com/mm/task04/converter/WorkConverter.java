package com.mm.task04.converter;

import com.mm.task04.db.tables.pojos.Work;
import com.mm.task04.model.WorkModel;

public interface WorkConverter {

  WorkModel convertToModel(Work work);

  Work convertFromModel(WorkModel workModel);
}
