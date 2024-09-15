package goral.psychotherapistoffice.domain.calender;

import goral.psychotherapistoffice.domain.calender.dto.CalenderDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;

class CalenderServiceTest {

    @Mock CalenderRepository calenderRepository;
    @InjectMocks CalenderService calenderService;


    @BeforeEach
            void setUp(){
                MockitoAnnotations.openMocks(this);
        CalenderDto calenderDto1 = new CalenderDto(
                1l, "Poniedziałek", "17.30", true
        );
        CalenderDto calenderDto2 = new CalenderDto(
                3l, "Wtorek", "08:30", false
        );
        calenderService.addCalender(calenderDto1);
        calenderService.addCalender(calenderDto2);
    }

    @Test
    void shouldAddThermToCalender() {
        //given
        CalenderDto calenderDto = new CalenderDto(
                1l, "Poniedziałek", "17.30", true
        );
        //when
        calenderService.addCalender(calenderDto);
        //then
        assert(calenderDto.getId()).equals(1l);
        assert(calenderDto.getDayof()).equals("Poniedziałek");
        assert(calenderDto.getTime()).equals("17.30");
        assert(calenderDto.isFree());
    }

    @Test
    void findAllFreeTherms() {

    }

    @Test
    void findAllTherms() {
    }

    @Test
    void findFreeCalenderById() {
    }

    @Test
    void findCalenderById() {
    }



    @Test
    void editCalender() {

    }

    @Test
    void deleteCalender() {
    }
}