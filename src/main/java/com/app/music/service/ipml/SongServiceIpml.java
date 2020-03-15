package com.app.music.service.ipml;

import com.app.music.dao.ISongDao;
import com.app.music.entity.Song;
import com.app.music.entity.User;
import com.app.music.service.ISongService;
import com.app.music.utils.*;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Enumeration;
import java.util.List;

@Service
public class SongServiceIpml implements ISongService {
    @Autowired
    private ISongDao songDao;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    LoadingCache loadingCache;
    @Autowired
    TokenUtil tokenUtil;
    @Value("${music.filePath}")
    String filePath;

    @Override
    public Result insert(Song song) throws IOException {
        System.out.println(filePath);
        String originalFilename = song.getFile().getOriginalFilename();
        String regex = "\\.";
        String[] split = originalFilename.split(regex);
        String fileName = song.getSongName() + "." + split[1];
        try {
            CommonUtils.uploadFile(song.getFile().getBytes(), filePath, fileName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        song.setSongUrl(filePath + "/" + fileName);
        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = sdf.format(new Date());
        song.setCreateTime(new Date());*/
        Boolean aBoolean = songDao.insert(song);
        if (aBoolean) {
            return CommonUtils.success(ResultCode.SUCCESS, null);
        } else {
            return CommonUtils.failed(ResultCode.NETWORK_ERROR);
        }

    }

    @Override
    public Result delete(int id) {
        User user = tokenUtil.getUser();
        // 如果当前用户不是admin不能删除
        if (!"admin".equals(user.getUserCode())) {
            return CommonUtils.failed(ResultCode.PERMISSIONS_ERROR);
        }
        Song song = songDao.queryById(id);
        String s = CommonUtils.deleteFile(song.getSongUrl());
        logger.info(s);
        Boolean delete = songDao.delete(id);

        return delete ? CommonUtils.success(ResultCode.SUCCESS, null)
                : CommonUtils.failed(ResultCode.NETWORK_ERROR);
    }

    @Override
    public Result update(Song song) {
        Song _song = songDao.queryById(song.getId());
        if (_song != null) {
            Boolean update = songDao.update(song);
            return update ? CommonUtils.success(ResultCode.SUCCESS, null)
                    : CommonUtils.failed(ResultCode.NETWORK_ERROR);
        } else return CommonUtils.failed(ResultCode.SONG_ERROR);
    }

    @Override
    public Result query() {
        List query = songDao.query();
        return CommonUtils.success(ResultCode.SUCCESS, query);
    }

    @Override
    public Result queryById(int id) {
        Song song = songDao.queryById(id);
        return CommonUtils.success(ResultCode.SUCCESS, song);
    }

    @Override
    public Result deleteByBatch(String[] arr) {
        User user = tokenUtil.getUser();
        // 如果当前用户不是admin不能删除
        if (!"admin".equals(user.getUserCode())) {
            return CommonUtils.failed(ResultCode.PERMISSIONS_ERROR);
        }
        songDao.deleteByBatch(arr);
        return CommonUtils.success(ResultCode.SUCCESS, null);
    }

    @Override
    public void getDownload(int songId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Song song = songDao.queryById(songId);
        String fullPath = song.getSongUrl();
        File downloadFile = new File(fullPath);
        String extName = "";
        // 文件不存在
        if (!downloadFile.exists()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        long fileLength = downloadFile.length();// 记录文件大小
        long pastLength = 0;// 记录已下载文件大小
        int rangeSwitch = 0;// 0：从头开始的全文下载；1：从某字节开始的下载（bytes=27000-）；2：从某字节开始到某字节结束的下载（bytes=27000-39000）
        long toLength = 0;// 记录客户端需要下载的字节段的最后一个字节偏移量（比如bytes=27000-39000，则这个值是为39000）
        long contentLength = 0;// 客户端请求的字节总量
        String rangeBytes = "";// 记录客户端传来的形如“bytes=27000-”或者“bytes=27000-39000”的内容
        RandomAccessFile raf = null;// 负责读取数据
        OutputStream os = null;// 写出数据
        OutputStream out = null;// 缓冲
        int bsize = 1024;// 缓冲区大小
        byte b[] = new byte[bsize];// 暂存容器

        if (request.getParameter("showheader") != null) {
            Enumeration paramNames = request.getHeaderNames();

            while (paramNames.hasMoreElements()) {
                String name = paramNames.nextElement().toString();
                if (name != null && name.length() > 0) {
                    String value = request.getHeader(name);
                    logger.info("************" + name + "：" + value);
                }
            }
        }
        String range = request.getHeader("Range");
        // if(range == null)
        // range = "bytes=0-";
        int responseStatus = 206;
        if (range != null && range.trim().length() > 0 && !"null".equals(range)) {// 客户端请求的下载的文件块的开始字节
            responseStatus = javax.servlet.http.HttpServletResponse.SC_PARTIAL_CONTENT;
            logger.info("request.getHeader(\"Range\")=" + range);
            rangeBytes = range.replaceAll("bytes=", "");
            if (rangeBytes.endsWith("-")) {// bytes=969998336-
                rangeSwitch = 1;
                rangeBytes = rangeBytes.substring(0, rangeBytes.indexOf('-'));
                pastLength = Long.parseLong(rangeBytes.trim());
                contentLength = fileLength - pastLength;// 客户端请求的是
                // 969998336之后的字节（包括bytes下标索引为969998336的字节）
            } else {// bytes=1275856879-1275877358
                rangeSwitch = 2;
                String temp0 = rangeBytes.substring(0, rangeBytes.indexOf('-'));
                String temp2 = rangeBytes.substring(rangeBytes.indexOf('-') + 1, rangeBytes.length());
                // bytes=1275856879-1275877358，从第1275856879个字节开始下载
                pastLength = Long.parseLong(temp0.trim());
                toLength = Long.parseLong(temp2);// bytes=1275856879-1275877358，到第
                // 1275877358 个字节结束
                contentLength = toLength - pastLength + 1;// 客户端请求的是
                // 1275856879-1275877358
                // 之间的字节
            }
        } else {// 从开始进行下载
            contentLength = fileLength;// 客户端要求全文下载
        }

        /**
         * 如果设设置了Content-Length，则客户端会自动进行多线程下载。如果不希望支持多线程，则不要设置这个参数。 响应的格式是:
         * Content-Length: [文件的总大小] - [客户端请求的下载的文件块的开始字节]
         * ServletActionContext.getResponse().setHeader("Content-Length", new
         * Long(file.length() - p).toString());
         */
        // 来清除首部的空白行
        response.reset();
        // 告诉客户端允许断点续传多线程连接下载,响应的格式是:Accept-Ranges: bytes
        response.setHeader("Accept-Ranges", "bytes");
        // 如果是第一次下,还没有断点续传,状态是默认的 200,无需显式设置;响应的格式是:HTTP/1.1

        // response.addHeader("Cache-Control", "max-age=1296000");
        // response.addHeader("Expires", "Fri, 12 Oct 2012 03:43:01 GMT");
        // response.addHeader("Last-Modified", "Tue, 31 Jul 2012 03:58:36 GMT");
        // response.addHeader("Connection", "keep-alive");
        // response.addHeader("ETag", downloadFile.getName() + "-" +
        // downloadFile.lastModified());
        // response.addHeader("Last-Modified", "Thu, 27 Sep 2012 05:24:44 GMT");
        if (rangeSwitch != 0) {
            response.setStatus(responseStatus);
            // 不是从最开始下载，断点下载响应号为206
            // 响应的格式是:
            // Content-Range: bytes [文件块的开始字节]-[文件的总大小 - 1]/[文件的总大小]
            logger.info("----------------------------片段下载，服务器即将开始断点续传...");
            switch (rangeSwitch) {
                case 1: {// 针对 bytes=27000- 的请求
                    String contentRange = new StringBuffer("bytes ")
                            .append(new Long(pastLength).toString()).append("-")
                            .append(new Long(fileLength - 1).toString())
                            .append("/").append(new Long(fileLength).toString())
                            .toString();
                    response.setHeader("Content-Range", contentRange);
                    break;
                }
                case 2: {// 针对 bytes=27000-39000 的请求
                    String contentRange = range.replace("=", " ") + "/"
                            + new Long(fileLength).toString();
                    response.setHeader("Content-Range", contentRange);
                    break;
                }
                default: {
                    break;
                }
            }
        } else {
            String contentRange = new StringBuffer("bytes ").append("0-")
                    .append(fileLength - 1).append("/").append(fileLength)
                    .toString();
            response.setHeader("Content-Range", contentRange);
            // 是从开始下载
            logger.info("----------------------------是从开始到最后完整下载！");
        }

        try {
            // /////////////////////////设置文件Content-Type///////////////////////////

            String contentType = null;

            if (contentType != null) {
                // /logger.debug("设置内容类型:" + contentType);
                response.setContentType(contentType);// set the MIME type.
            } else {
                response.setContentType("audio/mpeg");// set the
                // MIME
                // type.
            }
            // /////////////////////////设置文件下载名称Content-Disposition///////////////////////////
//            if("bytes=0-1".equals(range)){
//                response.reset();
//
//                response.setStatus(javax.servlet.http.HttpServletResponse.SC_NOT_MODIFIED);
//            }
            response.setHeader("Content-Length", String.valueOf(contentLength));
            os = response.getOutputStream();
            out = new BufferedOutputStream(os);
            raf = new RandomAccessFile(downloadFile, "r");
            try {
                long outLength = 0;// 实际输出字节数
                switch (rangeSwitch) {
                    case 0: {// 普通下载，或者从头开始的下载
                        // 同1，没有break
                    }
                    case 1: {// 针对 bytes=27000- 的请求
                        raf.seek(pastLength);// 形如 bytes=969998336- 的客户端请求，跳过
                        // 969998336 个字节
                        int n = 0;
                        while ((n = raf.read(b)) != -1) {
                            out.write(b, 0, n);
                            outLength += n;
                        }
                        // while ((n = raf.read(b, 0, 1024)) != -1) {
                        // out.write(b, 0, n);
                        // }
                        break;
                    }
                    case 2: {
                        // 针对 bytes=27000-39000 的请求，从27000开始写数据
                        raf.seek(pastLength);
                        int n = 0;
                        long readLength = 0;// 记录已读字节数
                        while (readLength <= contentLength - bsize) {// 大部分字节在这里读取
                            n = raf.read(b);
                            readLength += n;
                            out.write(b, 0, n);
                            outLength += n;
                        }
                        if (readLength <= contentLength) {// 余下的不足 1024 个字节在这里读取
                            n = raf.read(b, 0, (int) (contentLength - readLength));
                            out.write(b, 0, n);
                            outLength += n;
                        }
                        break;
                    }
                    default: {
                        break;
                    }
                }
                logger.info("Content-Length为：" + contentLength + "；实际输出字节数：" + outLength);
                out.flush();
            } catch (IOException ie) {
                /**
                 * 在写数据的时候， 对于 ClientAbortException 之类的异常，
                 * 是因为客户端取消了下载，而服务器端继续向浏览器写入数据时， 抛出这个异常，这个是正常的。
                 * 尤其是对于迅雷这种吸血的客户端软件， 明明已经有一个线程在读取 bytes=1275856879-1275877358，
                 * 如果短时间内没有读取完毕，迅雷会再启第二个、第三个。。。线程来读取相同的字节段， 直到有一个线程读取完毕，迅雷会 KILL
                 * 掉其他正在下载同一字节段的线程， 强行中止字节读出，造成服务器抛 ClientAbortException。
                 * 所以，我们忽略这种异常
                 */
                // ignore
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    logger.info(e.getMessage());
                }
            }
            if (raf != null) {
                try {
                    raf.close();
                } catch (IOException e) {
                    logger.info(e.getMessage());
                }
            }
        }
    }
}
