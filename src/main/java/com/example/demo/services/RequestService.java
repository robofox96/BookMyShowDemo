package com.example.demo.services;

import com.example.demo.apimodels.ScreenLayoutModel;
import com.example.demo.entities.Screen;
import com.example.demo.entities.Seat;
import com.example.demo.entities.Tier;
import com.example.demo.repositories.ScreenRepo;
import com.example.demo.repositories.SeatRepo;
import com.example.demo.repositories.TierRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class RequestService {

    @Autowired
    ScreenRepo screenRepo;

    @Autowired
    SeatRepo seatRepo;

    @Autowired
    TierRepo tierRepo;

    public ScreenLayoutModel getScreenLayout(Integer screenId, Integer theaterId){
        Screen screen = screenRepo.findById(screenId).orElse(null);
        if(Objects.isNull(screen))
            throw new RuntimeException("Invalid Screen Id");
        List<Tier> tierList = tierRepo.findAllByScreenId(screenId);
        List<ScreenLayoutModel.TierLayout> tierLayoutList = new ArrayList<>();
        for(Tier tier : tierList){
            List<Seat> seatListForTier = seatRepo.findAllByScreenIdAndTierId(screenId,tier.getId());
            ScreenLayoutModel.TierLayout tierLayout = new ScreenLayoutModel.TierLayout(tier,seatListForTier);
            tierLayoutList.add(tierLayout);
        }
        ScreenLayoutModel screenLayoutModel = new ScreenLayoutModel(screen.getId(),tierLayoutList,screen.getType());
        return screenLayoutModel;
    }

}
