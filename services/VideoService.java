package com.crio.rentvideo.services;

import com.crio.rentvideo.models.Video;
import com.crio.rentvideo.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoService {

    @Autowired
    private VideoRepository videoRepository;

    public List<Video> getAllVideos() {
        return videoRepository.findAll();  // Get all videos from the repository
    }

    public Video addVideo(Video video) {
        return videoRepository.save(video);  // Add a new video
    }

    public void deleteVideo(Long videoId) {
        videoRepository.deleteById(videoId);  // Delete a video by its ID
    }

    public Optional<Video> getVideoById(Long videoId) {
        return videoRepository.findById(videoId);  // Get video by ID
    }
}
