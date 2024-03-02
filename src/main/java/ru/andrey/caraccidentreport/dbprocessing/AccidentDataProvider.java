package ru.andrey.caraccidentreport.dbprocessing;

import ru.andrey.caraccidentreport.model.LimitedAccidentData;

import java.util.List;

public interface AccidentDataProvider<T, K> {

    K getAccidentData(T t);

}
