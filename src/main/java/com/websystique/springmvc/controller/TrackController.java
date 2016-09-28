package com.websystique.springmvc.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.websystique.springmvc.model.Track;
import com.websystique.springmvc.service.TrackService;

@RestController
public class TrackController {

	private static Log LOG = LogFactory.getLog(TrackController.class);	
	
	@Autowired
	private TrackService trackService;

	@RequestMapping(value = "/track/", method = RequestMethod.GET)
    public ResponseEntity<List<Track>> listAllUsers() {
        List<Track> tracks = trackService.getAllTracks();

        LOG.info(tracks.size() + " tracks available: " + tracks);
        
        if (tracks.isEmpty()){
            return new ResponseEntity<List<Track>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }

        return new ResponseEntity<List<Track>>(tracks, HttpStatus.OK);
    }
}
