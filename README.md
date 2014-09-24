# **百大易商城 Android 客户端项目简析** #

启动Eclipse，点击菜单并导入Android客户端项目，请确保你当前的Android SDK是最新版。<br>
如果编译出错，请修改项目根目录下的 project.properties 文件。<br>
推荐使用Android 4.0 以上版本的SDK,请使用JDK1.6编译：
> target=android-15

## release 0.1
* 实现商品浏览搜索
* 添加购物车、收藏
* 添加侧滑
* 滑动返回
* 第三方平台登录
* BaiDaData 和 GetData 用于测试获取数据

下面将简单的解析下项目：

## **一、项目的目录结构** ##
> 根目录<br>
> ├ src<br>
> ├ libs<br>
> ├ res<br>
> ├ AndroidManifest.xml<br>
> ├ LICENSE.txt<br>
> ├ proguard.cfg<br>
> └ project.properties<br>

**1、src目录**<br>
src目录用于存放项目的包及java源码文件。

下面是src目录的子目录：
> src<br>
> ├com.baida.activity<br>
> ├com.baida.adapter<br>
> ├com.baida.config<br>
> ├com.baida.db<br>
> ├com.baida.domain<br>
> ├com.baida.fragment<br>
> ├com.baida.service<br>
> ├com.baida.slidingmenu<br>
> ├com.baida.slidingmenu.lib<br>
> ├com.baida.swipeback<br>
> ├com.baida.util<br>


- com.baida.activity — activity类
- com.baida.adapter — 界面适配器
- com.baida.config — 配置信息
- com.baida.db — 数据库
- com.baida.domain —界面容器  
- com.baida.service —网络连接服务   
- com.baida.slidingmenu —侧滑菜单
- com.baida.slidingmenu.lib —侧滑菜单lib
- com.baida.swipeback —滑动返回
- com.baida.util —工具类

**2、libs目录**<br>
libs目录用于存放项目引用到的jar包文件。

下面是libs目录里的jar包文件：
> libs<br>
> └ jsoup-1.6.3.jar<br>

- jsoup-1.6.3.jar — jsoup解析网页工具包

> └ jsoup-1.6.3.jar<br>

- jsoup-1.6.3.jar — jsoup解析网页工具包



**3、res目录**<br>
res目录用于存放项目的图片、布局、样式等资源文件。

下面是res目录的子目录：
> res<br>
> ├ anim<br>
> ├ color<br>
> ├ drawable<br>
> ├ drawable-hdpi<br>
> ├ drawable-ldpi<br>
> ├ drawable-mdpi<br>
> ├ layout<br>
> ├ menu<br>
> ├ raw<br>
> ├ values<br>
> └ xml<br>

- anim — 动画效果
- color — 颜色
- drawable/drawable-hdpi/drawable-ldpi/drawable-mdpi — 图标、图片
- layout — 界面布局
- menu — 菜单
- raw — 图片
- values — 语言包和样式
- xml — 系统设置

**4、AndroidManifest.xml**<br>
AndroidManifest.xml用于设置应用程序的版本、主题、用户权限及注册Activity等。

## **二、项目的功能流程** ##

#### 1、APP启动流程 ####
AndroidManifest.xml注册的启动界面为"SplashActivity"，具体文件为src\com.baida.activity.SplashActivity.java文件。启动显示欢迎界面之后，通过意图(Intent)跳转到首页（com.baida.activity\MainActivity.java）。<br>
*注：除启动界面之外，其他所有界面都放在src\com.baida.activity包中。*

#### 2、APP访问API流程 ####

以首页资讯列表显示访问API数据为例：

**1) 初始化控件**<br>
首页MainActivity(MainActivity.java)在onCreate()方法里面加载布局文件(activity_main.xml)，对下拉刷新列表控件(PullToRefreshListView)进行了初始化，并设置了数据适配器(ListViewNewsAdapter)。<br>
*注：Main.xml布局文件在res\layout目录下；PullToRefreshListView控件在com.baida.widget包；ListViewNewsAdapter适配器在com.baida.adapter包。*

**2) 异步线程访问**<br>
列表控件初始化后，开启一个线程方法(loadLvNewsData())，该方法中调用全局应用程序类(AppContext)来访问API客户端类(ApiClient)。通过ApiClient以http方式请求服务器的API。返回响应的XML数据，再通过实体Bean(NewsList)解析XML，返回实体(NewsList)给UI控件(PullToRefreshListView)展示。<br>


**3) 解析数据显示**<br>
服务得到请求，将返回对应的资讯XML数据，再通过资讯实体类(NewsList)解析XML，返回实体(NewsList)给UI控件(PullToRefreshListView)展示。<br>
*注：NewsList实体类在com.baida.domain包。*


效果图<br>
![图片](/BaiDa/raw/device-2014-09-06-145438.png "首页")<br>
![图片](/BaiDa/raw/device-2014-09-06-145504.png "首页")<br>
![图片](/BaiDa/raw/device-2014-09-06-145513.png "首页")<br>
![图片](/BaiDa/raw/device-2014-09-06-145523.png "首页")<br>
![图片](/BaiDa/raw/device-2014-09-06-145533.png "首页")<br>
![图片](/BaiDa/raw/device-2014-09-06-145541.png "首页")<br>
![图片](/BaiDa/raw/device-2014-09-06-145602.png "首页")<br>
![图片](/BaiDa/raw/device-2014-09-06-145608.png "首页")<br>
![图片](/BaiDa/raw/device-2014-09-06-145615.png "首页")<br>
![图片](/BaiDa/raw/device-2014-09-06-145627.png "首页")<br>
![图片](/BaiDa/raw/device-2014-09-06-145641.png "首页")<br>
![图片](/BaiDa/raw/device-2014-09-06-145654.png "首页")<br>
![图片](/BaiDa/raw/device-2014-09-06-145709.png "首页")<br>

