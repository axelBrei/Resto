package com.axelynicky.resto.WebModel;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor()
@Getter
public class ListContainer<T> {

    @NonNull
    private List<T> result;
}
