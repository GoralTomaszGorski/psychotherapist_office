package goral.psychotherapistoffice.domain.calender;


import goral.psychotherapistoffice.domain.calender.dto.CalenderDto;
import org.springframework.stereotype.Service;

import java.util.List;

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







/*    @Transactional
    public void releaseCalender(Long calenderId){
        updateCalenderReleaseTherm(calenderId, false);
    }

    public Calender updateCalenderReleaseTherm(Long calenderId, Boolean free){
        Calender calender = calenderRepository
                .findById(calenderId)
                .orElseThrow(CalenderNotFoundException::new);
        calender.setFree(free);
        return calender;
    }

    @Transactional
    public void reserveCalender(Long calenderId){
        updateCalenderReserveTherm(calenderId, true);
    }

    public Calender updateCalenderReserveTherm(Long calenderId, Boolean free){
        Calender calender = calenderRepository
                .findById(calenderId)
                .orElseThrow(CalenderNotFoundException::new);
        calender.setFree(free);
        return calender;
    }*/

}
