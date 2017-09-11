containerhub
--

文件清单:
----

<table width="100%">
<tr><th>File Name</th><th>Description</th><th>备注</th> </tr>
<tr><td>\src\main\resources\application.properties</td><td>配置文件, 指定了Shell脚本的存放路径.</td><td>&nbsp;</td></tr>
</table>


使用指南
--

- 生成Eclipse工程文件,  便于调试
```
gradlew  cleanEclipse  eclipse
```

- 发布可执行包
```
gradlew  clean build
```

- 执行

生成的jar包在\build\libs\containerhub-0.1.0.jar 

```
java -jar   containerhub-0.1.0.jar
```
通过链接访问 [http://localhost:80](http://localhost:80)

接口说明:


<table width="100%">
<tr><th>资源名</th><th>请求类型</th><th>Description</th><th>参数列表</th> </tr>
<tr><td>/container/{ownerid}</td><td>POST</td><td>创建LIMS产品容器.</td><td>ownerid::String</td></tr>
<tr><td>/container/{ownerid}</td><td>GET</td><td>返回指定客户的容器信息.</td><td>ownerid::String</td></tr>
</table>


