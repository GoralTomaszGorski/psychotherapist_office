package goral.psychotherapistoffice.domain.calender;


import goral.psychotherapistoffice.domain.calender.dto.CalenderDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CalenderService {
    public CalenderRepository calenderRepository;

    public CalenderService(CalenderRepository calenderRepository) {
        this.calenderRepository = calenderRepository;
    }

    public List<CalenderDto>findAllFreeTherms(){
        return calenderRepository.findAllByFreeIsTrueOrderById().stream()
                .map(CalenderDtoMapper::map).toList();
    }

    public List<CalenderDto>findAllTherms(){
        return calenderRepository.findAll()
                .stream()
                .map(CalenderDtoMapper::map).toList();
    }

    public Optional<CalenderDto> findCalenderById(long id){
        return calenderRepository.findById(id)
                .map(CalenderDtoMapper::map);
    }


    public Optional<CalenderDto> findCalByIdIsFree(long id){
        return calenderRepository.findCalenderByIdAndFreeIsTrue(id)
                .map(CalenderDtoMapper::map);
    }



}
