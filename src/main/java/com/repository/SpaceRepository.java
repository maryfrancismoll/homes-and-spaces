package com.repository;

import com.domain.Space;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SpaceRepository extends CrudRepository<Space, Integer> {

    List<Space> findByUserId(Long userId);

    void deleteByCategoryId(Integer categoryId);

    List<Space> findByCategoryId(Integer categoryId);

    List<Space> findByIdNotIn(List<Integer> ids);

    List<Space> findByIdNotInAndCategoryId(List<Integer> ids, Integer categoryId);
}
