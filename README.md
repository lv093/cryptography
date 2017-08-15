### PKI基础设施


###模块简介

+ `ca-management` 功能

    + 功能模块，证书保存，密钥备份和恢复，证书作废和处理系统
    
+ `ca-sign-issue` 功能
    + CA的证书签发模块，验证用户身份，签发证书，备份，通知用户
    
### 依赖服务

+ `ca-management` 依赖

+ `ca-sign-issue` 依赖

### 运行

+ 打包 `mvn clean package -Dmaven.test.skip=true`

+ 输出到`dist` 目录下，解压发布包，脚本添加可执行权限

+ 配置`application.properties` 文件

+ 运行`start.sh` 启动脚本
