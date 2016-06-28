# MyBlog
个人博客源码，博客原网址：http://www.coselding.cn 。

之前没注意啊，今天发现这个项目多了好多个Star和Fork，真是承蒙厚爱啊！！！  
该项目的完全静态化分支分离创建了一个全新的项目，请前往新项目~[https://github.com/Coselding/Hamster](https://github.com/Coselding/Hamster)  
PS：这里简要说明一下该项目和Hamster的全静态化的项目的区别：  
1. 半静态化：博客页面的文章内容等一系列不变部分事先静态化成freemarker的ftl文件，然后再用户请求该页面时再把访问量、喜爱数量、最新文章等即时动态数据通过freemarker马上填充从而返回给用户浏览器。这样的好处是可以减少用户请求时的从数据库查询文章内容数据并加载到动态页面的时间（毕竟一篇文章有时候会很长，再加上一些样式标签，会更长），并且整个过程只进行一次请求。  
2. 完全静态化：文章页面的所有数据，在后台编写时会全部填充，存储为一个html文件，至于访问量、喜爱数量等即时性动态数据，用ajax在网页加载时再发送请求获取最新数据并更新页面，这样的好处是用户请求页面时，完全就是一个静态html页面，不需要Servlet去处理（还能直接交给nginx处理，更快），但是缺点是每个页面会多一次ajax请求，这两种方案孰轻孰重我也不是很清楚（实在太渣了我）。  
PS：站点设计过程中，就是想着尽可能的小，尽可能轻量化，放弃Mybatis，放弃Spring，可是开发到最后发现缺少了IoC容器，对象管理变得很乱，很是后悔，尽力吐槽吧=-=（用Struts2的原因仅仅是刚学会，想上手做个项目试试，也尽力吐槽吧，搞的url设置的很不好看），如果也有道友想开发类似的系统的话，强烈建议还是SSM吧，虽然反感这些东西很大，但是毕竟成熟的框架做出来还是很优雅的，单单RESTful风格网址Struts2就做不到了）。


采用了Struts2+JSP+FreeMarker+Service+Dao+dbutils进行架构设计，并对博客文章页面进行了静态化处理，加快浏览加载速度。前端界面采用第三方提供的开源界面框架，后台一个自己原创开发的基于JavaWeb的博客程序，感觉网上博客相关的源码php，NodeJS居多，只好自己完整的做一个。

本博客的特点在于，将博文页面中的文章内容等固定信息进行了半静态化处理，而其中的访问量，最新文章等动态信息则采用动态网页设计，减少了访问文章时的文章内容加载的耗时操作，虽然有点作死，但是至少为了这个项目学会了该怎么把动态网页静态化，我想这可是很多大型网站必不可少的工作。

开发环境为：IntelliJ 14+Tomcat 8+mysql 5.5+JDK1.8

本博客除了前台html+css+js使用了第三方框架以及使用的开源jar包框架，均为本人原创开发，版权归本人所有，开源仅仅为了学习交流使用，若未经同意擅自用于商业途径，将追究法律责任。

主页：
![image](https://github.com/Coselding/MyBlog/blob/master/screenshot/1.png)

文章列表：
![image](https://github.com/Coselding/MyBlog/blob/master/screenshot/2.png)

文章页面：
![image](https://github.com/Coselding/MyBlog/blob/master/screenshot/3.png)

留言板：
![image](https://github.com/Coselding/MyBlog/blob/master/screenshot/4.png)

![image](https://github.com/Coselding/MyBlog/blob/master/screenshot/5.png)

