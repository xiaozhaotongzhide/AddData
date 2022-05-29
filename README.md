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

import com.example.adddata.Bean.Data;
import com.example.adddata.Mapper.DataMapper;
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
    public String data(Model model){
        String data = dataService.getData();
        System.out.println(data);
        model.addAttribute("data",data);
        return "index";
    }
    @GetMapping("/add")
    public String addData(Model model){
        dataService.addData("10");
        return "forward:/";
    }

    @GetMapping("/reduce")
    public String reduceData(Model model){
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
