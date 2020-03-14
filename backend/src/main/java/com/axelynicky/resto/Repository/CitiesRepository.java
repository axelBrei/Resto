package com.axelynicky.resto.Repository;

import com.axelynicky.resto.Domain.City;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CompletableFuture;


public interface CitiesRepository  extends CrudRepository<City,String> {

}
