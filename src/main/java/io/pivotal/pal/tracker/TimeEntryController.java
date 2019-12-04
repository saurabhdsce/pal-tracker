package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TimeEntryController {
    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping("/time-entries")
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry timeEntry = timeEntryRepository.create(timeEntryToCreate);
        ResponseEntity responseEntity = new ResponseEntity(timeEntry, HttpStatus.CREATED);
        return responseEntity;

    }
    @GetMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {
        TimeEntry timeEntry = timeEntryRepository.find(timeEntryId);
        ResponseEntity responseEntity;
        if(timeEntry!= null)
             responseEntity = new ResponseEntity(timeEntry, HttpStatus.OK);
        else
            responseEntity = new ResponseEntity(timeEntry, HttpStatus.NOT_FOUND);

        System.out.println("timeEntry " + timeEntry);
        return responseEntity;
    }
    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {

        ResponseEntity responseEntity = new ResponseEntity(timeEntryRepository.list(),HttpStatus.OK);

        return responseEntity;
    }
    @PutMapping("/time-entries/{timeEntryId}")
    public ResponseEntity update(@PathVariable long timeEntryId, @RequestBody TimeEntry expected) {
        TimeEntry timeEntry = timeEntryRepository.update(timeEntryId,expected);
        ResponseEntity responseEntity;
        if(timeEntry!=null)
            responseEntity= new ResponseEntity(timeEntry,HttpStatus.OK);
        else
            responseEntity= new ResponseEntity(timeEntry,HttpStatus.NOT_FOUND);

        return responseEntity;
    }

    @DeleteMapping("/time-entries/{timeEntryId}")
    public ResponseEntity delete(@PathVariable long timeEntryId) {
        timeEntryRepository.delete(timeEntryId);
        ResponseEntity responseEntity = new ResponseEntity( HttpStatus.NO_CONTENT);

        return responseEntity;
    }
}
