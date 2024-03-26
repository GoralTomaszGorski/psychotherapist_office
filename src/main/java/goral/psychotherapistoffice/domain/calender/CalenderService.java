package goral.psychotherapistoffice.domain.calender;


import goral.psychotherapistoffice.domain.calender.dto.CalenderDto;
import goral.psychotherapistoffice.domain.exception.DeleteCalenderException;
import goral.psychotherapistoffice.domain.meeting.MeetingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CalenderService {
    private final CalenderRepository calenderRepository;
    private final MeetingRepository meetingRepository;

    public CalenderService(CalenderRepository calenderRepository, MeetingRepository meetingRepository) {
        this.calenderRepository = calenderRepository;
        this.meetingRepository = meetingRepository;
    }

    public List<CalenderDto>findAllFreeTherms(){
        return calenderRepository.findAllByFreeIsTrue()
                .stream()
                .map(CalenderDtoMapper::map)
                .toList();
    }

    public List<CalenderDto>findAllTherms(){
        return calenderRepository.findAll()
                .stream()
                .map(CalenderDtoMapper::map)
                .toList();
    }

    public Optional<CalenderDto> findFreeCalenderById(long id){
        return calenderRepository.findCalenderByIdAndFreeIsTrue(id)
                .map(CalenderDtoMapper::map);
    }
    public Optional<CalenderDto> findCalenderById(long id){
        return calenderRepository
                .findById(id)
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
    public void editCalender(CalenderDto calenderDto){
        Calender calenderToSave = new Calender();
        calenderToSave.setId(calenderDto.getId());
        calenderToSave.setDayof(calenderDto.getDayof());
        calenderToSave.setTime(calenderDto.getTime());
        if (meetingRepository.findMeetingsByCalender_Id(calenderDto.getId()) == null){
            calenderToSave.setFree(true);
        } else {
            calenderToSave.setFree(false);
        }
        calenderRepository.save(calenderToSave);
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
