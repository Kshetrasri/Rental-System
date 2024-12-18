package com.crio.rentvideo.repositories;

import com.crio.rentvideo.models.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {
}