package com.service;

import com.domain.Space;
import com.repository.SpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpaceService {

    @Autowired
    SpaceRepository spaceRepository;

    //save new space
    public boolean saveNewSpace(Space space){
        boolean successful = false;
        try{
            space.setId(null);
            space = spaceRepository.save(space);
            successful = true;
        }finally{

        }

        return successful;
    }

    //get all spaces
    public List<Space> getAllSpaces(){
        List<Space> spaceList = new ArrayList<>();
        spaceRepository.findAll().forEach(space -> spaceList.add(space));
        return spaceList;
    }

    //get space by id
    public Space getSpace(Integer id){
        return spaceRepository.findById(id).get();
    }

    //get all spaces of user
    public List<Space> getAllUsersSpaces(){
        Long userId = CommonService.getCurrentUserId();

        List<Space> spaceList = new ArrayList<>();
        spaceRepository.findByUserId(userId).forEach(space -> spaceList.add(space));
        return spaceList;
    }

    //update space
    public boolean updateSpace(Space space){
        boolean successful = false;
        try{
            space = spaceRepository.save(space);
            successful = true;
        }finally{

        }

        return successful;
    }

    //delete space
    public boolean deleteSpace(Integer id){
        boolean successful = false;

        try{
            //get space
            Space space = spaceRepository.findById(id).get();
            if(space != null && space.getUserId().equals(CommonService.getCurrentUserId())){
                spaceRepository.delete(space);
                successful = true;
            }

        }finally{

        }

        return successful;
    }

    //delete space of given category
    public boolean deleteSpaceByCategory(Integer categoryId){
        boolean successful = false;

        try{
            spaceRepository.deleteByCategoryId(categoryId);
            successful = true;

        }finally{

        }

        return successful;
    }
}
