//package com.app.music.service.ipml;
//
//import com.app.music.config.OSSConfig;
//import com.app.music.utils.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @Description: 公共业务具体实现类
// * @Author: junqiang.lu
// * @Date: 2018/12/24
// */
//@Service("commonService")
//public class CommonServiceImpl implements CommonService {
//
//    @Autowired
//    private OSSConfig ossConfig;
//
//    /**
//     * 上传文件至阿里云 oss
//     *
//     * @param file
//     * @param uploadKey
//     * @return
//     * @throws Exception
//     */
//    @Override
//    public Result uploadOSS(MultipartFile file, String uploadKey) throws Exception {
//
//        // 低依赖版本 oss 上传工具
//        String fileSuffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
//        ;
//        String ossFileUrlSingle = null;
//        ossFileUrlSingle = OSSSingleUtil.upload(ossConfig.getEndpoint(), ossConfig.getAccessKeyId(),
//                ossConfig.getAccessKeySecret(), ossConfig.getBucketName(), ossConfig.getUrl(), file.getInputStream(),
//                "upload/demo", fileSuffix);
//
//        // 高依赖版本 oss 上传工具
//        String ossFileUrlBoot = null;
//        ossFileUrlBoot = OSSBootUtil.upload(ossConfig, file, "upload/demo");
//
//        Map<String, Object> resultMap = new HashMap<>(16);
//        resultMap.put("ossFileUrlSingle", ossFileUrlSingle);
//        resultMap.put("ossFileUrlBoot", ossFileUrlBoot);
//
//        return CommonUtils.success(ResultCode.SUCCESS, resultMap);
//    }
//}
