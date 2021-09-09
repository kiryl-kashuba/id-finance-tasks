package com.mm.task06.converter;

import com.mm.task06.db.tables.pojos.Work;
import com.mm.task06.model.WorkModel;

public interface WorkConverter {

  WorkModel convertToModel(Work work);

  Work convertFromModel(WorkModel workModel);
}
