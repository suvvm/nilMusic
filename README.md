# nilMusic

## 一、开发背景

​	音乐爬虫千千万，网易一抓全完蛋。趁着这次学期项目，不如搞个让用户自主配置外链的播放器应用。拿到外链👴就能随便搞事情。多快乐。

## 二、需求

- 提供推荐歌单，用来撑起主页场面
- 提供最基本的音乐播放功能
- 提供用户识别功能，用户信息保存在服务端
- 提供个人专辑CRUD，与专辑中音乐CRUD

## 三、框架选型与存储设计

- 客户端
  - 本地缓存使用Realm
  - http客户端使用OkHttp
  - json解析使用阿里fastjson-android
  - 图片展示使用circleimageview
  - 杂七杂八的验证功能使用com.blankj.utilcode
- 服务端
  - 使用go开发，http框架使用gin
  - DB使用MySql
  - orm框架使用gorm

## 四、核心逻辑

### 1、系统架构图

![image](https://raw.githubusercontent.com/suvvm/nilmusic_service/master/resources/nilMusic%E7%B3%BB%E7%BB%9F%E6%9E%B6%E6%9E%84.png)

### 2、客户端设计

- 启动后显示登陆页面，可在登陆页面前往注册，登陆时保存默认专辑信息与个人专辑信息至本地缓存

  ![login_img](https://raw.githubusercontent.com/suvvm/nilMusic/master/readme_res/login.jpg)

  ![register_img](https://raw.githubusercontent.com/suvvm/nilMusic/master/readme_res/register.jpg)

- 首页

  - 点击专辑可以显示专辑中全部音乐
  - 点击热歌列表中的歌曲可以直接开始播放

  ![main_img](https://raw.githubusercontent.com/suvvm/nilMusic/master/readme_res/main.jpg)

- 点击右上角个人信息按钮可以进入个人信息页面

- 个人信息页面

  - 自定义播放按钮可以进入个人专辑管理页面
  - 退出登陆可以退出当前用户并清空realm缓存

  ![me_img](https://raw.githubusercontent.com/suvvm/nilMusic/master/readme_res/me.jpg)

- 个人专辑管理页面

  - 点击我的专辑右侧加号可以创建新专辑
  - 点击专辑内容

  ![me_img](https://raw.githubusercontent.com/suvvm/nilMusic/master/readme_res/mdf_album.jpg)

![add_album](https://raw.githubusercontent.com/suvvm/nilMusic/master/readme_res/add_album.jpg)

![del_album](https://raw.githubusercontent.com/suvvm/nilMusic/master/readme_res/del_album.jpg)

- 专辑音乐管理页面

  - 点击音乐管理右侧加号可以在目标专辑新增音乐
  - 点击音乐右侧删除按钮可以删除音乐
  - 点击音乐右侧扳手按钮可以修改音乐信息

  ![mdf_music](https://raw.githubusercontent.com/suvvm/nilMusic/master/readme_res/mdf_music.jpg)

  ![add_music](https://raw.githubusercontent.com/suvvm/nilMusic/master/readme_res/add_music.jpg)

  ![del_music](https://raw.githubusercontent.com/suvvm/nilMusic/master/readme_res/del_music.jpg)

- 音乐播放页面

  - 点击中央盘带可以播放/暂停音乐

  ![music_play](https://raw.githubusercontent.com/suvvm/nilMusic/master/readme_res/music_play.jpg)

  ![music_stop](https://raw.githubusercontent.com/suvvm/nilMusic/master/readme_res/music_stop.jpg)

### 3、服务端设计

[nilmusic服务端](https://github.com/suvvm/nilmusic_service)

