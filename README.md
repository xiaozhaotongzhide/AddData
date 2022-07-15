# 从0到1为女朋友做一个记录积分的springboot小项目

### 前言

因为疫情的原因没有办法开学,于是我为女朋友设立了一个奖励她的机制,督促她好好学习,好好生活

![请添加图片描述](https://img-blog.csdnimg.cn/cf91c83353194f3fae02d782386aece4.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA5bCP6LW15ZGi,size_12,color_FFFFFF,t_70,g_se,x_16)


后来我琢磨这这积分,每天靠人记住也不是一个办法呀,于是就想着做一个springboot的小项目,部署到云端,帮助记一下,顺便复习一下springboot的知识

### 项目技术

由于项目比较简单,所以用到的技术比较简单

数据库:mysql

后端框架:spring-boot,mybatis,spring-mvc

前端渲染引擎:thymeleaf

前端网页制作:Adobe Photoshop 2021,vscode

### 前端部分

当然是先让我的小仙女挑选一个喜欢的模板了

![请添加图片描述](https://img-blog.csdnimg.cn/b48d0e2cc7f6473fb26816be3f5da057.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA5bCP6LW15ZGi,size_20,color_FFFFFF,t_70,g_se,x_16)

![请添加图片描述](https://img-blog.csdnimg.cn/a71908493064485d8a214a974849392e.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA5bCP6LW15ZGi,size_20,color_FFFFFF,t_70,g_se,x_16)

![请添加图片描述](https://img-blog.csdnimg.cn/60db0ffbf7cb4d22b681d5f29bdec259.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA5bCP6LW15ZGi,size_20,color_FFFFFF,t_70,g_se,x_16)



这是我们的模板

捣鼓了半天弄成这个样子

![请添加图片描述](https://img-blog.csdnimg.cn/d2e678f6149541e79c95c329575282e5.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA5bCP6LW15ZGi,size_20,color_FFFFFF,t_70,g_se,x_16)


![请添加图片描述](https://img-blog.csdnimg.cn/bc14df5efd0f45e88b3048c71d67ee8e.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA5bCP6LW15ZGi,size_20,color_FFFFFF,t_70,g_se,x_16)


### 后端部分

首先把之前说过的包引入一下

pom文件

```xml
<dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <!--            <version>5.1.49</version>-->
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jdbc</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>org.mybatis.spring.boot</groupId>
        <artifactId>mybatis-spring-boot-starter</artifactId>
        <version>2.1.4</version>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>

<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <configuration>
                <excludes>
                    <exclude>
                        <groupId>org.projectlombok</groupId>
                        <artifactId>lombok</artifactId>
                    </exclude>
                </excludes>
            </configuration>
        </plugin>
    </plugins>
</build>
```

导入依赖

```yaml
server:
  port: 8081

spring:
  datasource:
    url: jdbc:mysql://:3306/javaee
    username: root
    password: 123456
    driver-class-name: com.mysql.jdbc.Driver
```

把我们的静态文件导入进来

![请添加图片描述](https://img-blog.csdnimg.cn/2acdb1c30efc4c6dacc221a33facdb08.png)


在HTML中导入我们的thymeleaf

```
<html lang="en" xmlns:th="http://www.thymeleaf.org">
```

之后先写我们的Controller类

```java
package com.example.adddata.Controller;

import com.example.adddata.Service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DataController {

    @Autowired
    DataService dataService;

    @GetMapping("/")
    public String data(Model model) {
        String data = dataService.getData();
        System.out.println(data);
        model.addAttribute("data", data);
        return "index";
    }

    @GetMapping("/add")
    public String addData(Model model) {
        dataService.addData("10");
        return "forward:/";
    }

    @GetMapping("/reduce")
    public String reduceData(Model model) {
        dataService.reduceData("10");
        return "forward:/";
    }
}
```

Service类

```java
@Service
public class DataServiceImpl implements DataService {
    @Autowired
    DataMapper dataMapper;
    @Override
    public String getData() {
        Data data = dataMapper.getData();
        String data1 = data.getData();
        return data1;
    }

    @Override
    public void addData(String data) {
        Integer A = Integer.parseInt(data);
        Data data1 = dataMapper.getData();
        String data2 = data1.getData();
        Integer B = Integer.parseInt(data2);
        dataMapper.addData(String.valueOf((B+A)));
    }

    @Override
    public void reduceData(String data) {
        Integer A = Integer.parseInt(data);
        Data data1 = dataMapper.getData();
        String data2 = data1.getData();
        Integer B = Integer.parseInt(data2);
        dataMapper.reduceData(String.valueOf((B-A)));
    }
}
```

Mapper类

```java
@Mapper
@Repository
public interface DataMapper {

    @Select("select * from data where id = 1")
    Data getData();

    @Update("UPDATE data set data = #{data} where id = 1")
    void addData(String data);

    @Update("UPDATE data set data = #{data} where id = 1")
    void reduceData(String data);
}
```

Bean类

```
public class Data {

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", data='" + data + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    Integer id;

    String data;
}
```

数据库


![请添加图片描述](https://img-blog.csdnimg.cn/084dadf8f7e3471e80b18c6170feca58.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA5bCP6LW15ZGi,size_19,color_FFFFFF,t_70,g_se,x_16)


### 云端部署

因为mysql本身已经部署到云端是所以只需要把springboot打成jar包然后部署就可以了

![请添加图片描述](https://img-blog.csdnimg.cn/b3244e2bfef44de29e0407f508d9918c.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA5bCP6LW15ZGi,size_13,color_FFFFFF,t_70,g_se,x_16)

先clean然后package

![请添加图片描述](https://img-blog.csdnimg.cn/5104c00743f2476daf64a59a63e8f8b7.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA5bCP6LW15ZGi,size_20,color_FFFFFF,t_70,g_se,x_16)

之后把jar包移入到云服务器上

后台运行上我们的jar包就ok

nohup java -jar (jar包名) &

这是我们的项目地址:

### 后言

2022.7.15

由于之前那个前端网页做的实在是太丑了

![image-20220715235910493](http://cdn.zhaodapiaoliang.top/PicGo/image-20220715235910493.png)

于是我决定重写一下,最近接触到了一个可以把psd转换为vue或者是HTML的在线编辑平台

[任性布局 (shagua.wiki)](http://aicode.shagua.wiki/uni/index.html#/)

![image-20220716000110691](http://cdn.zhaodapiaoliang.top/PicGo/image-20220716000110691.png)

直接根据教程在ps中安装相对应的插件

```html
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <!-- CSS only -->
    <link rel="stylesheet" href="http://i.gtimg.cn/vipstyle/frozenui/2.0.0/css/frozen.css">
    <link href="https://cdn.bootcdn.net/ajax/libs/animate.css/4.1.1/animate.compat.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>添加数据</title>
    <style>
        /** 全局样式-开始,建议放在公共css样式文件中,比如common.css 或app.vue文件 */

        html,
        body {
            border: 0px;
            margin: 0;
            width: 100%;
            height: 100%;
            font-size: 16px
        }

        * {
            position: relative;
            box-sizing: border-box;
            flex-shrink: 0;
        }

        .flex-col {
            display: flex;
            align-items: flex-start;
            flex-direction: column;
        }

        .flex-row {
            display: flex;
            align-items: flex-start;
        }

        .flex-col .flex-row {
            width: 100%
        }

        /** 全局样式-结束*/

        .homepage {
            padding: 0px;
            padding-left: 0px;
        }

        .homepage .layer-1 {
            padding: 0px;
            margin: 0px;
            border: 0px;
            width: 97.843vw;
            justify-content: center;
            padding: 1.941vw 0vw;
            background: url('https://vkceyugu.cdn.bspapp.com/VKCEYUGU-55dbb834-9ba8-4068-98ca-c341f6402e27/471574bc-8126-4525-9eaa-4e6b5f2e31ea.png') no-repeat;
            background-size: 100% 100%;
        }

        .homepage .layer-1 .layer-2 {
            width: 93.961vw;
            padding: 25.665vw 0vw 10.927vw 3.379vw;
            background: url('https://vkceyugu.cdn.bspapp.com/VKCEYUGU-55dbb834-9ba8-4068-98ca-c341f6402e27/463d3198-cfae-496c-99bb-acfda7694d58.png') no-repeat;
            background-size: 100% 100%;
        }

        .homepage .layer-1 .set-1 {
            width: 87.275vw;
            height: 101.438vw;
            margin-bottom: 16.463vw;
            padding: 5.823vw 0vw 0vw 6.542vw;
            background: url('https://vkceyugu.cdn.bspapp.com/VKCEYUGU-55dbb834-9ba8-4068-98ca-c341f6402e27/7a51fb7f-d37a-4191-97e9-95452eb9f1b7.png') no-repeat;
            background-size: 100% 100%;
        }

        .homepage .layer-1 .layer-15 {
            width: 5.751vw;
            height: 5.823vw;
            margin: 0vw 0vw 6.542vw 62.042vw;
        }

        .homepage .layer-1 .layer-31 {
            width: 32.782vw;
            justify-content: center;
            margin: 0vw 0vw 6.758vw 20.705vw;
            padding: 9.274vw 0vw 12.725vw 0vw;
            background: url('https://vkceyugu.cdn.bspapp.com/VKCEYUGU-55dbb834-9ba8-4068-98ca-c341f6402e27/4be3f982-8774-4287-9e33-8cf4f956dc09.png') no-repeat;
            background-size: 100% 100%;
        }

        .homepage .layer-1 .layer-32 {
            width: 14.881vw;
            height: 10.855vw;
        }

        .homepage .layer-1 .box-row {
            width: 22.574vw;
            margin: 0vw 0vw 9.13vw 24.946vw;
        }

        .homepage .layer-1 .text {
            margin-right: -0.072vw;
            letter-spacing: 0.874vw;
            font-size: 5.464vw;
            line-height: 5.032vw;
            color: #ed473f;
        }

        .homepage .layer-1 .fifteen-point-nine {
            margin-top: 0.431vw;
            letter-spacing: 0.874vw;
            font-size: 5.464vw;
            line-height: 4.098vw;
            color: #2b2b2b;
        }

        .homepage .layer-1 .box-row-two {
            width: 44.572vw;
            justify-content: space-between;
        }

        .homepage .layer-1 .layer-10 {
            width: 11.215vw;
            justify-content: center;
            margin-top: 4.817vw;
            padding: 3.882vw 0vw 3.666vw 0vw;
            background: url('https://vkceyugu.cdn.bspapp.com/VKCEYUGU-55dbb834-9ba8-4068-98ca-c341f6402e27/bf033eb9-8243-444e-b287-c5e9468ad8f4.png') no-repeat;
            background-size: 100% 100%;
        }

        .homepage .layer-1 .layer-61-copy {
            width: 5.104vw;
            height: 3.666vw;
        }

        .homepage .layer-1 .layer-25-copy {
            width: 17.11vw;
            padding: 1.653vw 0vw 1.582vw 5.751vw;
            background: url('https://vkceyugu.cdn.bspapp.com/VKCEYUGU-55dbb834-9ba8-4068-98ca-c341f6402e27/9d1518bc-e2db-4331-833a-22c2cf631600.png') no-repeat;
            background-size: 100% 100%;
        }

        .homepage .layer-1 .add-copy {
            font-size: 3.307vw;
            line-height: 2.588vw;
            color: #ffffff;
        }

        .homepage .layer-1 .layer-17 {
            width: 18.476vw;
            justify-content: center;
            padding: 6.254vw 0vw 6.326vw 0vw;
            bottom: -9.13vw;
            left: 19.77vw;
            background: url('https://vkceyugu.cdn.bspapp.com/VKCEYUGU-55dbb834-9ba8-4068-98ca-c341f6402e27/b46c13a4-a06d-4ea7-b632-52fbf64fe2a9.png') no-repeat;
            background-size: 100% 100%;
            position: absolute;
            z-index: 5;
        }

        .homepage .layer-1 .layer-18 {
            width: 6.039vw;
            height: 5.823vw;
        }

        .homepage .layer-1 .layer-29-copy {
            width: 18.476vw;
            justify-content: center;
            padding: 6.686vw 0vw;
            bottom: -9.13vw;
            right: 19.77vw;
            background: url('https://vkceyugu.cdn.bspapp.com/VKCEYUGU-55dbb834-9ba8-4068-98ca-c341f6402e27/9bdfa7e8-d468-4a67-a4c3-b7985a6de639.png') no-repeat;
            background-size: 100% 100%;
            position: absolute;
            z-index: 7;
        }

        .homepage .layer-1 .layer-19 {
            width: 5.176vw;
            height: 5.032vw;
        }

        .homepage .layer-1 .layer-11 {
            width: 56.075vw;
            margin: 0vw 0vw 3.882vw 15.6vw;
            padding: 1.725vw 0vw 1.797vw 51.546vw;
            background: url('https://vkceyugu.cdn.bspapp.com/VKCEYUGU-55dbb834-9ba8-4068-98ca-c341f6402e27/fa1e2746-2658-4013-b911-fe0399419657.png') no-repeat;
            background-size: 100% 100%;
        }

        .homepage .layer-1 .layer-12 {
            width: 2.588vw;
            height: 2.516vw;
        }

        .homepage .layer-1 .layer-16 {
            width: 32.351vw;
            margin: 0vw 0vw 3.738vw 27.462vw;
            padding: 2.372vw 0vw 2.804vw 4.817vw;
            background: url('https://vkceyugu.cdn.bspapp.com/VKCEYUGU-55dbb834-9ba8-4068-98ca-c341f6402e27/ae3d189a-d1ad-4e3a-9b36-c0acb2ca6a56.png') no-repeat;
            background-size: 100% 100%;
        }

        .homepage .layer-1 .new-100 {
            letter-spacing: 0.359vw;
            font-size: 3.595vw;
            line-height: 2.588vw;
            text-align: center;
            color: #000000;
        }

        .homepage .layer-1 .layer-16-copy {
            width: 32.351vw;
            margin-left: 27.462vw;
            padding: 2.372vw 0vw 2.804vw 7.836vw;
            background: url('https://vkceyugu.cdn.bspapp.com/VKCEYUGU-55dbb834-9ba8-4068-98ca-c341f6402e27/7127ac27-faef-4118-b1ff-6d2d7c3a4220.png') no-repeat;
            background-size: 100% 100%;
        }
    </style>
</head>

<body>
    <div style="margin: auto;" class="homepage">
        <div class="flex-row layer-1">
            <div class="flex-col layer-2">
                <div class="flex-col set-1">
                    <img class="layer-15" src="https://vkceyugu.cdn.bspapp.com/VKCEYUGU-55dbb834-9ba8-4068-98ca-c341f6402e27/e6daa8cf-c9d7-4c6f-a029-2064a9f5ad6a.png" />
                    <div class="flex-row layer-31">
                        <img class="layer-32" src="https://vkceyugu.cdn.bspapp.com/VKCEYUGU-55dbb834-9ba8-4068-98ca-c341f6402e27/0a5402f4-19cc-4480-8d73-57a85973f237.png" />
                    </div>
                    <div class="flex-row box-row">
                        <span class="text">$</span><span th:text="${data}" class="fifteen-point-nine"></span>
                    </div>
                    <div class="flex-row box-row-two">
                        <div class="flex-row layer-10">
                            <img class="layer-61-copy" src="https://vkceyugu.cdn.bspapp.com/VKCEYUGU-55dbb834-9ba8-4068-98ca-c341f6402e27/94e00768-2b27-4f7c-a227-6c3e7dc346cd.png" />
                        </div>
                        <div class="layer-25-copy">
                            <span class="add-copy">add</span>
                        </div>
                    </div>
                    <div class="flex-row layer-17">
                        <img class="layer-18" src="https://vkceyugu.cdn.bspapp.com/VKCEYUGU-55dbb834-9ba8-4068-98ca-c341f6402e27/df3d700d-3b45-4380-b6a2-fed6393cb06a.png" />
                    </div>
                    <div class="flex-row layer-29-copy">
                        <img class="layer-19" src="https://vkceyugu.cdn.bspapp.com/VKCEYUGU-55dbb834-9ba8-4068-98ca-c341f6402e27/46f8c1f6-26b0-4014-bf9c-25d8e5255eeb.png" />
                    </div>
                </div>
                <div style="margin: auto;" class="layer-11">
                    <img class="layer-12" src="https://vkceyugu.cdn.bspapp.com/VKCEYUGU-55dbb834-9ba8-4068-98ca-c341f6402e27/7e9b8882-6371-493f-8912-56c02d2a662c.png" />
                </div>
                <div style="padding-top: 10px;"></div>
                <div style="margin: auto;">
                    <button type="button" style="width: 56px;" class="btn btn-light">
                        <a href="/add">+10</a>
                    </button>
                </div>
                <div style="padding-top: 10px;"></div>
                <div style="margin: auto;">
                    <button type="button" style="width: 56px;" class="btn btn-light">
                        <a href="/reduce">-10</a>
                    </button>
                </div>
            </div>
        </div>
    </div>


</body>

</html>
```

这里贴一段我生成的代码

然后暂停掉服务器上的代码

```
netstat -nlp|grep 8089 
//8089是系统启动访问的端口，从输出数据中可以看到线程对应的pid 
kill -9 pid 
//强制停掉 pid 对应线程
```

![Screenshot_2022-07-16-00-16-14-15_9d26c6446fd7bb8e41d99b6262b17def](http://cdn.zhaodapiaoliang.top/PicGo/Screenshot_2022-07-16-00-16-14-15_9d26c6446fd7bb8e41d99b6262b17def.jpg)

最后看一下效果