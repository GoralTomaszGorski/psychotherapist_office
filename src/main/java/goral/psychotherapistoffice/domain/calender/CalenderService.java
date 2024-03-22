package goral.psychotherapistoffice.domain.calender;


import goral.psychotherapistoffice.domain.calender.dto.CalenderDto;
import goral.psychotherapistoffice.domain.exception.CalenderNotFoundException;
import goral.psychotherapistoffice.domain.exception.DeleteCalenderException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CalenderService {
    private final CalenderRepository calenderRepository;
    public CalenderService(CalenderRepository calenderRepository) {
        this.calenderRepository = calenderRepository;
    }


    public List<CalenderDto>findAllFreeTherms(){
        return calenderRepository.findAllByFreeIsTrue().stream()
                .map(CalenderDtoMapper::map).toList();
    }

    public List<CalenderDto>findAllTherms(){
        return calenderRepository.findAll()
                .stream()
                .map(CalenderDtoMapper::map).toList();
    }

    public Optional<CalenderDto> findFreeCalenderById(long id){
        return calenderRepository.findCalenderByIdAndFreeIsTrue(id)
                .map(CalenderDtoMapper::map);
    }
    public  Optional<CalenderDto> findCalenderById(long id){
        return calenderRepository.findById(id)
                .map(CalenderDtoMapper::map);
    }

    @Transactional
    public void addCalender(CalenderDto calenderDto){
        Calender calenderToSave = new Calender();
        calenderToSave.setId(calenderDto.getId());
        calenderToSave.setDayof(calenderDto.getDayof());
        calenderToSave.setTime(calenderDto.getTime());
        calenderToSave.setFree(true);
        calenderRepository.save(calenderToSave);
    }

    @Transactional
    public void editCalender(Long id, CalenderDto calenderDto){
        try {
            findCalenderById(id);
            Calender calenderToSave = new Calender();
            calenderToSave.setId(calenderDto.getId());
            calenderToSave.setDayof(calenderDto.getDayof());
            calenderToSave.setTime(calenderDto.getTime());
            calenderRepository.save(calenderToSave);
        } catch (Throwable e){
            throw new CalenderNotFoundException(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public void deleteCalender(Long id) {
            try {
                calenderRepository.deleteById(id);
            } catch (Throwable e) {
                throw new DeleteCalenderException();
            }
    }

}
