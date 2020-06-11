package com.example.demo.repositories;

import com.example.demo.entities.Tier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TierRepo extends CrudRepository<Tier, Integer> {

    List<Tier> findAllByScreenId(Integer screenId);

}
