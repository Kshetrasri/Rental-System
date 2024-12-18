package com.crio.rentvideo.controllers;

import com.crio.rentvideo.models.Video;
import com.crio.rentvideo.models.User;
import com.crio.rentvideo.services.VideoService;
import com.crio.rentvideo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/videos")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @Autowired
    private UserService userService;

    @PostMapping("/{videoId}/rent")
    public ResponseEntity<String> rentVideo(@PathVariable Long videoId, @RequestHeader("Authorization") String token) {
        User user = userService.getUserFromToken(token);  // Extract user from JWT token
        
        if (user.getRentals().size() >= 2) {
            return ResponseEntity.status(400).body("Cannot rent more than two videos");
        }

        Video video = videoService.getVideoById(videoId).orElseThrow(() -> new RuntimeException("Video not found"));
        video.setAvailable(false);  // Set video as rented
        videoService.addVideo(video);  // Save the video

        user.getRentals().add(video);
        userService.saveUser(user);

        return ResponseEntity.ok("Video rented successfully");
    }

    @PostMapping("/{videoId}/return")
    public ResponseEntity<String> returnVideo(@PathVariable Long videoId, @RequestHeader("Authorization") String token) {
        User user = userService.getUserFromToken(token);  // Extract user from JWT token

        Video video = videoService.getVideoById(videoId).orElseThrow(() -> new RuntimeException("Video not found"));
        video.setAvailable(true);  // Mark video as available again
        videoService.addVideo(video);  // Save the video

        user.getRentals().remove(video);  // Remove rental from user
        userService.saveUser(user);

        return ResponseEntity.ok("Video returned successfully");
    }
}
