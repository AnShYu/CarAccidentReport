package ru.andrey.caraccidentreport.util;

public interface DTOMapper<T, K> {
    T convertToDTO (K k);
    K convertFromDTO(T t);
}
