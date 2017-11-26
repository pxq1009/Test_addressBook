# drivingAPI
## 工具
```
1、IDEA 地址：http://pan.baidu.com/s/1bo5TF4r  密码：8erd
2、破解IDEA的jar包 地址：http://pan.baidu.com/s/1skXRwWt   密码：smf3
3、jdk1.8 地址：http://pan.baidu.com/s/1kVb0ANH    密码：zodx
4、maven 地址：http://pan.baidu.com/s/1hsCe9MW     密码：t23s
5、tomcat 地址：http://pan.baidu.com/s/1mi7mDKW    密码：1cyv
 ```
 ## 步骤

 1、IDEA下载安装破解（不破解可用30天），可用导jar包破解；
 参考地址：http://idea.lanyus.com/

 2、maven安装，安装好maven后配置本地仓库；
 参考地址：http://blog.csdn.net/qq_32588349/article/details/51461182

 3、IDEA配置jdk和tomcat
 参考地址：http://blog.csdn.net/u010297791/article/details/68950967

 4、导入项目，执行main函数

 5、IDEA查看数据库：
 参考地址：http://blog.csdn.net/qq_27093465/article/details/52872582

 ## 重要目录介绍
 1、pox.xml:
    跟目录下的pom.xml是maven项目配置文件的地方，需要什么jar，在这里写上配置文件就能直接下载到本地仓库。
 2、src：
 - java里面是写java源码
 - resources里面是写映射文件和配置文件，主要是mapper和applicaion.properties
 - DrivingapiApplication是程序入口，执行main()

 3、com.driving.status:封装一些工具类。

 4、com.driving.model:写实体类。

 5、com.driving.mapper:写映射在mapper.xml里面的接口。

 6、com.driving.service:写业务层函数接口。

 7、com.driving.service.impl:写业务层接口的实现。

 8、com.driving.controller:写具体实现方法（接口路径）。


 ## 框架使用（以查询account表所有数据为例子）
 
 **1、在com.driving.model包下建立实体类**

注意事项：
 （1）创建文件时，首字母一定要大写；
 （2）get、set、toString方法可以使用编辑器的自动生成快捷方式（Alt+Insert），再选择对应的生成方式（Getter and Setter和toString()），Alt+a全选，OK即可。
 （3）将数据库语句添加到sql文件夹下的driving.sql中
 ```
 Account.java

 /**
 * created by wk on 2017-11-23
 * account实体类
 */
public class Account {

    private []byte id;
    private String phone;
    private String wx_account;
    private String login_password;

    public Account() {
        super();
    }

    seter,geter方法省略...

    @Override
    public String toString() {
        return "Account{" +
                "id=" + Arrays.toString(id) +
                ", phone='" + phone + '\'' +
                ", wx_account='" + wx_account + '\'' +
                ", login_password='" + login_password + '\'' +
                '}';
    }
}
```

2、com.driving.mapper 里写接口

注意事项：
 （1）注意修改import的实体。
 （2）命名需要符合规范
 （示例：*findAllUserByAccount：表示通过account查找所有user，如果有多个查询条件则可直接写成findAllUser，如果只查找一条数据则写成findUser*）
```
AccountMapper.java

/**
 * created by wk on 2017-11-23
 * mapper映射，方法对应mapper.xml的id名
 */
 
@Component
public interface AccountMapper {

    /**
     * 查询所有account
     * @return
     */
    List<Account> findAllAccount();

}
```

3、添加新的数据库映射文件

注意事项：
 （1）新建一个文件，命名AccountMapper.xml，复制其他的Mapper文件里面的代码进去修改即可。
 （2）先修改mapper的namespace改成之前新建的Mapper文件的名字。
 （3）在修改resultMaptype和其中的字段。
 （4）书写操作数据表的操作。
```
AccountMapper.xml：

<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="com.driving.mapper.AccountMapper" >
  <resultMap id="BaseResultMap" type="com.driving.model.Account" >
    <id column="id" property="id" jdbcType="VARBINARY" />
    <result column="phone" property="phone" jdbcType="VARCHAR" />
    <result column="wx_account" property="wx_account" jdbcType="VARCHAR" />
    <result column="login_password" property="login_password" jdbcType="VARCHAR" />
  </resultMap>

 <!-- 查询 Account-->
  <select id="findAllAccount" resultType="com.driving.model.Account">
    SELECT  id,phone,wx_account,login_password FROM account
  </select>
</mapper>
```

4、com.driving.service写业务接口
```
AccountService.java

/**
 * Created by wk on 2017-11-23
 * account 的 service 的接口
 */
public interface AccountService {

    /**
     * 查询所有 Account
     * @return
     */
    List<Account> findAllAccount();

}
```

5、com.driving.service.impl里写业务接口实现函数
```
AccountServiceImpl.java

/**
 * Created by wk on 2017-11-23
 * account的service层的实现
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    /**
     * 查询所有 Account
     * @return
     */
    @Override
    public List<Account> findAllAccount() {
        return accountMapper.findAllAccount();
    }

}
```

6、com.driving.controller写接口api实现（映射路径）
```
AccountController.java

/**
 * created by wk on 2017-11-23
 * account的Controller
 */
@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    /**
     * 查询所有Account
     * @return
     */
    @RequestMapping(value="/getAllAccount",method= RequestMethod.GET)
    public List<Account> getAllAccount(){
        List<Account> accountList = accountService.findAllAccount();
        return accountList;
    }

}
```
7、运行main()，接口地址(对应application.properties和AccountController.java文件)：
> localhost:8081/drivingAPI/getAllAccount
