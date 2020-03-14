## 音乐后台管理系统

---
> 1. 前端 angular6 ng-zorro
> 2. 后台 java spring-boot maven MyBatis mysql caffeine
> 3. 实现一个web音乐网管系统

---

> 1. 目前实现的功能
- 登录 
- 注册
- 歌手管理
- 歌单管理
- 歌曲管理
- 用户管理
- 歌曲播放
- 快进快退拖拽
> 2. 页面效果图

---

![image](https://github.com/xiaoconghu/app-music-server/blob/master/src/main/resources/png/1584154384571.png)
![image](https://github.com/xiaoconghu/app-music-server/blob/master/src/main/resources/png/1584154653568.png)
![image](https://github.com/xiaoconghu/app-music-server/blob/master/src/main/resources/png/1584154846540.png)
![image](https://github.com/xiaoconghu/app-music-server/blob/master/src/main/resources/png/1584154712143.png)
![image](https://github.com/xiaoconghu/app-music-server/blob/master/src/main/resources/png/1584155132774.png)


---
> 项目启动

1. git clone 项目
2. 安装 java maven mysql 等java开发需要的环境
3. 修改mysql连接等项目配置。导入resources中sql脚本
2. 通过idea 导入maven项目 安装依赖 通过mian方法启动
3. java -jar 启动
3. 浏览器打开http://localhost:8888

> 项目部署 
-  通过maven 将项目打成jar 在linux中运行

---
### 项目中难点问题

> 前端播放歌曲，后台返回的流断点续传处理

