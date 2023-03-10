package com.yali.vilivili.service.impl;

import com.yali.vilivili.model.entity.VideosEntity;
import com.yali.vilivili.repository.VideosRepository;
import com.yali.vilivili.service.VideosService;
import com.yali.vilivili.utils.MyException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.util.List;

/**
 * 视频接口
 *
 * @author fuqianlin
 * @date 2023-01-25 15:31
 **/

@Service
public class VideosServiceImpl implements VideosService {
    @Resource
    private VideosRepository videosRepository;


    @Value("${server.port}")
    private String port;

    @Value("${file.upload.context-path}")
    private String contextPath;

    /**
     * 获取视频列表
     *
     * @return 视频列表
     */
    @Override
    public List<VideosEntity> getVideosList() {

        try {
            String url = "http://" + InetAddress.getLocalHost().getHostAddress() + ":" + port + contextPath + "/";
            List<VideosEntity> videosList = videosRepository.findAll();
            for (VideosEntity video : videosList) {
                if (!video.getVideosAddress().contains(url) && !video.getVideosCover().contains(url)) {
                    video.setVideosAddress(url + video.getVideosAddress());
                    video.setVideosCover(url + video.getVideosCover());
                }
            }
            return videosList;

        } catch (Exception e) {
            throw new MyException("获取视频列表失败", String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
    }

    /**
     * 分页查询视频列表
     *
     * @param page   页码
     * @param size   每页数量
     * @param status 状态
     * @return videoList 视频列表
     */
    @Override
    public List<VideosEntity> getVideosListByPage(Integer page, Integer size, Integer status) {
        // 计算分页起始位置
        page = (page - 1) * size;
        try {
            String url = "http://" + InetAddress.getLocalHost().getHostAddress() + ":" + port + contextPath + "/";
            List<VideosEntity> videosList = videosRepository.findAllByPage(page, size, status);
            for (VideosEntity video : videosList) {
                if (!video.getVideosAddress().contains(url) && !video.getVideosCover().contains(url)) {
                    video.setVideosAddress(url + video.getVideosAddress());
                    video.setVideosCover(url + video.getVideosCover());
                }
            }
            return videosList;

        } catch (Exception e) {
            throw new MyException("获取视频列表失败", String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
    }
}
